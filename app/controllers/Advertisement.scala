package controllers

import models.{Advertisement => AdvertisementModel}
import play.api.Play.current
import play.api._
import play.api.libs.json._
import play.api.mvc._
import java.util._
import java.text._

class Advertisement extends Controller {

  def show = Action { request =>
  	val sort = request.queryString.get("sort").flatMap(_.headOption).getOrElse("uid")
  	val models = AdvertisementModel.find.orderBy(sort).findList  	
  	Ok(models.toString)
  }

  def get(uid: Int) = Action { request =>
  	Ok("Got " + uid);
  }

  def post(uid: Int) = Action { request =>
	request.body.asJson.map { json =>
		val df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

		val title = (json \ "title").asOpt[String]
		val price = (json \ "price").asOpt[Int]
		val mileage = (json \ "mileage").asOpt[Int]
		val registration = (json \ "registration").asOpt[String]
		val state = (json \ "state").asOpt[Boolean]

		try {
			val date = df.parse(registration.get)

			if (title.getOrElse(null) == null || price.getOrElse(null) == null
				|| mileage.getOrElse(null) == null || state.getOrElse(null) == null)
			{
				BadRequest("Missing fields")
			} else
			{
				val model = new AdvertisementModel
				model.title = title.get
				model.price = price.get
				model.mileage = mileage.get
				model.state = state.get
				model.registration = date
				model.save
				Ok("Ok")
			}
		} catch {
			case e: Exception => BadRequest("Bad fields")
		}
	}.getOrElse {
      BadRequest("Json Missing")
    }
  }

  def delete(uid: Int) = Action {
  	Ok("Got " + uid);
  }

}
