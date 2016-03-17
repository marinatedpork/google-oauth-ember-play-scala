package test
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import models.{Worksheet, OAuth2, Cell}

@RunWith(classOf[JUnitRunner])
class WorksheetSpec extends Specification with SpecHelper {

  "Worksheet" should {
    "may be found" in new WithApplication {
			implicit val testCredential = OAuth2("", refreshToken, 0)
			Worksheet.find(testWorkbookId, testWorksheetId).getClass must beTheSameAs (someTestWorksheet.getClass)
    }

    "has a list of cells" in new WithApplication {
			implicit val testCredential = OAuth2("", refreshToken, 0)
			Worksheet
				.find(testWorkbookId, testWorksheetId)
				.getOrElse(Worksheet())
				.cells.getClass must beTheSameAs (someTestCells.getClass)
    }

    "returns nothing when not authenticated" in new WithApplication {
			implicit val badCredential = OAuth2("", "", 0)
			Worksheet.find(testWorkbookId, testWorksheetId).getClass must beTheSameAs (nothing.getClass)
    }
  }

}
