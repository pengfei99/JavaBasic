package org.pengfei.Lesson3_Classes

class Person2 (val firstName:String,val lastName:String) {
  val fullName=firstName+" "+lastName

  def getName(): String ={
    return fullName
  }

}
