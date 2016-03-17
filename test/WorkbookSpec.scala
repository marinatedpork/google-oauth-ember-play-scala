package test
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import models.{Workbook, OAuth2}

@RunWith(classOf[JUnitRunner])
class WorkbookSpec extends Specification with SpecHelper {

  "Workbook" should {
    "may be found" in new WithApplication {
      implicit val testCredential = OAuth2("", refreshToken, 0)
      Workbook.find(testWorkbookId).getClass must beTheSameAs (someTestWorkbook.getClass)
    }

    "may be listed" in new WithApplication {
      implicit val testCredential = OAuth2("", refreshToken, 0)
      Workbook.list.getClass must beTheSameAs (someTestWorkbookList.getClass)
    }

    "returns nothing when not authenticated" in new WithApplication {
      implicit val badCredential = OAuth2("", "", 0)
      Workbook.list.getClass must beTheSameAs (nothing.getClass)
    }

    "returns nothing when not authenticated" in new WithApplication {
      implicit val badCredential = OAuth2("", "", 0)
      Workbook.find(testWorkbookId).getClass must beTheSameAs (nothing.getClass)
    }
  }

}
