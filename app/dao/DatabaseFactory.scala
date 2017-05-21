package dao

import reactivemongo.api.{DefaultDB, DB}

import scala.concurrent.Future

/**
 * Created by sagar on 5/21/17.
 */
object DatabaseFactory {

  import com.typesafe.config.ConfigFactory
  import scala.collection.JavaConversions._

  val config = ConfigFactory.load

  val defaultDB = config.getString("mongodb.db")

  val defaultCollection = "cars.advert"

  val defaultLimit = 1000

  val locations: Seq[String] = config.getStringList("mongodb.servers")

  val driver = new reactivemongo.api.MongoDriver

  val connection = driver.connection(locations)

  lazy val db: DB = {
    import scala.concurrent.ExecutionContext.Implicits.global

    connection.db(DatabaseFactory.defaultDB)
  }

}
