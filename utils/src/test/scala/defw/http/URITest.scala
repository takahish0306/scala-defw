package defw.http

import org.scalatest._
import java.net.{URI => JavaNetURI}

class URITest extends FlatSpec {

  "URI.apply" should "create uri object from http://hc.apache.org/?a=b#c" in {
    val uri = URI("http://hc.apache.org/?a=b#c")
    assert(uri.isInstanceOf[JavaNetURI] === true)
  }

}
