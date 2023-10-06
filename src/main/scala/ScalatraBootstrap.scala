import com.example.app._
import org.scalatra._
import javax.servlet.ServletContext
import com.example.swagger._

class ScalatraBootstrap extends LifeCycle {

  implicit val swagger: FlowersSwagger = new FlowersSwagger

  override def init(context: ServletContext) {
    context.mount(new MyScalatraServlet, "/*")
    context.mount(
      new FlowersController,
      "/flowers",
      "flowers"
    ) // the 3rd arg must be the same as the mount path without the leading slash.
    context.mount(new ResourcesApp, "/api-docs")
  }
}
