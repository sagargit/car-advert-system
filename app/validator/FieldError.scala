package validator

import play.api.data.validation.ValidationError
import play.api.libs.json._
import utils.CaseConverter

/**
 * Created by sagar on 5/21/17.
 */
case class FieldError(path: String, error: Option[String])

object FieldError {
  implicit val fieldErrorWrites: Writes[FieldError] = new Writes[FieldError] {
    def writes(foo: FieldError): JsValue = {
      val paths = foo.path.split("/")
      val a = paths.foldLeft("")((r, s) => if (!s.equals(paths(1))) r + "." + s else s)
      Json.obj("field" -> CaseConverter.StringUtil.snakecase(a), "message" -> foo.error)
    }
  }

  def apply(error: Seq[(JsPath, Seq[ValidationError])]): JsObject = {
    val errors = error.map { x =>
      FieldError(x._1.toString(), x._2.map {
        _.message
      }.headOption match {
        case Some("error.path.missing") => Some("Required")
        case Some("error.expected.jsstring") => Some("Expected String")
        case Some("error.expected.jsnumber") => Some("Expected Number")
        case Some("error.expected.jsarray") => Some("Expected Array")
        case e: Option[String] => e
      })

    }
    Json.obj("result" -> "error", "log" -> errors, "message" -> "Schema Validation Failed")
  }
}