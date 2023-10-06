package com.example.swagger

import org.scalatra._
import com.example.swagger.FlowersSwagger

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._
import org.scalatra.swagger._

class FlowersController(implicit val swagger: Swagger)
    extends ScalatraServlet
    with NativeJsonSupport
    with SwaggerSupport {

  protected def applicationDescription: String = "The Flowershop API"

  // Sets up automatic case class to JSON output serialization
  protected implicit val jsonFormats: Formats = DefaultFormats

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  /*
   * Retrieve a list of flowers
  get("/") {
   */
  val getFlowers = apiOperation[List[Flower]]("getFlowers")
    .summary("Show all flowers")
    .description(
      "Shows all the flowers in the flower shop. You can search it too."
    )
    .parameter(
      queryParam[Option[String]]("name").description("A name to search for")
    )
  get("/", operation(getFlowers)) {
    params.get("name") match {
      case Some(name) =>
        FlowerData.all filter (_.name.toLowerCase contains name.toLowerCase())
      case None => FlowerData.all
    }
  }

  /** Find a flower using its slug.
    */
  val findBySlug = apiOperation[Flower]("findBySlug")
    .summary("Find a flower by its slug")
    .description("Returns a flower based on its slug")
    .parameter(pathParam[String]("slug").description("Slug of flower to fetch"))
  get("/:slug", operation(findBySlug)) {
    FlowerData.all find (_.slug == params("slug")) match {
      case Some(b) => b
      case None    => halt(404)
    }
  }

}

// A Flower object to use as a faked-out data model
case class Flower(slug: String, name: String)

// An amazing datastore!
object FlowerData {

  /** Some fake flowers data so we can simulate retrievals.
    */
  var all = List(
    Flower("yellow-tulip", "Yellow Tulip"),
    Flower("red-rose", "Red Rose"),
    Flower("black-rose", "Black Rose")
  )
}
