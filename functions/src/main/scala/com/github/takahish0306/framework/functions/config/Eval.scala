/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.functions.config

import java.io.{File => JavaIoFile}
import com.twitter.util.{Eval => TwitterUtilEval}
import com.github.takahish0306.framework.functions.log.Logger

trait Eval extends Logger {

  /**
   * To get config from file by using twitter util eval
   *
   * @param file JavaIoFile
   * @return Option[T]
   */
  def load[T](file: JavaIoFile): Option[T] = {
    val exceptionMessage = "! Eval.load failed."

    try {
      Some(TwitterUtilEval[T](file))
    } catch {
      case e: Exception => {
        logger.error(exceptionMessage + " A problem occured.")
        logger.error("StackTrace:\n" + e.getStackTrace.mkString("\n"))
        None
      }
    }
  }

}
