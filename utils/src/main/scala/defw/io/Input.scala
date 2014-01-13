/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package defw.io

import java.io.{File => JavaIoFile, FileInputStream, FileNotFoundException, IOException}
import org.apache.commons.io.{IOUtils, FileUtils}
import defw.log.Log

trait Input {

  /**
   * To use input stream
   *
   * @param fileobject JavaIoFile
   * @param operation FileInputStream => T
   * @return Option[T]
   */
  def withFileInputStream[T](fileobject: JavaIoFile)(operation: FileInputStream => T): Option[T] = {
    var stream: FileInputStream = null
    val exceptionMessage = "! Input.withFileInputStream failed."
    try {
      stream = FileUtils.openInputStream(fileobject)
      Some(operation(stream))      
    } catch {
      case e: FileNotFoundException => {
        Log.error(exceptionMessage + " File does not exist.")
        None
      }
      case e: IOException => {
        Log.error(exceptionMessage + " File object is a directory or cannot be read.")
        None
      }
      case e: Exception => {
        Log.error(exceptionMessage + " Unknown.")
        Log.error("StackTrace:\n" + e.getStackTrace.mkString("\n"))
        None
      }
    } finally {
      IOUtils.closeQuietly(stream)
    }
  }

}
