
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json._

import models._

@RunWith(classOf[JUnitRunner])
class AdvertisementSpec extends Specification {

  "Advertisement" should {
    "toJson should be correct" in new WithApplication {
      val json: JsValue = Json.parse("""
        {
          "title" : "SampleTest",
          "price" : 27000,
          "mileage" : 10,
          "registration" : "03/03/1993",
          "state" : true,
          "fuel" : "Diesel"
        }
        """
      )
      val mA = Advertisement.fromJson(json)
      mA.uid = 666
      val result = mA.toJson
      val mB = Advertisement.fromJson(json)
      mA.title must equalTo(mB.title)
    }
  }
}
