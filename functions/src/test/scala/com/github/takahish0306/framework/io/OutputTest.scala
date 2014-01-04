package com.github.takahish0306.framework.functions.io

import org.scalatest._
import java.io._

class OutputTest extends FlatSpec with Output {
  "Output.withFileOutputStream" should "output to ./functions/src/test/resources/com/github/takahish0306/framework/functions/io/output.txt" in {
    var result: Boolean = false
    val filepath = "./functions/src/test/resources/com/github/takahish0306/framework/functions/io/output.txt"
    val lines    = List("line1", "line2", "line3", "line4", "line5")
    withFileOutputStream[Unit](filepath) {
      stream => {
        lines.foreach(line => {
          val bytes = (line + "\n").getBytes
          for (byte <- bytes) stream.write(byte)
        })
      }
    } match {
      case Some(v) => result = true
      case None    => result = false
    }
    assert(result === true)
  }

  // Error log output
  "Output.withFileOutputStream" should "throw exception opening /test/dummy.txt" in {
    var result: Boolean = false
    val filepath = "/test/dummy.txt"
    withFileOutputStream[Unit](filepath) {
      stream => {}
    } match {
      case Some(v) => result = true
      case None    => result = false
    }
    assert(result === false)
  }
}
