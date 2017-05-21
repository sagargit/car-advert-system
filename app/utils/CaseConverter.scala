package utils

import play.api.libs.json._

/**
 * Created by sagar on 5/21/17.
 */

object CaseConverter {

  private def mapKeys[A, B, C](m: Seq[(A, B)])(f: A => C): Seq[(C, B)] =
    m.map { case (k, v) => (f(k), v) }

  private def snakecaseReads[T](parentReads: JsValue => JsResult[T]): Reads[T] = new Reads[T] {
    def reads(json: JsValue): JsResult[T] = {
      parentReads(json match {
        case obj: JsObject => JsObject(mapKeys(obj.fields)(StringUtil.camelcase))
        case x => x
      })
    }
  }

  private def snakecaseWrites[T](parentWrites: T => JsValue): Writes[T] = new Writes[T] {
    def writes(o: T): JsValue = {
      parentWrites(o) match {
        case obj: JsObject => JsObject(mapKeys(obj.fields)(StringUtil.snakecase))
        case x => x
      }
    }
  }

  def snakecase[T](reads: Reads[T]): Reads[T] = snakecaseReads(reads.reads)

  def snakecase[T](writes: Writes[T]): Writes[T] = snakecaseWrites(writes.writes)

  def snakecase[T](format: Format[T]): Format[T] = Format(snakecaseReads(format.reads), snakecaseWrites(format.writes))

  object StringUtil {

    def camelcase(s: String): String ={
      if(s.head == '_'){
        "_".concat((s.tail.split("_").toList match {
          case head :: tail => head :: tail.map(_.capitalize)
          case x => x
        }).mkString)
      }
      else{
        (s.split("_").toList match {
          case head :: tail => head :: tail.map(_.capitalize)
          case x => x
        }).mkString
      }
    }

    def snakecase(s: String): String = s.foldLeft(new StringBuilder) {
      case (s, c) if Character.isUpperCase(c) =>
        s.append("_").append(Character.toLowerCase(c))
      case (s, c) =>
        s.append(c)
    }.toString

  }

}
