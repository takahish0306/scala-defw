/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package defw.json

import net.liftweb.json._
import net.liftweb.json.Serialization.write
import defw.log.Log

trait LiftWebJSON {

  /**
   * To create a JSON string from a scala object.
   *
   * @param obj Any
   * @return Option[String]
   */
  def encode(obj: AnyRef): Option[String] = {
    val errorMessage = "! LiftWebJSON.encode failed."
    try {
      // create a JSON string from obj, then print it
      implicit val formats = DefaultFormats
      Some(write(obj))
    } catch {
      case e: Exception => {
        Log.error(errorMessage + " A problem occurred.")
        Log.error("StackTrace:\n" + e.getStackTrace.mkString("\n"))
        None
      }
    }
  }

  /**
   * To create a simple scala object from a JSON string.
   *
   * @param jsonString String
   * @return Option[T]
   */
  def decode[T: Manifest](jsonString: String): Option[T] = {
    val errorMessage = "! LiftWebJSON.decode failed."
    try {
      implicit val formats = DefaultFormats
      // convert a String to JValue object
      val jValue = parse(jsonString)
      // create a object from the string
      Some(jValue.extract[T])
    } catch {
      case e: Exception => {
        Log.error(errorMessage + " A problem occurred.")
        Log.error("StackTrace:\n" + e.getStackTrace.mkString("\n"))
        None
      }
    }
  }
}
