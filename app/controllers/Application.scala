package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.Play.current
import play.api.libs.functional.syntax._
import models.{OAuth2, Workbook, Worksheet, Cell}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import com.google.api.client.auth.oauth2.Credential
import services.AuthenticatedRequest

/**
 * This Application controller acts as the API for the Ember Data Adapter.
 * Each method serves up a Google resource, and starts with the request
 * implicitly passing into authenticatedRequest. Then, an implicit credential
 * is returned and is passed implictly through the model and into the service.
 * If the request fails an error is returned to Ember.
 */

class Application extends Controller with ServiceError with AuthenticatedRequest {

  /**
   * Renders the index view and starts the show.
   * @param path  This is a catch all parameter for url persistence in Ember.
   * @return an HTML view.
   */

  def index(path: String) = Action { implicit request =>
    Ok(views.html.index())
  }

  /**
   * Responds to GET /xhr/workbooks.
   * @return a JSON formatted list of workbooks.
   */

  def workbooks = Action.async { implicit request =>
    Future(Ok(authenticatedRequest { implicit cred =>
      Workbook.list match {
        case Some(workbooks) => Json.obj("workbooks" -> workbooks)
        case None => SERVICE_ERROR
      }
    }))
  }

  /**
   * Responds to GET /xhr/workbooks/:workbookId.
   * @param workbookId the ID of the workbook to find.
   * @return a JSON formatted workbook.
   */

  def workbook(workbookId: String) = Action.async { implicit request =>
    Future(Ok(authenticatedRequest { implicit cred =>
      Workbook.find(workbookId) match {
        case Some(workbook) => Json.obj("workbook" -> workbook)
        case None => SERVICE_ERROR
      }
    }))
  }

  /**
   * Responds to GET /xhr/workbooks/:workbookId/worksheets/:worksheetId.
   * @param workbookId   the ID of the workbook that the worksheet belongs to.
   * @param worksheetId  the ID of the worksheet to find.
   * @return a JSON formatted worksheet.
   */

  def worksheet(workbookId: String, worksheetId: String) = Action.async { implicit request =>
    Future(Ok(authenticatedRequest { implicit cred =>
      Worksheet.find(workbookId, worksheetId) match {
        case Some(worksheet) => Json.obj("worksheet" -> worksheet)
        case None => SERVICE_ERROR
      }
    }))
  }

}
