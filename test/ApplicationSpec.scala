package test
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification with SpecHelper {

  "Application" should {
    "provide workbooks as JSON" in new WithApplication {
      val workbooks = route(FakeRequest(GET, "/xhr/workbooks").withSession(authenticatedSession)).get
      status(workbooks) must equalTo(OK)
      contentType(workbooks) must beSome.which(x => x == "application/json")
      contentAsJson(workbooks).as[JsObject].keys.contains("workbooks") must beTrue
    }

    "provide a workbook as JSON" in new WithApplication {
      val workbook = route(FakeRequest(GET, s"/xhr/workbooks/$testWorkbookId").withSession(authenticatedSession)).get
      status(workbook) must equalTo(OK)
      contentType(workbook) must beSome.which(x => x == "application/json")
      contentAsJson(workbook).as[JsObject].keys.contains("workbook") must beTrue
    }

    "provide a worksheet as JSON" in new WithApplication {
      val worksheet = route(FakeRequest(GET, s"/xhr/workbooks/$testWorkbookId/worksheets/$testWorksheetId").withSession(authenticatedSession)).get
      status(worksheet) must equalTo(OK)
      contentType(worksheet) must beSome.which(x => x == "application/json")
      contentAsJson(worksheet).as[JsObject].keys.contains("worksheet") must beTrue
    }

    "provide a error as JSON" in new WithApplication {
      val worksheet = route(FakeRequest(GET, s"/xhr/workbooks/nonExistentId/worksheets/nonExistentId").withSession(authenticatedSession)).get
      status(worksheet) must equalTo(OK)
      contentType(worksheet) must beSome.which(x => x == "application/json")
      contentAsJson(worksheet).as[JsObject].keys.contains("errors") must beTrue
    }
  }
  
}
