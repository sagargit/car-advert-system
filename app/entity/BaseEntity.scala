package entity

import models.{UpdateCarAdvert, AddCarAdvert, CarAdvert}
import utils.RequestParam
import scala.concurrent.{Future, ExecutionContext}

/**
 * Created by sagar on 5/20/17.
 */

trait BaseEntity {

  def uuid = java.util.UUID.randomUUID.toString

  def save(addCarAdvert: AddCarAdvert)(implicit ctx: ExecutionContext): Future[CarAdvert]

  def list(param: RequestParam)(implicit ctx: ExecutionContext): Future[Seq[CarAdvert]]

  def getById(id: String)(implicit ctx: ExecutionContext): Future[Option[CarAdvert]]

  def update(id: String, carAdvert: UpdateCarAdvert)(implicit ctx: ExecutionContext): Future[Unit]

  def remove(id: String)(implicit ctx: ExecutionContext): Future[Unit]

}
