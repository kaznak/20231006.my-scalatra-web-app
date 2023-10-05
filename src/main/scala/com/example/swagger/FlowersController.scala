package com.example.swagger

import org.scalatra._

// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._

class FlowersController extends ScalatraServlet with NativeJsonSupport {

  // Sets up automatic case class to JSON output serialization
  protected implicit val jsonFormats: Formats = DefaultFormats

  // Before every action runs, set the content type to be in JSON format.
  before() {
    contentType = formats("json")
  }

  /*
   * Retrieve a list of flowers
   */
  get("/") {
    params.get("name") match {
      case Some(name) =>
        FlowerData.all filter (_.name.toLowerCase contains name.toLowerCase())
      case None => FlowerData.all
    }
  }

  /** Find a flower using its slug.
    */
  get("/:slug") {
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
