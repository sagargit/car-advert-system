package dao

import models.CarAdvert
import play.api.libs.json.JsObject
import utils.RequestParam
import scala.concurrent.{Future, ExecutionContext}

/**
 * Created by sagar on 5/20/17.
 */

trait Dao {

  def insert(carAdvert: CarAdvert)(implicit ctx: ExecutionContext): Future[CarAdvert]

  def findAll(param: RequestParam)(implicit ctx: ExecutionContext): Future[List[JsObject]]

  def findById(id: String)(implicit ctx: ExecutionContext): Future[Option[JsObject]]

  def findOne(t: JsObject)(implicit ctx: ExecutionContext): Future[Option[JsObject]]

  def update(id: String, carAdvert: CarAdvert)(implicit ctx: ExecutionContext): Future[Unit]

  def delete(id: String)(implicit ctx: ExecutionContext): Future[Unit]


}
