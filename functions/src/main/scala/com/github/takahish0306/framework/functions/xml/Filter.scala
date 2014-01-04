/**
 *
 * @author takahish0306
 *
 * This code is open source software licensed under the Apache Software License.
 * For more details, see LICENSE
 *
 */

package com.github.takahish0306.framework.functions.xml

import scala.xml.NodeSeq

object Filter {

  /**
   * Valid attributes
   */
  val r = """^([a-zA-Z_]+)\[([a-zA-Z_]+)=([a-zA-Z_]+)\]$""".r

  class AttributeFilter(node: NodeSeq) {
    /**
     * To filter by attribute
     *
     * @param name String
     * @param value String
     * @return scala.xml.NodeSeq
     */
    def attributeFilter(name: String, value: String): NodeSeq = {
      node filter (_ \ ("@" + name) exists (_.text == value))
    }

    /**
     * To filter by valid attribute
     *
     * @param attribute String
     * @return scala.xml.NodeSeq
     */
    def \@ (attribute: String): NodeSeq = {
      r.findFirstMatchIn(attribute) match {
        case Some(m) => node \ m.group(1) attributeFilter (m.group(2), m.group(3))
        case None    => node \ attribute
      }
    }

    /**
     * To filter by valid attribute recursively
     *
     * @param attribute String
     * @return scala.xml.NodeSeq
     */
    def \\@ (attribute: String): NodeSeq = { 
      r.findFirstMatchIn(attribute) match { 
        case Some(m) => node \\ m.group(1) attributeFilter (m.group(2), m.group(3))
        case None    => node \\ attribute
      }
    }
  }

  /**
   * Implicit def
   */
  implicit def nodeSeqToAttributeFilter(nodeSeq: NodeSeq): AttributeFilter = new AttributeFilter(nodeSeq)
}
