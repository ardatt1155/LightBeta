package controllers

import play.api._
import play.api.mvc._
import models._

class Advertisement extends Controller {

  def show = Action {
  	val x = new Advertisement()
  	System.out.println(x.toString())
    Ok("Hello world")
  }

}
