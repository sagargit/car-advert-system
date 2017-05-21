package utils

import play.api.libs.json.{Json, JsObject}

/**
 * Created by sagar on 5/21/17.
 */
case class RequestParam(
                         sort: Option[JsObject] = Some(Json.obj())
                         )
