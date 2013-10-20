package com.github.takahish0306.framework.io

import org.scalatest._
import java.io._

class OutputTest extends FlatSpec with Output {
  "Output.withOutputStream" should "output to src/test/resources/com/github/takahish0306/framework/io/output.txt" in {
    var result: Boolean = false
    val filepath = "src/test/resources/com/github/takahish0306/framework/io/output.txt"
    val lines    = List("line1", "line2", "line3", "line4", "line5")
    withOutputStream[Unit](filepath) {
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
  "Output.withOutputStream" should "throw exception opening /test/dummy.txt" in {
    var result: Boolean = false
    val filepath = "/test/dummy.txt"
    withOutputStream[Unit](filepath) {
      stream => {}
    } match {
      case Some(v) => result = true
      case None    => result = false
    }
    assert(result === false)
  }
}