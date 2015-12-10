package controllers

import play.api._
import play.api.mvc._

class Advertisement extends Controller {

  def show = Action {
    Ok("Hello world")
  }

}
