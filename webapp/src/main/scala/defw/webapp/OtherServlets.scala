package defw.webapp

import org.scalatra._
import scalate.ScalateSupport

class StockServlet extends ScalatraWebAppStack {
  get("/") {
    <p>Hello from StockServlet</p>
  }

  get("/foo") {
    <p>Hello from StockServlet, Foo!</p>
  }
}

class BondServlet extends ScalatraWebAppStack {
  get("/") {
    <p>Hello from BondServlet</p>
  }
}
