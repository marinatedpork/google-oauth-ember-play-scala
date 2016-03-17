package models

import java.io.InputStreamReader
import java.io.ByteArrayInputStream

import play.api.Play.current
import play.api.libs.json._
import play.api.Logger
import play.api.mvc.Request
import com.typesafe.config.ConfigRenderOptions

import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.googleapis.auth.oauth2.{GoogleClientSecrets, GoogleAuthorizationCodeFlow, GoogleRefreshTokenRequest, GoogleTokenResponse}
import com.google.api.client.auth.oauth2.Credential

/**
 * The OAuth2 object contains all methods and value necessary for communicating
 * with Google's OAuth end points. This baby is definitely a bit more raw than
 * the rest of the application, as it is doing a lot of the config work.
 */

object OAuth2 {
  
  /**
   * retrieves config object from application conf as a
   * JSON Object.
   */
  private val config: JsObject = current.configuration.getObject("google-oauth-2") match {
    case Some(configObj) => 
      Logger.info("Successfully found OAuth 2.0 configuration")
      Json.parse(
        configObj.render(
          ConfigRenderOptions.concise()
        )
      ).as[JsObject]
    case None =>
      Logger.error("Error finding OAuth 2.0 configuration")
      Json.obj()
  }

  /**
   * Serverside credentials for refresh process.
   */
  private val CLIENT_ID     : String = (config \\ "client_id").head.as[String]
  private val CLIENT_SECRET : String = (config \\ "client_secret").head.as[String]


  /**
   * Scopes, JSON Factory, and HTTP Transport for token
   * request process.
   * 
   * SCOPES: The one thing that you might have to
   * change, should you want to access different services
   * that require OAuth.
   */
  private val JSON_FACTORY  = JacksonFactory.getDefaultInstance
  private val httpTransport = GoogleNetHttpTransport.newTrustedTransport
  private val SCOPES        = java.util.Arrays.asList(
    "https://docs.google.com/feeds",
    "https://spreadsheets.google.com/feeds"
  )

  /**
   * Transforming config object into a InputStream, reads it in, and loads
   * the object as a GoogleClientSecrets objects.
   */
  private val configBytes   = new ByteArrayInputStream(Json.stringify(config).getBytes)
  private val configStream  = new InputStreamReader(configBytes)
  private val secrets: GoogleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, configStream)

  /**
   * This is the thing that actually communicates with authentication
   * severs and returns tokens. It is where all the magic happens.
   * 
   * SCOPES: The one thing that you might have to change, should we
   * want to access different services that require OAuth.
   */
  val flow = new GoogleAuthorizationCodeFlow.Builder(
    httpTransport,
    JSON_FACTORY,
    secrets,
    SCOPES
  ).setAccessType("offline").build

  /**
   * Takes raw credential data as basic datatypes and returns a Google
   * Credential object composed of the raw credentials.
   * 
   * @param accessToken   Temporary token retrieved via the refresh token.
   *                      This token is what actually allows us access.
   * @param refreshToken  Permanent token retrieved at the time of initial
   *                      authentication. This is what we use to retrieve
   *                      new access tokens.
   * @param expiresAt     All tokens expires after 3600 seconds.
   * @return A Google credential object for accessing client services.
   */
  def apply(accessToken: String, refreshToken: String, expiresAt: Long): Credential = {
    val tokenPart = new GoogleTokenResponse()
    val tokenPartOne = tokenPart.setAccessToken(accessToken)
    val tokenPartTwo = tokenPartOne.setExpiresInSeconds(expiresAt)
    val tokenPartThree = tokenPartTwo.setRefreshToken(refreshToken)
    flow.createAndStoreCredential(tokenPartThree, null)
  }

  /**
   * Refreshes a credential. Returns an empty one if the refresh process fails.
   * @param cred The expired Credential.
   * @return A fresh Credential.
   */
  def refresh(cred: Credential): Credential = {
    val refreshRequest = new GoogleRefreshTokenRequest(
      httpTransport,
      JSON_FACTORY,
      cred.getRefreshToken,
      CLIENT_ID,
      CLIENT_SECRET
    )
    try { 
      val tokenResponse = refreshRequest.execute
      flow.createAndStoreCredential(tokenResponse, null)
    } catch {
      case e: Exception =>
        Logger.error("Unable to refresh session token. Please re-authenticate.")
        Logger.error(s"${e.getMessage}")
        apply("", "", 0)
    }
  }

  /**
   * Tries to extract the tokens from the session and make a fresh credential.
   * @tparam T        The type of request. This parameter is really just here
   *                  to make the compiler happy.
   * @param  request  The request that is passed in from the Application controller.
   * @return A optional fresh credential.
   */
  def session[T](implicit request: Request[T]): Option[Credential] = request.session.get("refreshToken") match {
    case Some(refreshToken) => Some(refresh(apply("", refreshToken, 0)))
    case None => None
  }
  
}
