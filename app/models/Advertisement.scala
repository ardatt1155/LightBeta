package models

import java.util._
import javax.persistence._
import com.avaje.ebean._
import play.data.format._
import play.data.validation._

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
}
