package test
import scala.concurrent.ExecutionContext.Implicits.global
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

@RunWith(classOf[JUnitRunner])
class OAuth2ControllerSpec extends Specification with SpecHelper {

  "OAuth2Controller" should {
    "redirect to google" in new WithApplication {
      val authenticate = route(FakeRequest(GET, "/startAuth").withSession(authenticatedSession)).get
      status(authenticate) must equalTo(SEE_OTHER)
      redirectLocation(authenticate) must beSome.which(_.contains("https://accounts.google.com/o/oauth2/auth"))
    }
  } 
}
