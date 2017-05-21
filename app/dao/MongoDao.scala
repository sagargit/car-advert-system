package dao

import contexts.ApplicationContext
import models.CarAdvert
import play.api.inject.guice.GuiceInjectorBuilder
import play.api.libs.json.{Json, JsObject, Writes, Reads}
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.{Cursor, DB}
import utils.{RequestParam, MongoUtils}
import scala.concurrent.{Future, ExecutionContext}
import models.CarAdvertMongoFormats._
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.json.collection._

/**
 * Created by sagar on 5/20/17.
 */
class MongoDao extends Dao {

  val reader: Reads[CarAdvert] = implicitly[Reads[CarAdvert]]
  val writer: Writes[CarAdvert] = implicitly[Writes[CarAdvert]]

  val injector = new GuiceInjectorBuilder()
    .bindings(new ApplicationContext)
    .injector

  val db: DB = injector.instanceOf[DB]

  val logger = play.api.Logger(this.getClass)

  def collection = db.collection[JSONCollection](DatabaseFactory.defaultCollection)

  def insert(carAdvert: CarAdvert)(implicit ctx: ExecutionContext): Future[CarAdvert] = {

    val modelToJsObj = writer.writes(carAdvert).as[JsObject]
    val objectWithId = MongoUtils.appendObjId(modelToJsObj)
    val finalObject = MongoUtils.transformToMongoFields(objectWithId)
    collection.insert(finalObject) map { k =>

      reader.reads(MongoUtils.transformMongoFields(objectWithId)).get
    }
  }

  def findAll(param: RequestParam)(implicit ctx: ExecutionContext): Future[List[JsObject]] = {
    val sort = if (param.sort.get.keys.nonEmpty) {
      param.sort.get
    } else {
      Json.obj("_id" -> 1)
    }
    collection.find(Json.obj()).sort(sort).cursor[JsObject]().collect[List]().map {
      list =>
        if (list.size > 0)
          list.map(k => MongoUtils.transformMongoFields(k))
        else
          List[JsObject]()
    }
  }

  def findById(id: String)(implicit ctx: ExecutionContext): Future[Option[JsObject]] = {
    val filter = Json.obj("_id" -> Json.obj("$oid" -> id))
    collection.find(filter).cursor[JsObject]().headOption.map {
      case Some(k) => Some(MongoUtils.transformMongoFields(k))

      case None => None
    }
  }

  def findOne(t: JsObject)(implicit ctx: ExecutionContext): Future[Option[JsObject]] = {
    collection.find(t).cursor[JsObject]().headOption
  }

  def update(id: String, carAdvert: CarAdvert)(implicit ctx: ExecutionContext): Future[Unit] = {
    val idObject = Json.obj("_id" -> Json.obj("$oid" -> id))
    val modelToJsObj = writer.writes(carAdvert.copy(id = None)).as[JsObject]
    Future.successful(collection.update(idObject, Json.obj("$set" -> modelToJsObj)))
  }

  def delete(id: String)(implicit ctx: ExecutionContext): Future[Unit] = {
    collection.remove(Json.obj("_id" -> Json.obj("$oid" -> id))).map(_ => ())
  }
}
