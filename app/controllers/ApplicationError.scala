package controllers

import play.api.libs.json._

trait ServiceError {

  /**
   * JSON returned to the front end when an error is caught.
   */

  val SERVICE_ERROR = Json.obj(
    "errors" -> Json.obj(
      "authentication" -> Json.arr(JsString("You're not authenticated. Please authenticate"))
    )
  )

}
