package org.pengfei.Lesson1_BasicTypes_Variables

object Lesson1_Variables {


  def main(args:Array[String]){
/**************************************************************************************************
  * ************************************ 1.1 Variables ******************************************
  * *********************************************************************************************/
    /*
* Scala has two types of variables: mutable and immutable. Usage of mutable variables is highly discouraged. A pure
* functional program would never use a mutable variable. However, sometimes usage of mutable variables may result
* in less complex code, so Scala supports mutable variables too. It should be used with caution.
*
* A mutable variable is declared using the keyword var; whereas an immutable variable is declared using the keyword val.
* A var is similar to a variable in imperative languages such as C/C++ and Java. It can be reassigned after
* it has been created. The syntax for creating and modifying a variable is shown next.
* */
    /*Immutable variable, :Int is optional scala can determine it automatically with val x=1*/
    val x:Int=1
    println("x has value: "+x)
    // x=2 will not work

    /*Mutable variable*/
    var y=2
    println("y has value: "+y)
    y=3
    println("y has value: "+y)

    /******************************************************************************************
      ************************************1.2 Basic Types ************************************
      ***************************************************************************************/

    /* Note that Scala does not have primitive types like java. Each type in Scala is implemented as a class. When a
    * Scala application is compiled to Java bytecode, the compiler automatically converts the Scala types to Java’s
    * primitive types wherever possible to optimize application performance.*/

    /*
    * Variable Type Description
    * Byte 8-bit signed integer
    * Short 16-bit signed integer
    * Int 32-bit signed integer
    * Long 64-bit signed integer
    * Float 32-bit single precision float
    * Double 64-bit double precision float
    * Char 16-bit unsigned Unicode character
    * String A sequence of Chars
    * Boolean true or false
    * */

    /*****************************1.2.1 Basic Type Casting*************/

      /*String to Int*/
    val x1=42
    println("Type of x1 :"+x1.getClass.getName)
    val str_x1=x1.toString
    println("Type of str_x1: "+str_x1.getClass.getName)

    /*Int to String*/

    val str="38"
    println("Type of str : "+str.getClass.getName)
    val int_str=str.toInt
    println("Type of int_str: "+int_str.getClass.getName)
  /* you need to take account of exception when the string may not be able to convert to int
  * The function customToInt shows a safe way to convert str to int*/

    /*When we cosume an option class in scala, it's better to use pattern matching to get the object
    * For example we have Some(Int), but we need Int*/
    val int_str1=customToInt(str) match {
      case Some(i)=>i
      case None=>0
    }

    println("Type of int_str1: "+int_str1.getClass.getName)
    val sum=int_str1+int_str
    println("Sum value: "+ sum)
    val not_str=customToInt("13s")
    println("Type of not_str: "+not_str.getClass.getName)

    /*******************************1.3 String trim ************************/
  val test:String="  hell o w or ld"
    val afterTrim=test.trim()
    println(s"afterTrim value $afterTrim")
  }

  /* A typical scala function, it catch exception and return none, if everything goes well, it returns
  * Some[Int]*/
  def customToInt(s:String): Option[Int] ={
    try{
      Some(Integer.parseInt(s.trim))
    }
    catch{
      case e: NumberFormatException => None
    }
  }
}
