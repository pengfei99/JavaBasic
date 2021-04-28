package org.pengfei.Lesson3_Classes

abstract class AbstractTaxiDriver(firstName:String,lastName:String) {
  val carType = "Taxi"
  val fullName:String=firstName+" "+lastName
  /* abstract method declaration*/
  def drive(): Unit

  /* Non abstract method*/
  def whichCarICanDrive(): String = {
    return carType
  }
}
