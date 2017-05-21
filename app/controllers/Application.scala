package controllers

import javax.inject.Inject
import entity.BaseEntity
import models.{CarAdvertApiFormats, UpdateCarAdvert, AddCarAdvert}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import utils.{CaseConverter, RequestParamParser, RequestParam}
import validator.{ViolationMessage, ViolationFormat, FieldValidator, FieldError}
import scala.concurrent.Future
import validator.CarAdvertValidator._
import scala.concurrent.ExecutionContext.Implicits.global
import CarAdvertApiFormats._

/**
 * Created by sagar on 5/20/17.
 */

class Application @Inject()(baseEntity: BaseEntity) extends Controller with RequestParamParser {

  def createAdvert = Action.async(parse.json) {
    implicit request =>
      request.body.validate[AddCarAdvert].fold(
        left =>
          Future.successful(BadRequest(Json.toJson(FieldError(left.seq)))),
        right =>
          FieldValidator.validateData(right, addCarAdvertValidator) match {
            case Some(messages) =>
              Future.successful(BadRequest(ViolationFormat.seqViolationMessageWrites.writes(messages)))

            case None =>
              baseEntity.save(right).map {
                k => Accepted(Json.obj("message" -> "Successfully Inserted",
                  "id" -> k.id.get))
              }

          }
      )
  }

  def getAllAdverts = Action.async {
    implicit request =>
      val param: RequestParam = parseRequestParams(request.queryString)
      baseEntity.list(param).map {
        k =>
          val listJson = carAdvertWritesSeq.writes(k)
          Ok(listJson)
      }
  }

  def getAdvertById(id: String) = Action.async {
    implicit request =>
      baseEntity.getById(id).map {
        case Some(carAdvert) =>
          val json = carAdvertWrites.writes(carAdvert)
          Ok(json)

        case None =>
          NotFound(Json.obj("message" -> "CarAdvert not found with given id",
            "id" -> id))
      }
  }

  def updateCarAdvert(id: String) = Action.async(parse.json) {
    implicit request =>
      implicit val updateCarAdvert = CaseConverter.snakecase(Json.format[UpdateCarAdvert])
      request.body.validate[UpdateCarAdvert].fold(
        left =>
          Future.successful(BadRequest(Json.toJson(FieldError(left.seq)))),
        right =>
          FieldValidator.validateData(right, updateCarAdvertValidator) match {
            case Some(messages) =>
              Future.successful(BadRequest(ViolationFormat.seqViolationMessageWrites.writes(messages)))

            case None => baseEntity.update(id, right).map {
              k => Ok(Json.obj("message" -> "Successfully Updated",
                "id" -> id))
            }

          }
      )
  }

  def removeAdvert(id: String) = Action.async {
    implicit request =>
      baseEntity.remove(id).map { k =>
        Ok(Json.obj("message" -> "Successfully Deleted",
          "id" -> id))
      }
  }

}
