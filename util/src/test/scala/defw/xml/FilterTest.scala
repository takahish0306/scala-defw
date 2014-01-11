package defw.xml

import org.scalatest._
import scala.xml.XML
import Filter._

class FilterTest extends FlatSpec {

  "\\@" should "filter xml node from <sample><node name=\"value\">test</node></sample>" in {
    val xmlString = "<sample><node name=\"value\">test</node></sample>"
    val xml = XML.loadString(xmlString)
    assert((xml \@ "node[name=value]").text === "test")
  }

  "\\\\@" should "filter xml node from <sample><node><nested_node name=\"value\">test<nested_node></node></sample> recursively" in {
    val xmlString = "<sample><node><nested_node name=\"value\">test</nested_node></node></sample>"
    val xml = XML.loadString(xmlString)
    assert((xml \\@ "nested_node[name=value]").text === "test")
  }

}
