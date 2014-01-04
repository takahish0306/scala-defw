/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.functions.http

import java.util.ArrayList

import org.apache.http.NameValuePair
import org.apache.http.message.BasicNameValuePair

import scala.collection.JavaConversions._
import scala.collection.mutable.{Map => MutableMap}

object Parameter {

  /**
   * Apply
   *
   * @param parameters Map[String, String]
   */
  def apply(parameters: Map[String, String]): ArrayList[NameValuePair] = {
    // to convert into List[BasicNameValuePair]
    val list = for ((name, value) <- parameters) yield new BasicNameValuePair(name, value)
    // to convert into ArrayList[NameValuePair]
    val pairs = list.foldLeft(new ArrayList[NameValuePair])((pairs, pair) => { pairs.add(pair); pairs })

    // return ArrayList[NameValuePair]
    pairs
  }

}
