
import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import play.api.libs.json._
import play.api.test.{PlaySpecification, FakeRequest}

/**
 * Created by sagar on 5/21/17.
 */

@RunWith(classOf[JUnitRunner])
class CarAdvertSpecification extends PlaySpecification with AbstractSpecification {

  val carAdvertJson = Json.parse(scala.io.Source.fromFile("./doc/advert.json").getLines.mkString)
  var advertId: String = ""


  "Application" should {
    "Insert a CarAdvert" in {
      val Some(result) = route(FakeRequest(POST, "/adverts")
        .withJsonBody(carAdvertJson))
      status(result) must equalTo(ACCEPTED)
      val json = contentAsJson(result)
      advertId = (json \ "id").as[String]
      advertId must not be equalTo("")
    }
  }

  "Get All CarAdverts" in {
    val Some(result) = route(FakeRequest(GET, "/adverts"))
    status(result) must equalTo(OK)
    val resultJson = contentAsJson(result)
    resultJson.\\("id").length must be_>=(1)
  }

  "Get CarAdvert by Id" in {
    val Some(result) = route(FakeRequest(GET, s"/adverts/$advertId"))
    status(result) must equalTo(OK)
    val resultJson = contentAsJson(result)
    println(Json.stringify(resultJson.\("id").get))
    Json.stringify(resultJson.\("id").get).stripPrefix("\"").stripSuffix("\"") must_== advertId

  }

  "Update CarAdvert" in {
    val updateAdvertJson = Json.obj("id" -> advertId, "mileage" -> JsNumber(30))
    println(updateAdvertJson)
    val Some(result) = route(FakeRequest(PUT, s"/adverts/$advertId").withJsonBody(updateAdvertJson))
    val Some(result1) = route(FakeRequest(GET, s"/adverts/$advertId"))
    val resultJson1 = contentAsJson(result1)
    status(result) must equalTo(OK)
    status(result1) must equalTo(OK)
    Json.stringify(resultJson1.\("mileage").get).toInt must_== 30
  }

  "Delete CarAdvert" in {
    advertId must not be equalTo("")
    val Some(result) = route(FakeRequest(DELETE, s"/adverts/$advertId"))
    status(result) must equalTo(OK)
  }

}
