import com.github.takahish0306.framework.webapp._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    // created by default
    context.mount(new WebAppServlet, "/*")

    // new
    context.mount(new StockServlet, "/stocks/*")
    context.mount(new BondServlet, "/bonds/*")
  }
}
