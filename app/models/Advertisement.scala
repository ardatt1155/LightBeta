package models

import java.util._
import java.text._
import javax.persistence._
import com.avaje.ebean._
import play.api.libs.json._
import play.data.format._
import play.data.validation._

object Advertisement {
    var find: Model.Finder[Integer, Advertisement] = new Model.Finder(classOf[Advertisement])
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
