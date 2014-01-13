/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package defw.io

import scala.io.Source
import defw.log.Log

object IO extends Input with Output {

  /**
   * To read string from file.
   *
   * @param filepath String
   * @return Option[String]
   */
  def read(filepath: String): Option[String] = {
    withFileInputStream[String](File(filepath)) {
      stream => {
        try {
          Source.fromInputStream(stream).getLines.mkString
        } catch {
          case e: Exception => {
            Log.error("! IO.read failed. A problem occurred.")
            throw e
          }
        }
      }
    }
  }

  /**
   * To write string to file.
   *
   * @param filepath String
   * @param data String
   * @return Option[Unit]
   */
  def write(filepath: String, data: String): Option[Unit] = {
    withFileOutputStream[Unit](File(filepath)) {
      stream => {
        try {
          val bytes = data.getBytes
          for (byte <- bytes) stream.write(byte)
        } catch {
          case e: Exception => {
            Log.error("! IO.write failed. A problem occurred.")
            throw e
          }
        }
      }
    }
  }
}
