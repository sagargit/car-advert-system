package validator

import com.wix.accord.dsl._
import models.{UpdateCarAdvert, AddCarAdvert}

/**
 * Created by sagar on 5/21/17.
 */

object CarAdvertValidator {

  val addCarAdvertValidator = validator[AddCarAdvert]{v =>
    v.`new` is notEmpty
    v.title is notEmpty
    v.price is notEmpty
    v.fuel is notEmpty
  }

  val updateCarAdvertValidator = validator[UpdateCarAdvert]{v =>
    v.id is notEmpty
  }

}
