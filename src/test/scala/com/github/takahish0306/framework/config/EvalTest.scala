package com.github.takahish0306.framework.config

import org.scalatest._
import com.github.takahish0306.framework.io.File

class EvalTest extends FlatSpec with Eval {

  "Eval.load" should "input from ./src/test/resources/com/github/takahish0306/framework/config/Test.scala" in {
    val file = File("./src/test/resources/com/github/takahish0306/framework/config/Test.scala")
    val config = load[Test](file)
    assert(config.get.value1 === "test1")
    assert(config.get.value2 === 1024)
    assert(config.get.value3.value4 === "test2")
    assert(config.get.value3.value5 === "test3")
  }

}

trait Test {
  val value1: String
  val value2: Int
  case class Value3 (value4: String, value5: String)
  val value3: Value3
}
