import org.specs2.mutable.Specification
import org.specs2.specification.Step
import org.specs2.specification.core.Fragments
import play.api.Play
import play.api.test.FakeApplication

/**
 * Created by sagar on 5/21/17.
 */

trait AbstractSpecification {
  this: Specification =>

  val location = "Location"

  val testDatabaseConfig = Map("mongodb.uri" -> "mongodb://localhost:27017/advertdb")

  def testApplication = FakeApplication(additionalConfiguration = testDatabaseConfig)

  sequential
  ""

  def startApp = {
    println("start test")
    if (Play.unsafeApplication == null) Play.start(testApplication)
    success
  }

  def stopApp = {
    println("stop test")
    success
  }

  override def map(fs: => Fragments) = step(startApp) ^ fs ^ step(stopApp)

}
