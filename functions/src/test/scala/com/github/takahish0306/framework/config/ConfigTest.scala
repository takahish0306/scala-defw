package com.github.takahish0306.framework.config

import org.scalatest._

class ConfigTest extends FlatSpec with Eval {

  "Config.load" should "input from ./functions/src/test/resources/com/github/takahish0306/framework/config/Test.scala" in {
    val filepath = "./functions/src/test/resources/com/github/takahish0306/framework/config/Test.scala"
    val config = Config.load[Test](filepath)
    assert(config.get.value1 === "test1")
    assert(config.get.value2 === 1024)
    assert(config.get.value3.value4 === "test2")
    assert(config.get.value3.value5 === "test3")
  }

}
