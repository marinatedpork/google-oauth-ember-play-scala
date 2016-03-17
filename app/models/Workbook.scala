package models

import java.net.URL
import scala.collection.JavaConverters._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import com.google.gdata.client.spreadsheet.{SpreadsheetService => GoogleSpreadsheetService}
import com.google.gdata.data.spreadsheet.{
  WorksheetFeed,
  WorksheetEntry,
  SpreadsheetFeed,
  SpreadsheetEntry
}
import com.google.api.client.auth.oauth2.Credential

case class Workbook(
	id         : String = "",
  title      : String = "",
	worksheets : Option[List[Worksheet]] = None
)

/**
 * The companion object for Workbook with SpreadsheetService
 * methods for accessing Google resources.
 */

object Workbook extends SpreadsheetService {

  implicit val workbookFormat = Json.format[Workbook]

  /**
   * Retrieves a list of workbooks. Workbooks only contain their
   * id and their title.
   * @param credential The credential for accessing the resources.
   * @return An optional list of workbooks
   */

  def list(implicit credential: Credential): Option[List[Workbook]] = {
    val rootUrl: URL = feedUrl("spreadsheets")
    get((service: GoogleSpreadsheetService) => {
      service
        .getFeed(rootUrl, classOf[SpreadsheetFeed])
        .getEntries
        .asScala
        .map(s => Workbook(id(s, "spreadsheets"), s.getTitle.getPlainText))
        .toList
    })
  }

  /**
   * Retrieves a workbook. Loads all the worksheets within it except for their
   * cells.
   * @param bookId The id of the workbook that you want to retrieve.
   * @param credential The credential for accessing the resources.
   * @return An optional workbook.
   */

  def find(bookId: String)(implicit credential: Credential): Option[Workbook] = {
    val url: URL = feedUrl(s"worksheets/$bookId")
    get((service: GoogleSpreadsheetService) => {
      val feed = service.getFeed(url, classOf[WorksheetFeed])
      val title = feed.getTitle.getPlainText
      val worksheets = feed
        .getEntries
        .asScala
        .map(s => Worksheet(id(s, s"worksheets/$bookId"), s.getTitle.getPlainText, bookId))
        .toList
      Workbook(bookId, title, Some(worksheets))
    })
  }
}