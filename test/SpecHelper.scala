package test
import models.{Workbook, Worksheet, Cell, OAuth2}

trait SpecHelper {

	val testInt               : Int  = 1
	val testValue             : String = "A String"
	val nothing               : Option[Nothing] = None
	val testWorkbookId        : String = System.getenv("TEST_WORKBOOK")
	val testWorksheetId       : String = System.getenv("TEST_WORKSHEET")
	val refreshToken          : String = System.getenv("TEST_REFRESH")
	val testWorksheet         : Worksheet = Worksheet()
	val testWorkbook          : Workbook = Workbook()
	val someTestWorkbook      : Option[Workbook] = Some(Workbook())
	val someTestWorksheet     : Option[Worksheet] = Some(Worksheet())
	val someTestWorkbookList  : Option[List[Workbook]] = Some(List.empty[Workbook])
	val someTestWorksheetList : Option[List[Worksheet]] = Some(List.empty[Worksheet])
	val someTestCells         : Option[List[Cell]] = Some(List.empty[Cell])
	val authenticatedSession = (
		"refreshToken" -> refreshToken
	)

}