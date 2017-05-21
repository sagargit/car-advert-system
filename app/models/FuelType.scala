package models


import play.api.libs.json.{Writes, Reads}
import utils.EnumUtils

/**
 * Created by sagar on 5/21/17.
 */

object FuelType extends Enumeration {
  type FuelType = Value

  val gasoline = Value
  val diesel = Value

  implicit val enumReads: Reads[FuelType] = EnumUtils.enumReads(FuelType)
  implicit def enumWrites: Writes[FuelType] = EnumUtils.enumWrites
}
