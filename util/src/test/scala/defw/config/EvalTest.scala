package defw.config

import org.scalatest._
import defw.io.File

class EvalTest extends FlatSpec with Eval {

  "Eval.load" should "input from ./util/src/test/resources/defw/config/Test.scala" in {
    val file = File("./util/src/test/resources/defw/config/Test.scala")
    val config = load[Test](file)
    assert(config.get.value1 === "test1")
    assert(config.get.value2 === 1024)
    assert(config.get.value3.value4 === "test2")
    assert(config.get.value3.value5 === "test3")
  }

}
