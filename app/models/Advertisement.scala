package models

import java.util._
import java.text._
import javax.persistence._
import com.avaje.ebean._
import play.api.libs.json._
import play.data.format._
import play.data.validation._
import scala.collection.JavaConversions._

object Advertisement {
  var find: Model.Finder[Integer, Advertisement] = new Model.Finder(classOf[Advertisement])
    
  @throws(classOf[Exception])
  def fromJson(json: JsValue) : Advertisement = {
    val df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    val title = (json \ "title").asOpt[String]
    val price = (json \ "price").asOpt[Int]
    val mileage = (json \ "mileage").asOpt[Int]
    val registration = (json \ "registration").asOpt[String]
    val state = (json \ "state").asOpt[Boolean]

    val date = df.parse(registration.get)
    val check = (title.getOrElse(null) == null || price.getOrElse(null) == null || mileage.getOrElse(null) == null || state.getOrElse(null) == null)
    if (check == true) throw new IllegalArgumentException

    val model = new Advertisement
    model.title = title.get
    model.price = price.get
    model.mileage = mileage.get
    model.state = state.get
    model.registration = date
    return model
  }

  def toJson(models: List[Advertisement]) : JsValue = {
    val flats = models.map { model => model.toJson }
    val response = Json.obj("advertisements" -> JsArray(flats))
    return response;
  }
}

@Entity
class Advertisement extends Model {

  @Id
  @GeneratedValue
  var uid: java.lang.Long = _

  @Constraints.Required
  @Constraints.MaxLength(value = 256)
  var title: String = _

  @Constraints.Required
  @Constraints.Min(0)
  var price: Int = _

  @Constraints.Required
  var state: Boolean = _

  @Constraints.Min(0)
  var mileage: Int = _

  @Constraints.Required
  @Formats.DateTime(pattern = "dd/MM/yyyy")
  var registration: Date = new Date()

  def toJson : JsValue = {
    val df = new SimpleDateFormat("dd/MM/yyyy")
    val result: JsValue = Json.obj(
      "uid" -> JsNumber(BigDecimal(this.uid)),
      "title" -> JsString(this.title),
      "price" -> JsNumber(BigDecimal(this.price)),
      "state" -> JsBoolean(state),
      "mileage" -> JsNumber(BigDecimal(this.mileage)),
      "registration" -> JsString(df.format(this.registration))
    )
    return result
  }
}
