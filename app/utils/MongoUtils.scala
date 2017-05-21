package utils

import org.joda.time.format.ISODateTimeFormat
import org.joda.time.{DateTimeZone, DateTime}
import play.api.libs.json.Reads._
import play.api.libs.json._
import reactivemongo.bson.BSONObjectID

import scala.util.matching.Regex

/**
 * Created by sagar on 5/20/17.
 */
object MongoUtils {
  val writeId = (__.json.update((__ \ '_id).json.copyFrom((__ \ '_id \ '$oid).json.pick)))

  val writeObjectId = (__.json.pickBranch((__ \ "_id").json.update(of[JsString].map { case JsString(id) => Json.obj("$oid" -> id) })))

  def appendObjId(t: JsObject): JsObject = {
    val id = BSONObjectID.generate.stringify
    t ++ Json.obj("_id" -> Json.obj("$oid" -> id))
  }


  def transformIdToObjectId(jsObject: JsObject): JsObject = {
    jsObject \ "_id" match {
      case s: JsString => {
        jsObject.transform(writeObjectId).get
      }
      case i => {
        jsObject
      }
    }
  }

  implicit def transformToMongoFields(jsObject: JsObject): JsObject = {

    val datePattern = new Regex( """\"([0-2]\d{3}\-[0-1]?\d-[0-3]?\d(T[0-2]?\d:[0-6]?\d:[0-6]\d\.\d{3}[\+,\-]\d{2}[\:]\d{2})?)\"""", "dateTime")

    val repl = datePattern.replaceAllIn(
      Json.stringify(jsObject), m => "\\{\"\\$date\"\\:" + DateTime.parse(m.group("dateTime")).withZone(DateTimeZone.UTC).getMillis + "\\}"
    )
    Json.parse(repl).as[JsObject]
  }

  def transformMongoFields(jsObject: JsObject): JsObject = {
    val datePattern = new Regex( """\{"\$date":-?(\d{12,})\}""", "timestamp")
    val jsObjectWithStringifiedId = transformMongoId(jsObject)

    val repl = datePattern.replaceAllIn(
      Json.stringify(jsObjectWithStringifiedId),
      m => "\"" + new DateTime(m.group("timestamp").toLong, DateTimeZone.UTC) + "\""
    )

    Json.parse(repl).as[JsObject]
  }

  def transformMongoId(jsObject: JsObject): JsObject = {
    val jsObjectWithStringifiedId = (jsObject \ "_id") match {
      case JsDefined(s: JsString) => jsObject.-("_id").++(Json.obj("id" -> s))
      case JsDefined(o: JsObject) => jsObject.-("_id").++(Json.obj("id" -> o.\("$oid").get))
      case JsDefined(_) => jsObject
    }
    jsObjectWithStringifiedId
  }


}
