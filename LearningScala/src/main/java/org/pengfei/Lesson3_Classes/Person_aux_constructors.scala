package org.pengfei.Lesson3_Classes

class Person_aux_constructors(firstName:String,lastName:String) {

  /* The key for auxiliary constructor is default value of class fields, and each constructor with more parameters
  * must call the previous one with less parameters*/
  var age=0
  var address="No_value"
  var fullName=firstName+" "+lastName

  def this(age:Int,firstName:String,lastName:String){
    this(firstName,lastName)
    this.age=age
  }

  def this(address:String,age:Int,firstName:String,lastName:String){
    this(age,firstName,lastName)
    this.address=address
  }

}
