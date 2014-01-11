package defw.webapp

import org.scalatra._
import scalate.ScalateSupport

class WebAppServlet extends ScalatraWebAppStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  /**
   * Accessing scalatra web service GET parameters
   * To use a named parameters approch
   */
  get("/hello/:name/") {
    <p>Hello, {params("name")}</p>
  }

  /**
   * Accessing scalatra web service GET parameters
   * To use wildcard charactors
   */
  get("/file/*.*") {
    val data = multiParams("splat")
    <p>{data.mkString("[", ",", "]")}</p>
  }

}
