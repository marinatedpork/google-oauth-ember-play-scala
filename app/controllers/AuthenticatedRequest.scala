package services
import models.OAuth2
import com.google.api.client.auth.oauth2.Credential
import play.api._
import play.api.mvc._
import play.api.libs.json._

trait AuthenticatedRequest {

  /**
   * Some generic JSON to return to the front end when we get an error
   */

  val AUTH_ERROR = Json.obj(
    "errors" -> Json.obj(
      "authentication" -> Json.arr(JsString("You're not authenticated. Please authenticate"))
    )
  )

  /**
   * Tries to pull out some of the credential from the session, and then pass it
   * into another function is it is successfully extracted. This is meant to
   * remove some of the boiler plate from constantly getting and refreshing it.
   * @tparam T The type of request. This is really just here to make the compiler
   *           happy.
   * @param  f A function to pass the credential into if it is successfully retrieved.
   * @return A JSON object that is to be sent to the front end.
   */

  def authenticatedRequest[T](f: Credential => JsObject)(implicit request: Request[T]): JsObject = {
    OAuth2.session match {
      case Some(cred) =>
        implicit val crededential = cred
        f(crededential)
      case None =>
        Logger.error("Unable to retrieve refreshToken from session. Please authenticate.")
        AUTH_ERROR
    }
  }

}
