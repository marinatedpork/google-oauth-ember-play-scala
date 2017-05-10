package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import com.google.gdata.data.spreadsheet.CellEntry

case class Cell(
  id        : String = "",
  workbook  : String = "",
  worksheet : String = "",
  row       : Int = 0,
  column    : Int = 0,
  value     : String = ""
)

/**
 * Companion object for the Cell.
 * Just contains an implicit JSON formatter.
 */

object Cell {

  implicit val cellFormat = Json.format[Cell]

}
