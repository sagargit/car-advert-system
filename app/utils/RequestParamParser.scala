package utils

import play.api.libs.json.{JsObject, Json}

/**
 * Created by sagar on 5/20/17.
 */
trait RequestParamParser {

  def parseRequestParams(queryString:  Map[String, Seq[String]]): RequestParam = {

    val sort = Json.parse(queryString.get("sort").flatMap(_.headOption.map(_.toString)).getOrElse("{}")).as[JsObject]

    RequestParam(sort = Some(sort))
  }

}
