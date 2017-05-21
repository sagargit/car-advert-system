package contexts

import entity.{CarAdvertEntity, BaseEntity}
import net.codingwell.scalaguice.ScalaModule
import reactivemongo.api.DB
import dao.{DatabaseFactory, MongoDao, Dao}

/**
 * Created by sagar on 5/20/17.
 */

class ApplicationContext extends ScalaModule {

  def configure() = {
    bind[DB].toInstance(DatabaseFactory.db)
    bind[Dao].to[MongoDao]
    bind[BaseEntity].to[CarAdvertEntity]
  }

}
