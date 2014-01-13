/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package defw.http

import java.io.IOException
import java.net.{URI => JavaNetURI}
import java.util.ArrayList

import org.apache.http.NameValuePair
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.methods.{HttpRequestBase, HttpGet, HttpPost, CloseableHttpResponse}
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.impl.client.{HttpClients, CloseableHttpClient}

import defw.log.Log

trait Response extends Client {

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
            Log.error(exceptionMessage + " Http protocol error.")
            throw e
	  }
          case e: IOException => {
            Log.error(exceptionMessage + " A problem or the connection was aborted.")
            throw e
          }
          case e: Exception => {
            Log.error(exceptionMessage + " Unknown.")
            Log.error("StackTrace:\n" + e.getStackTrace.mkString("\n"))
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
   * @param uri JavaNetURI
   * @param operation CloseableHttpResponse => T
   * @return Option[T]
   */
  def withHttpGetResponse[T](uri: JavaNetURI)(operation: CloseableHttpResponse => T): Option[T] = {
    withHttpResponse[T](new HttpGet(uri))(operation)
  }

  /**
   * To use HTTP/POST response
   *
   * @param uri JavaNetURI
   * @param parameter ArrayList[NameValuePair]
   * @param operation CloseableHttpResponse => T
   * @return Option[T]
   */
  def withHttpPostResponse[T](uri: JavaNetURI, parameter: ArrayList[NameValuePair])(operation: CloseableHttpResponse => T): Option[T] = {
    // to set Entity
    val httppost = new HttpPost(uri)
    httppost.setEntity(new UrlEncodedFormEntity(parameter))
    withHttpResponse[T](httppost)(operation)
  }

}
