package defw.io

import org.scalatest._
import java.io._

class IOTest extends FlatSpec with Input {
  "IO.read" should "read from ./utils/src/test/resources/defw/io/input.txt" in {
    val filepath = "./utils/src/test/resources/defw/io/input.txt"
    val result = IO.read(filepath)
    assert(result.get === "line1line2line3line4line5")
  }

  "IO.write" should "write to ./utils/src/test/resources/defw/io/output.txt" in {
    val filepath = "./utils/src/test/resources/defw/io/output.txt"
    IO.write(filepath, "line1\nline2\nline3\nline4\nline5")
  }
}
