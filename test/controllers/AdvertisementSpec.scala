
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class Advertisement extends Specification {

  "Advertisement" should {
    "gracefully handle bad parameter in show" in new WithApplication{
      val show = route(FakeRequest(GET, "/advertisements/show?sort=fizz")).get
      status(show) must equalTo(400)
      contentAsString(show) must contain ("Not ok")
    }
  }

  "Advertisement" should {
    "gracefully handle bad parameter in get" in new WithApplication{
      val show = route(FakeRequest(GET, "/advertisements/-1")).get
      status(show) must equalTo(400)
      contentAsString(show) must contain ("Not ok")
    }
  }
}
