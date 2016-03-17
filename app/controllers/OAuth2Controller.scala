package controllers

import scala.collection.JavaConverters._

import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.i18n.{Messages, MessagesApi, I18nSupport}

import javax.inject.Inject

import com.google.api.client.googleapis.auth.oauth2.{
	GoogleTokenResponse,
	GoogleAuthorizationCodeRequestUrl
}

import models.OAuth2

class OAuth2Controller @Inject()(val messagesApi:MessagesApi) extends Controller with I18nSupport {

	/**
	 * Redirect URI to tell Google where to send people
	 * after the OAuth process.
	 */
	
	private val REDIRECT_URI: String = current.configuration.getStringList("google-oauth-2.web.redirect_uris") match {
	  case Some(redirectUri) => 
	    Logger.info("Successfully found OAuth 2.0 redirect uri.")
	    redirectUri
	    	.asScala
	    	.toList
	    	.head
	  case None =>
	    Logger.error("Error finding OAuth 2.0 redirect uri.")
	    ""
	}

  /**
   * Responds to GET /startAuth
   * This method builds a redirect url out of the OAuth credentials
   * and sends the user to it. Once they authenticate from there, 
   * they will be sent to the OAuth2Controller.authenticate method.
   * @return A redirect
   */

  def redirect = Action { implicit request =>
    Logger.info(s"Redirecting to Google Authentication page.")
		val authUrl = OAuth2.flow
		  .newAuthorizationUrl
		  .setRedirectUri(REDIRECT_URI)
		  .build
    Redirect(authUrl)
  }

  /**
   * Responds to GET /authenticate
   * If the authentication is successful a code, which is used 
   * for the final step of authentication, is sent to this method.
   * The code is used in a second request between this server and 
   * Google's server to verify that indeed this is the server that
   * the user has authenticated to view their information.
   *
   * The response from the second request contains an access tolken
   * that is valid for 30 minutes, as well as a refresh tolken that
   * is used to get more access tolkens.
   *
   * Since this is an example, we just stash the tolkens into the 
   * session and get on with the show.
   * @return A redirect
   */

  def authenticate = Action { implicit request =>
    val code: String = request.queryString("code").toList(0)
    Logger.info(s"Auth callback received with code: $code")
    Logger.info("Excuting request now...")
		val tokenRequest = OAuth2.flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI)
		val tokenResponse = tokenRequest.execute
		val credential = OAuth2.flow.createAndStoreCredential(tokenResponse, null)
    Logger.info(s"Success. Response received with refresh token: ${credential.getRefreshToken}")
		Redirect("/workbooks").withSession(
			"accessToken"  -> credential.getAccessToken,
			"refreshToken" -> credential.getRefreshToken, 
			"expiresAt"    -> credential.getExpiresInSeconds.toString
		)
  } 
}