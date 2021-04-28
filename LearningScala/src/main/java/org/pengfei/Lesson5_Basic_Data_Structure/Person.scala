package org.pengfei.Lesson5_Basic_Data_Structure

class Person(val name: String, val age: Int, val sex: Symbol)

object Person {
  def apply(name: String, age: Int, sex: Symbol) = new Person(name, age, sex)
}