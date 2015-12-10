package controllers

import play.api._
import play.api.mvc._
import models._
import java.util._
import java.text._

class Advertisement extends Controller {

  def show = Action {
  	val x = new Advertisement()
  	System.out.println(x.toString())
    Ok("Hello world")
  }

  def get(uid: Int) = Action { request =>
  	val sort = request.queryString.get("sort").flatMap(_.headOption).getOrElse("uid");
  	Ok("Got " + uid);
  }

  def post(uid: Int) = Action { request =>
	request.body.asJson.map { json =>
		val df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
		val title = (json \ "title").asOpt[String].getOrElse(null)
		val price = (json \ "price").asOpt[Int].getOrElse(null)
		val mileage = (json \ "mileage").asOpt[Int].getOrElse(null)
		val registration = (json \ "registration").asOpt[String].getOrElse(null)
		val state = (json \ "state").asOpt[Int].getOrElse(null)
		try {
			val date = df.parse(registration)
			if (title == null || price == null || mileage == null || state == null) {
				BadRequest("Missing fields")
			} else {
				System.out.println(date)
				Ok(title + price + mileage)
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
