package org.pengfei.Lesson3_Classes

class Person(firstName:String,lastName:String) {
  println("the constructor begins")
  val fullName=firstName+" "+lastName

  def getName(): String ={
    return fullName
  }
  println("FullName is: "+fullName )
  println(" Still in the constructor")
}
