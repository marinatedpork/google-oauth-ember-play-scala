package test
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import models.{Worksheet, OAuth2, Cell}

@RunWith(classOf[JUnitRunner])
class CellSpec extends Specification with SpecHelper {

  "Cell" should {
    "may be found in a Worksheet query" in new WithApplication {
			implicit val testCredential = OAuth2("", refreshToken, 0)
			Worksheet
				.find(testWorkbookId, testWorksheetId)
				.getOrElse(Worksheet())
				.cells
				.getClass must beTheSameAs (someTestCells.getClass)
    }

    "has a row" in new WithApplication {
			implicit val testCredential = OAuth2("", refreshToken, 0)
			Worksheet
				.find(testWorkbookId, testWorksheetId)
				.getOrElse(Worksheet())
				.cells
				.getOrElse(Nil)
				.headOption match {
					case Some(head) => head.row.getClass must beTheSameAs (testInt.getClass)
					case None => false
				}
		} 

    "has a column" in new WithApplication {
			implicit val testCredential = OAuth2("", refreshToken, 0)
			Worksheet
				.find(testWorkbookId, testWorksheetId)
				.getOrElse(Worksheet())
				.cells
				.getOrElse(Nil)
				.headOption match {
					case Some(head) => head.column.getClass must beTheSameAs (testInt.getClass)
					case None => false
				}
		} 

    "has a value" in new WithApplication {
			implicit val testCredential = OAuth2("", refreshToken, 0)
			Worksheet
				.find(testWorkbookId, testWorksheetId)
				.getOrElse(Worksheet())
				.cells
				.getOrElse(Nil)
				.headOption match {
					case Some(head) => head.value.getClass must beTheSameAs (testValue.getClass)
					case None => false
				}
		} 
	}
}
