package com.example.swagger

import org.scalatra.ScalatraServlet
import org.scalatra.swagger._

class ResourcesApp(implicit val swagger: Swagger)
    extends ScalatraServlet
    with NativeSwaggerBase

object FlowersApiInfo
    extends ApiInfo(
      "The Flowershop API",
      "Docs for the Flowers API",
      "http://scalatra.org",
      ContactInfo(
        "Scalatra API Team",
        "http://scalatra.org",
        "apiteam@scalatra.org"
      ),
      LicenseInfo(
        "MIT",
        "http://opensource.org/licenses/MIT"
      )
    )

class FlowersSwagger
    extends Swagger(Swagger.SpecVersion, "1.0.0", FlowersApiInfo)
