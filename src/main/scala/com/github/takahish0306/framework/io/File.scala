/**
 *
 * @author takahish0306
 *
 */

package com.github.takahish0306.framework.io

import java.io.{File => FileObject}
import org.apache.commons.io.FilenameUtils

object File {

  /**
   * apply
   *
   * @param filepath String
   * @return java.io.File
   */
  def apply(filepath: String): FileObject = {
    new FileObject(FilenameUtils.normalize(filepath))
  }

  /**
   * unapply
   *
   * @param fileObject java.io.File
   * @return Option[String]
   */
  def unapply(fileObject: FileObject): Option[String] = {
    Some(fileObject.getAbsolutePath)
  }

}