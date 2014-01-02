/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.http

import java.io.{IOException, InputStream}
import org.apache.commons.io.IOUtils
import scala.io.Source
import com.github.takahish0306.framework.log.Logger

object Http extends Response with Logger {

  /**
   * To get HTTP/GET response
   *
   * @param uri String
   * @return Option[String]
   */
  def get(uri: String): Option[(Int, String)] = {
    var stream: InputStream = null
    withHttpGetResponse[(Int, String)](URI(uri)) {
      response => {
        try {
          val status = response.getStatusLine.getStatusCode
          stream = response.getEntity.getContent
          (status, Source.fromInputStream(stream).getLines.mkString)
        } catch {
          case e: Exception => {
            logger.error("! Http.get failed. A problem occurred.")
            throw e
          }
        } finally {
          IOUtils.closeQuietly(stream)
        }
      }
    }
  }

  /**
   * To get HTTP/POST response
   *
   * @param uri String
   * @param parameter Map[String, String]
   * @return Option[String]
   */
  def post(uri: String, parameter: Map[String, String]): Option[(Int, String)] = {
    var stream: InputStream = null
    withHttpPostResponse[(Int, String)](URI(uri), Parameter(parameter)) {
      response => {
        try {
          val status = response.getStatusLine.getStatusCode
          stream = response.getEntity.getContent
          (status, Source.fromInputStream(stream).getLines.mkString)
        } catch {
          case e: Exception => {
            logger.error("! Http.post failed. A problem occurred.")
            throw e
          }
        } finally {
          IOUtils.closeQuietly(stream)
        }
      }
    }
  }

}
