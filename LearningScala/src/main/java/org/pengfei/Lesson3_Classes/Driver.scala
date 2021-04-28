package org.pengfei.Lesson3_Classes

class Driver (firstName:String,lastName:String) extends TaxiDriver {
  val fullName:String=firstName+" "+lastName
  override def drive(): Unit = {
    println("I'm driving taxis")
  }
}
