package models

import java.net.URL
import scala.collection.JavaConverters._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import com.google.api.client.auth.oauth2.Credential
import com.google.gdata.data.spreadsheet.{
  WorksheetFeed,
  WorksheetEntry,
  CellFeed,
  CellEntry
}
import com.google.gdata.client.spreadsheet.{
  SpreadsheetService => GoogleSpreadsheetService
}

case class Worksheet(
  id         : String = "",
  title      : String = "",
  workbook   : String = "",
  cells      : Option[List[Cell]] = None
)

/**
 * The companion object for Worksheet with SpreadsheetService
 * methods for accessing Google resources.
 */

object Worksheet extends SpreadsheetService {

  implicit val worksheetFormat = Json.format[Worksheet]

  /**
   * Retrieves a worksheet. Loads all cells.
   * @param bookId     The id of the parent workbook for the
   *                   worksheet you want to retrieve.
   * @param sheetId    The id of the worksheet that you want to retrieve.
   * @param credential The credential for accessing the resources.
   * @return An optional worksheet.
   */

  def find(bookId: String, sheetId: String)(implicit cred: Credential): Option[Worksheet] = {
    val url = feedUrl(s"cells/$bookId/$sheetId")
    get((service: GoogleSpreadsheetService) => {
      val feed = service.getFeed(url, classOf[CellFeed])
      val title = feed.getTitle.getPlainText
      val cells = feed
        .getEntries
        .asScala
        .map { cell =>
          val c = cell.getCell
          Cell(cell.getId, bookId, sheetId, c.getRow, c.getCol, c.getValue)
        }
        .toList
      Worksheet(sheetId, title, bookId, Some(cells))
    })
  }
}
