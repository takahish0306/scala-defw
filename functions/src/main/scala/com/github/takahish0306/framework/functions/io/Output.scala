/**
 *
 * @author takahish0306 
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.functions.io

import java.io.{File => JavaIoFile, FileOutputStream, IOException}
import org.apache.commons.io.{IOUtils, FileUtils}
import com.github.takahish0306.framework.functions.log.Logger

trait Output extends Logger {

  /**
   * To use output stream
   *
   * @param fileobject JavaIoFile
   * @param operation FileOutputStream => T
   * @return Option[T]
   */
  def withFileOutputStream[T](fileobject: JavaIoFile)(operation: FileOutputStream => T): Option[T] = {
    var stream: FileOutputStream = null
    val exceptionMessage = "! Output.withFileOutputStream failed."
    try{
      stream = FileUtils.openOutputStream(fileobject)
      Some(operation(stream))
    } catch {
      case e: IOException => {
        logger.error(exceptionMessage 
            + " File object is a directory or cannot be read,"
            + " or a parent directory needs creating but that failed.")
        None
      }
      case e: Exception => {
        logger.error(exceptionMessage + " Unknown.")
        logger.error("StackTrace:\n" + e.getStackTrace.mkString("\n"))
        None
      }
    } finally {
      IOUtils.closeQuietly(stream)
    }
  }

  /**
   * To use output stream (overload)
   *
   * @param filepath String
   * @param operation FileOutputStream => T
   * @return Option[T]
   */
  def withFileOutputStream[T](filepath: String)(operation: FileOutputStream => T): Option[T] = {
    withFileOutputStream[T](File(filepath))(operation)
  }

}
