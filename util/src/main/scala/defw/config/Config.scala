/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package defw.config

import defw.io.File

object Config extends Eval {

  /**
   * To get config from file
   *
   * @param filepath String
   * @return Option[T]
   */
  def load[T](filepath: String): Option[T] = {
    super.load[T](File(filepath))
  }

}
