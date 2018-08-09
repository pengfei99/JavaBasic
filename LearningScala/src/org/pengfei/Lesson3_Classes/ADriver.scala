package org.pengfei.Lesson3_Classes

class ADriver(firstName:String,lastName:String) extends AbstractTaxiDriver(firstName,lastName) {
  override def drive(): Unit = {
    println("I'm driving taxi")
  }

  def getName():String={
  return fullName
  }
}
