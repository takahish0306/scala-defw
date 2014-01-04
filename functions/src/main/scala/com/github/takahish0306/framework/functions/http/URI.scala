/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.functions.http

import java.net.{URI => JavaNetURI}

object URI {

  /**
   * Apply
   *
   * @param uri String
   * @return java.net.URL
   */
  def apply(uri: String): JavaNetURI = {
    new JavaNetURI(uri)
  }

}
