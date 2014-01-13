/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package defw.json

object JSON extends LiftWebJSON {

  /**
   * To create a JSON string from a scala object.
   *
   * @param obj Any
   * @return Option[String]
   */
  override def encode(obj: AnyRef): Option[String] = {
    super.encode(obj)
  }

  /**
   * To create a simple scala object from a JSON string.
   *
   * @param jsonString String
   * @return Option[T]
   */
  override def decode[T: Manifest](jsonString: String): Option[T] = {
    super.decode[T](jsonString)
  }

}
