package com.github.takahish0306.framework.io

import org.scalatest._
import java.io._

class InputTest extends FlatSpec with Input {
  "Input.withFileInputStream" should "input from ./functions/src/test/resources/com/github/takahish0306/framework/io/input.txt" in {
    val filepath = "./functions/src/test/resources/com/github/takahish0306/framework/io/input.txt"
    val result = withFileInputStream[String](filepath){
      stream => {
        val bufferedReader = new BufferedReader(new InputStreamReader(stream))
        bufferedReader.readLine
      }
    } match {
      case Some(v) => v.asInstanceOf[String]
      case None    => "File dose not exist."
    }
    assert(result === "line1")
  }

  // Error log output
  "Input.withFileInputStream" should "throw exception /test/dummy.txt" in {
    val filepath = "/test/dummy.txt"
    val result = withFileInputStream[String](filepath){
        stream => "Not to evaluete."
    } match {
      case Some(v) => v
      case None    => "File dose not exist."
    }
    assert(result === "File dose not exist.")
  }
}
