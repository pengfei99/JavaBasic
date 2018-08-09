package org.pengfei.Lesson4_Operator_And_Statement_Control

import scala.util.control.Breaks

object Lesson4_Operator_And_Statement_Control {

  def main(args:Array[String]){
  /************************************************************************************************************
    * *****************************************4.1 Operators **************************************************
    * **********************************************************************************************************/

  /*
  * Scala provides a rich set of operators for the basic types. However, it does not have built-in operators. In
  * Scala, every type is a class and every operator is a method. Using an operator is equivalent to calling a
  * method. Consider the following example.*/

  val x=10
  val y=20
  val z=x+y

  /*
  * + is not a built-in operator in Scala. It is a method defined in class Int. The last statement in the
  * preceding code is same as the following code. val z = x.+(y)*/

  /************************************************************************************************************
    * *****************************************4.2 if then else **************************************************
    * **********************************************************************************************************/

  /* The scala if then else is very similar to Java's. So I will only show the if else if else example*/
  val x1=40
  if(0<= x1 && x1<=10 ){println("x is between 0 and 10")}
  else if(10< x1 && x1<=20){println("x is between 10 and 20")}
  else if(20< x1 && x1<=30) {println("x is between 10 and 20")}
  else if(0 > x1 || x1>30) {println("x is out of range")}
  else{println("unknow")}

    /************************************************************************************************************
      * *****************************************4.3 Loop **************************************************
      * **********************************************************************************************************/

    /******************************   While loop *************************/
    var x2=0
    while(x2>0){
      println("Value of x2: "+x2)
      x2=x2-1
    }

    /*********************************Do while loop ***********************/

    /*
    * Unlike while loop, which tests the loop condition at the top of the loop, the do-while loop checks its
    * condition at the bottom of the loop. A do-while loop is similar to a while loop, except that a do-while
    * loop is guaranteed to execute at least one time.*/

    var x3=0
    do{println("Value of x3: "+x3)
      x3=x3-1}
    while(x3>0)

    /*********************************For loop with range ***********************/
    /*
    * Here, the Range could be a range of numbers and that is represented as i to j or sometime like i until j.
    * The left-arrow ← operator is called a generator, so named because it's generating individual values from a range.*/
    /* x in 1 to 10 1<=x<=10*/
    var x4=0
    for(x4 <- 1 to 10){
      println("Value of x4: "+ x4)
    }

    /* x in 1 until 10 1<=x<10*/
    var x5=0
    for(x5<- 1 until 10){
      println("Value of x5: "+ x5)
    }

    /**************************For loop with two indice *****************/
    for(a<- 1 to 3; b<- 1 to 3){
      println("Value of a: "+a)
      println("Value of b: "+b)
    }

    /*************************For loop with collection ******************/
    val fruitList=List("apple","orangre","banana")
    for(fruit <- fruitList){
      println("Value of fruit: "+fruit)
    }

    /********************For loop with Filters *************************/

    val numList=List(1,2,3,4,5,6)
    for(num<-numList if num!=3; if num<5; if num!=1){
      println("Value of num: "+num)
    }

    /*******************For loop with yield ****************************/
    /* We can store the return value of for loop by using yield, in the following example we yield the return value num
      * and assign it to retVal */
    var retVal = for{ num<- numList if num!=3;if num<5} yield num

    for (a<- retVal){
      println("Value of a : "+a)
    }
   /*******************Break loop*****************************************/
     /* As such there is no built-in break statement available in Scala but if you are running Scala version 2.8,
     * then there is a way to use break statement. When the break statement is encountered inside a loop, the loop
     * is immediately terminated and program control resumes at the next statement following the loop.*/
    val loop=new Breaks
    loop.breakable{
    for(num<-numList){
      println("Value of num: "+num)
      if(num==4) loop.break
    }
    }

    /************************************************************************************************************
      * *****************************************4.4 Pattern Matching ********************************************
      * **********************************************************************************************************/

    /* Pattern matching is a Scala concept that looks similar to a switch statement in other languages. However, it
     * is a more powerful tool than a switch statement*/

    /* The matchIntToString function use a pattern matching as switch statement, which means input has the same type,
     * output has the same type too. */
    println(matchIntToString(1))
    println(matchIntToString(4))

    /* The matchAnyToAny illustrate how powerful the scala pattern matching is
    * in the same match we can match int, string, float to anything*/
   println(matchAnyToAny(1))
    println(matchAnyToAny("two"))
    println(matchAnyToAny(2.5))

    /* The scala pattern matching can be used for object type casting*/

    /* Pattern matching using case classes*/
    val alice= Person("Alice", 25)
    val bob= Person("Bob",32)
    val dog= Animal("Dog",4)

    println(distinctDogOrPerson(alice))
    println(distinctDogOrPerson(bob))
    println(distinctDogOrPerson(dog))
}
  def matchIntToString(x:Int):String = x match{
    case 1 => "one"
    case 2 => "two"
    case _=> "unknown"
  }

  def matchAnyToAny(x:Any):Any = x match {
    case 1 => "one"
    case "two" => 2
    case 2.5 => "I don't like this number"
    case _ => "unknown"
  }

  /* The case must start with more specific rules, the following example, the lase case is unreachable
  *  If we put first, we can see hi Bob in the result*/
  def distinctDogOrPerson(x:Any):Unit= x match {
    case Person(name,age) => println("Person "+"with name"+name)
    case Animal(race,age) => println("Animal of "+race)
    case Person("Bob",32) => println("hi Bob!")

  }

  case class Person(name:String,age:Int)
  case class Animal(race:String,age:Int)
}