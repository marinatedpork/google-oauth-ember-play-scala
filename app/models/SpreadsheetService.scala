package models

import java.net.URL
import scala.language.reflectiveCalls

import play.api.libs.json._
import play.api.Logger

import com.google.gdata.client.spreadsheet.{SpreadsheetService => GoogleSpreadsheetService}
import com.google.gdata.data.BaseEntry
import com.google.api.client.auth.oauth2.Credential
import com.google.gdata.util.ServiceException

trait SpreadsheetService {

  /**
   * Helper method for constructing a feed url.
   * @param path The path to the resource you're trying to access.
   * @return A url for the given resource's feed.
   */

  protected def feedUrl(path: String): URL = new URL(s"https://spreadsheets.google.com/feeds/$path/private/full")

  /**
   * Helper method for constructing an id for the each resource, since the ids
   * in the Google object can be a bit weird to work with as they are urls.
   * @tparam T     A type that has a method called 'getId' that returns a String
   * @param  gdata The Google Data object (e.g. a spreadsheet or something)
   * @param  key   A dynamic segment to take out.
   * @return A string to be used as an id.
   */

  protected def id[T <: { def getId():String }](gdata: T, key: String): String = gdata
    .getId
    .replace(s"https://spreadsheets.google.com/feeds/$key/", "")

  /**
   * This higher order function is where the implicit credential finally lands
   * after being taken out of the session, refreshed, and passed through the models.
   * It sets the credential on a Google service object and tries to call whatever
   * function passed in, while wrapping it in an Option.
   * @tparam T         The type of resource you're trying to retrieve.
   * @param  f         The function you'd like to attempt with the service
   * @param credential The implicit credential for accessing the resources
   * @return A instance of T wrapped in an option
   */
  protected def get[T](f: GoogleSpreadsheetService => T)(implicit credential: Credential): Option[T] = {
    Logger.info(s"Fecthing resource.")
    val service = new GoogleSpreadsheetService("OAuth Google Ember")
    service.setOAuth2Credentials(credential)
    try {
        Some(f(service))
    } catch {
      case e: ServiceException =>
        Logger.error(s"Caught: Received error while getting resource")
        Logger.error(s"$e, ${e.getResponseBody}")
        None
    }
  }

}
