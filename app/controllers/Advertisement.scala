package controllers

import models.{Advertisement => AdvertisementModel}
import play.api.Play.current
import play.api._
import play.api.mvc._
import java.util._
import java.text._

class Advertisement extends Controller {

  def show = Action { request =>
  	val sort = request.queryString.get("sort").flatMap(_.headOption).getOrElse("uid")
  	val models = AdvertisementModel.find.orderBy(sort).findList
  	Ok(models.toString)
  }

  def get(uid: Int) = Action {
  	val model = AdvertisementModel.find.where.eq("uid", uid).findUnique
  	val result = model.toJson
  	Ok(result);
  }

  def post = Action { request =>
	request.body.asJson.map { json =>
		try {
			val model = AdvertisementModel.fromJson(json)
			model.save
			Ok("Ok")
		} catch {
			case e: Exception => BadRequest("Not ok")
		}
	}.getOrElse {
      BadRequest("Not ok")
    }
  }

  def delete(uid: Int) = Action {
  	AdvertisementModel.find.where.eq("uid", uid).findUnique.delete
  	Ok("OK");
  }

}
