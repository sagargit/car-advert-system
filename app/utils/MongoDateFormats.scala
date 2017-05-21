package utils

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatterBuilder, DateTimeFormat}
import play.api.libs.json.{Reads, JsString, JsValue, Writes}

/**
 * Created by sagar on 5/21/17.
 */
object MongoDateFormats {

  val mongoDateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"

  implicit val jodaDateTimeWrites = Writes.jodaDateWrites(mongoDateTimeFormat)

  val parsers = Array(DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").getParser,
    DateTimeFormat.forPattern("YYYY").getParser,
    DateTimeFormat.forPattern("YYYY-MM").getParser,
    DateTimeFormat.forPattern("yyyy-MM-dd").getParser,
    DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ").getParser
  )

  val formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter

  implicit val jodaDateTimeReads = Reads[DateTime](_.validate[String].map[DateTime]{dtString =>
  DateTime.parse(dtString, formatter)})
}
