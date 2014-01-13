/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package defw.log

object Log extends Logger {

  /**
   * To print [ERROR] message.
   *
   * @param message String
   * @return Unit
   */
  def error(message: String): Unit = {
    logger.error(message)
  }

  /**
   * To print [WARNING] message.
   *
   * @param message String
   * @return Unit
   */
  def warn(message: String): Unit = {
    logger.warn(message)
  }

  /**
   * To print [INFO] message.
   *
   * @param message String
   * @return Unit
   */
  def info(message: String): Unit = {
    logger.info(message)
  }

  /**
   * To print [DEBUG] message.
   *
   * @param message String
   * @return Unit
   */
  def debug(message: String): Unit = {
    logger.debug(message)
  }
  
}
