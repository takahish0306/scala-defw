package defw.http

import org.scalatest._
import java.util.ArrayList
import org.apache.http.NameValuePair

class ParameterTest extends FlatSpec {

  "Parameter.apply" should "create parameter object from Map(\"key1\" -> \"value1\", \"key2\" -> \"value2\")" in {
    val parameter = Parameter(Map("key" -> "value"))
    assert(parameter.isInstanceOf[ArrayList[NameValuePair]] === true)
  }

}
