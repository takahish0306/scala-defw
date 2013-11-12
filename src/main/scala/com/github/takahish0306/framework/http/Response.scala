/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.http

import java.io.IOException
import java.net.{URI => JavaNetURI}
import java.util.ArrayList

import org.apache.http.NameValuePair
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.methods.{HttpRequestBase, HttpGet, HttpPost, CloseableHttpResponse}
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.message.BasicNameValuePair
import org.apache.http.impl.client.{HttpClients, CloseableHttpClient}

import com.github.takahish0306.framework.log.Logger

trait Response extends Client with Logger {

  /**
   * To use http response
   *
   * @param uri String
   * @param operation CloseableHttpResponse => T
   * @return Option[T]
   */
  def withHttpResponse[T](method: HttpRequestBase)(operation: CloseableHttpResponse => T): Option[T] = {
    var response: CloseableHttpResponse = null
    val exceptionMessage = "! Client.withHttpResponse failed."

    withHttpClient[T] {
      client => {
        try {
          response = client.execute(method)
          operation(response)
        } catch {
          case e: ClientProtocolException => {
            logger.error(exceptionMessage + " Http protocol error.")
            throw e
	  }
          case e: IOException => {
            logger.error(exceptionMessage + " A problem or the connection was aborted.")
            throw e
          }
          case e: Exception => {
            logger.error(exceptionMessage + " Unknown.")
            logger.error("StackTrace:\n" + e.getStackTrace.mkString("\n"))
            throw e
          }
	} finally {
          response.close
	}
      }
    }
  }

  /**
   * To use HTTP/GET response
   *
   * @param uri String
   * @param operation CloseableHttpResponse => T
   * @return Option[T]
   */
  def withHttpGetResponse[T](uri: String)(operation: CloseableHttpResponse => T): Option[T] = {
    withHttpResponse[T](new HttpGet(URI(uri)))(operation)
  }

  /**
   * To use HTTP/POST response
   *
   * @param uri String
   * @param params Map[String, String]
   * @param operation CloseableHttpResponse => T
   * @return Option[T]
   */
  def withHttpPostResponse[T](uri: String, params: Map[String, String])(operation: CloseableHttpResponse => T): Option[T] = {
    // to convert into List[BasicNameValuePair]
    val list  = for ((name, value) <- params) yield new BasicNameValuePair(name, value)

    // to convert into ArrayList[NameValuePair]
    val pairs = list.foldLeft(new ArrayList[NameValuePair])((pairs, pair) => { pairs.add(pair); pairs })

    // to set Entity
    val httppost = new HttpPost(URI(uri))
    httppost.setEntity(new UrlEncodedFormEntity(pairs))

    withHttpResponse[T](httppost)(operation)
  }

}
