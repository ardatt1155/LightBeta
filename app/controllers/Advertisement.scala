package controllers

import models.{Advertisement => AdvertisementModel}
import play.api.Play.current
import play.api._
import play.api.mvc._
import java.util._
import java.text._
import play.api.libs.json._

class Advertisement extends Controller {

  def show = Action { request =>
    try {
    	val sort = request.queryString.get("sort").flatMap(_.headOption).getOrElse("uid")
    	val models = AdvertisementModel.find.orderBy(sort).findList
  	  val response = AdvertisementModel.toJson(models)
  	  Ok(response)
    } catch {
      case e: Exception => BadRequest("Not ok")
    }
  }

  def get(uid: Int) = Action {
    try {
    	val model = AdvertisementModel.find.where.eq("uid", uid).findUnique
    	val result = model.toJson
  	  Ok(result);
    } catch {
      case e: Exception => BadRequest("Not ok")
    }
  }

  def post(uid: Int) = Action { request =>
    try {
      val json = request.body.asJson.get
      val nm = AdvertisementModel.fromJson(json)
      val om = AdvertisementModel.find.where.eq("uid", uid).findUnique
      om.assign(nm)
      om.save
      Ok("Ok")
    } catch {
      case e: Exception => BadRequest("Not ok")
    }
  }

  def put = Action { request =>
  	try {
      val json = request.body.asJson.get
			val model = AdvertisementModel.fromJson(json)
      model.save
  		Ok("Ok")
  	} catch {
			case e: Exception => BadRequest("Not ok")
  	}
  }

  def delete(uid: Int) = Action {
    try {
    	AdvertisementModel.find.where.eq("uid", uid).findUnique.delete
    	Ok("OK");
    } catch {
      case e: Exception => BadRequest("Not ok")
    }
  }

}
