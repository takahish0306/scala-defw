/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.io

import java.io.{File => FileObject, FileInputStream, FileNotFoundException, IOException}
import org.apache.commons.io.{IOUtils, FileUtils}
import com.github.takahish0306.framework.log.Logger

trait Input extends Logger {

  /**
   * To use input stream
   *
   * @param fileobject FileObject
   * @param operation FileInputStream => T
   * @return Option[T]
   */
  def withFileInputStream[T](fileobject: FileObject)(operation: FileInputStream => T): Option[T] = {
    var stream: FileInputStream = null
    val exceptionMessage = "! Input.withFileInputStream failed."
    try {
      stream = FileUtils.openInputStream(fileobject)
      Some(operation(stream))      
    } catch {
      case e: FileNotFoundException => {
        logger.error(exceptionMessage + " File does not exist.")
        None
      }
      case e: IOException => {
        logger.error(exceptionMessage + " File object is a directory or cannot be read.")
        None
      }
      case e: Exception => {
        logger.error(exceptionMessage + " Unknown.")
        logger.error(e.getMessage)
        None
      }
    } finally {
      IOUtils.closeQuietly(stream)
    }
  }

  /**
   * To use input stream (overload)
   *
   * @param filepath String
   * @param operation FileInputStream => T
   * @return Option[T]
   */
  def withFileInputStream[T](filepath: String)(operation: FileInputStream => T): Option[T] = {
    withFileInputStream[T](File(filepath))(operation)
  }

}
