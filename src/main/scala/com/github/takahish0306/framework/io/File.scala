/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.io

import java.io.{File => FileObject}
import org.apache.commons.io.FilenameUtils

object File {

  /**
   * Apply
   *
   * @param filepath String
   * @return java.io.File
   */
  def apply(filepath: String): FileObject = {
    new FileObject(FilenameUtils.normalize(filepath))
  }

  /**
   * Unapply
   *
   * @param fileObject java.io.File
   * @return Option[String]
   */
  def unapply(fileObject: FileObject): Option[String] = {
    Some(fileObject.getAbsolutePath)
  }

}
