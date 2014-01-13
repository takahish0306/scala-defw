package defw.json

import org.scalatest._
import java.io._

class LiftWebJSONTest extends FlatSpec with LiftWebJSON {
  "LiftWebJSON.encode" should "create a JSON string from a scala object" in {
    val m = Map("name" -> "Takahiro Ishikawa", "country" -> "Tokyo")
    val result = encode(m)
    assert(result.get == "{\"name\":\"Takahiro Ishikawa\",\"country\":\"Tokyo\"}")
  }

  "LiftWebJSON.decode" should "create a simple scala object from a JSON string" in {
    val s = "{\"name\":\"Takahiro Ishikawa\",\"country\":\"Tokyo\"}"
    val result = decode[Map[String, String]](s)
    assert((result.get)("name") === "Takahiro Ishikawa")
    assert((result.get)("country") === "Tokyo")
  }
}
