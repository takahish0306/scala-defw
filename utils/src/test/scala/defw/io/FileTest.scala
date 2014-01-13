package defw.io

import org.scalatest._
import java.io.{File => FileObject}

class FileTest extends FlatSpec {
  "File.apply" should "create file object from /Users/Guest" in {
    val file = File("/Users/Guest")
    assert(file.isInstanceOf[FileObject] === true)
  }

  "File.unapply" should "create option istance from /Users/Guest" in {
    val file = File("/Users/Guest")
    file match {
      case File("/Users/Guest") => {
        assert(true === true)
      }
      case _ => assert(false === true)
    }
  }
}
