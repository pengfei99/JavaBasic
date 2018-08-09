package org.pengfei.Lesson3_Classes

class Person4 (firstName:String,lastName:String) {
  private val fullName=firstName+" "+lastName

  def getName(): String ={
    return fullName
  }

}
