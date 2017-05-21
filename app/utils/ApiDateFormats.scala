package utils

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatterBuilder, DateTimeFormat}
import play.api.libs.json.{JsString, JsValue, Reads, Writes}

/**
 * Created by sagar on 5/21/17.
 */

object ApiDateFormats {

  val applicationDateTimeFormat = "yyyy-MM-dd"
  val mongoDateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"

  implicit val jodaDateTimeWrites = jodaDateWrite(mongoDateTimeFormat, applicationDateTimeFormat)

  def jodaDateWrite(dbPattern: String, applicationPattern: String): Writes[org.joda.time.DateTime] = new Writes[org.joda.time.DateTime] {
    val applicationDF = org.joda.time.format.DateTimeFormat.forPattern(applicationPattern)
    val mongoDF = org.joda.time.format.DateTimeFormat.forPattern(dbPattern)

    def writes(d: org.joda.time.DateTime): JsValue = {
      val stringifiedTime = d.toString(mongoDF)
      val updatedStringifiedTime = stringifiedTime.substring(0, stringifiedTime.indexOf("T"))
      JsString(updatedStringifiedTime)
    }
  }

  val parsers = Array(DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").getParser,
    DateTimeFormat.forPattern("YYYY").getParser,
    DateTimeFormat.forPattern("YYYY-MM").getParser,
    DateTimeFormat.forPattern("yyyy-MM-dd").getParser,
    DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ").getParser
  )
  val formatter = new DateTimeFormatterBuilder().append(null, parsers).toFormatter

  implicit val jodaDateTimeReads = Reads[DateTime](_.validate[String].map[DateTime]{dtString =>

   val updatedDateString = dtString +"T00:00:00.000+00:00"
   val updatedDateTime = formatter.withOffsetParsed().parseDateTime(updatedDateString)
   updatedDateTime
  })

}
