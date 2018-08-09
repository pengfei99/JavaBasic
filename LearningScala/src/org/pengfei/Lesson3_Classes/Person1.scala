package org.pengfei.Lesson3_Classes

class Person1(var firstName:String,var lastName:String) {
  var fullName=firstName+" "+lastName

  def getName(): String ={
    return fullName
  }

}
