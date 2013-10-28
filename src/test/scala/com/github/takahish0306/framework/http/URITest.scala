package com.github.takahish0306.framework.http

import org.scalatest._
import java.net.{URI => JavaNetURI}

class URITest extends FlatSpec {
  "URI.apply" should "create url object from http://hc.apache.org/?a=b#c" in {
    val uri = URI("http://hc.apache.org/?a=b#c")
    assert(uri.isInstanceOf[JavaNetURI] === true)
  }

  "URI.unapply" should "create option instance from http://hc.apache.org/?a=b#c" in {
    val uri = URI("http://hc.apache.org/?a=b#c")
    uri match {
      case URI("http://hc.apache.org/?a=b#c") => {
        assert(true === true)
      }
      case _ => assert(false === true)
    }
  }
}
