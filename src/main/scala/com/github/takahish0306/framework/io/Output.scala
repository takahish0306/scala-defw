/**
 *
 * @author takahish0306 
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.io

import java.io.{FileOutputStream, IOException}
import org.apache.commons.io.{IOUtils, FileUtils}
import com.github.takahish0306.framework.log.Logger

trait Output extends Logger {

  /**
   * To use output stream
   *
   * @param filepath String
   * @param operation FileOutputStream => T
   * @return Option[T]
   */
  def withOutputStream[T](filepath: String)(operation: FileOutputStream => T): Option[T] = {
    var stream: FileOutputStream = null
    val exceptionMessage = "! Output.withOutputStream failed."
    try{
      stream = FileUtils.openOutputStream(File(filepath))
      Some(operation(stream))
    } catch {
      case e: IOException => {
        logger.error(exceptionMessage 
            + " File object is a directory or cannot be read,"
            + " or a parent directory needs creating but that failed.")
        None
      }
      case e: Exception => {
        logger.error(exceptionMessage + " Unknown. " + e.getMessage)
        None
      }
    }
  }

}