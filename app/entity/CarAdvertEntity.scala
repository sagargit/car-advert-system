package entity

import com.google.inject.Inject
import dao.Dao
import models._
import play.api.libs.json.JsArray
import reactivemongo.bson.BSONObjectID
import utils.{RequestParam}
import scala.concurrent.{Future, ExecutionContext}

/**
 * Created by sagar on 5/21/17.
 */

class CarAdvertEntity @Inject()(dao: Dao) extends BaseEntity {

  def generateId = BSONObjectID.generate.stringify

  def save(addCarAdvert: AddCarAdvert)(implicit ctx: ExecutionContext): Future[CarAdvert] = {

    val id = generateId
    val carAdvert = CarAdvert(
      id = Some(id),
      title = addCarAdvert.title,
      fuel = addCarAdvert.fuel,
      price = addCarAdvert.price,
      `new` = addCarAdvert.`new`,
      mileage = addCarAdvert.mileage,
      firstRegistration = addCarAdvert.firstRegistration
    )
    dao.insert(carAdvert)
  }

  def list(param: RequestParam)(implicit ctx: ExecutionContext): Future[Seq[CarAdvert]] = {
    val result = dao.findAll(param).map {
      k =>
        val array = JsArray(k)
        CarAdvertMongoFormats.carAdvertReadsSeq.reads(array).getOrElse(List.empty[CarAdvert])
    }
    result
  }

  def getById(id: String)(implicit ctx: ExecutionContext): Future[Option[CarAdvert]] = {
    dao.findById(id).map {
      case Some(carAdvertJson) =>
        Some(CarAdvertMongoFormats.carAdvertReads.reads(carAdvertJson).get)

      case None => None
    }
  }

  def update(id: String, updateCarAdvert: UpdateCarAdvert)(implicit ctx: ExecutionContext): Future[Unit] = {
    val carAdvert = CarAdvert(
      id = Some(id),
      title = updateCarAdvert.title,
      fuel = updateCarAdvert.fuel,
      price = updateCarAdvert.price,
      `new` = updateCarAdvert.`new`,
      mileage = updateCarAdvert.mileage,
      firstRegistration = updateCarAdvert.firstRegistration
    )

    dao.update(id, carAdvert)
  }


  def remove(id: String)(implicit ctx: ExecutionContext): Future[Unit] = {
    dao.delete(id)
  }

}
