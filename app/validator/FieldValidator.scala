package validator

import com.wix.accord._
import play.api.libs.json.{Writes, Reads, Json}

/**
 * Created by sagar on 5/20/17.
 */

object FieldValidator {

  def validateData[T](x: T, validator: Validator[T]): Option[Seq[ViolationMessage]] = {
    if (validator != null) {
      validate(x)(validator) match {
        case com.wix.accord.Success => None
        case f: Failure => Some(failure(f.violations))
      }
    } else None
  }

  def failure(f: Set[com.wix.accord.Violation]): Seq[ViolationMessage] = {
    var messages = Seq.empty[ViolationMessage]
    f.foreach { v =>
      messages = messages :+ ViolationMessage(v.value.toString, Some(Descriptions.render(v.description)), v.constraint)
    }
    messages
  }
}

case class ViolationMessage(current: String, field: Option[String], message: String)

object ViolationFormat {
  implicit val violationMessageWrites = Json.writes[ViolationMessage]
  implicit val seqViolationMessageWrites = Writes.seq(violationMessageWrites)

}
