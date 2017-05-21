package models

import models.FuelType.FuelType
import org.joda.time.DateTime
import play.api.libs.json.{Writes, Reads, Json}
import utils.CaseConverter

/**
 * Created by sagar on 5/20/17.
 */

case class CarAdvert(
                      id: Option[String],
                      title: Option[String] = None,
                      fuel: Option[FuelType] = None,
                      price: Option[BigDecimal] = None,
                      `new`: Option[Boolean] = None,
                      mileage: Option[BigDecimal] = None,
                      firstRegistration: Option[DateTime] = None
                      )

case class AddCarAdvert(
                         title: Option[String] = None,
                         fuel: Option[FuelType] = None,
                         price: Option[BigDecimal] = None,
                         `new`: Option[Boolean] = None,
                         mileage: Option[BigDecimal] = None,
                         firstRegistration: Option[DateTime] = None
                         )

case class UpdateCarAdvert(
                            id: Option[String] = None,
                            title: Option[String] = None,
                            fuel: Option[FuelType] = None,
                            price: Option[BigDecimal] = None,
                            `new`: Option[Boolean] = None,
                            mileage: Option[BigDecimal] = None,
                            firstRegistration: Option[DateTime] = None
                            )

object CarAdvertApiFormats {

  import utils.ApiDateFormats._

  implicit val carAdvertReads = CaseConverter.snakecase(Json.reads[CarAdvert])
  implicit val carAdvertWrites = CaseConverter.snakecase(Json.writes[CarAdvert])
  implicit val carAdvertReadsSeq = CaseConverter.snakecase(Reads.seq(carAdvertReads))
  implicit val carAdvertWritesSeq = CaseConverter.snakecase(Writes.seq(carAdvertWrites))
  implicit val addCarAdvert = CaseConverter.snakecase(Json.format[AddCarAdvert])
  implicit val updateCarAdvert = CaseConverter.snakecase(Json.format[UpdateCarAdvert])
}

object CarAdvertMongoFormats {

  import utils.MongoDateFormats._

  implicit val carAdvertReads = CaseConverter.snakecase(Json.reads[CarAdvert])
  implicit val carAdvertWrites = CaseConverter.snakecase(Json.writes[CarAdvert])
  implicit val carAdvertReadsSeq = CaseConverter.snakecase(Reads.seq(carAdvertReads))
  implicit val carAdvertWritesSeq = CaseConverter.snakecase(Writes.seq(carAdvertWrites))
  implicit val addCarAdvert = CaseConverter.snakecase(Json.format[AddCarAdvert])
  implicit val updateCarAdvert = CaseConverter.snakecase(Json.format[UpdateCarAdvert])
}