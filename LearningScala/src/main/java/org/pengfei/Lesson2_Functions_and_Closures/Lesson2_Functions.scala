package org.pengfei.Lesson2_Functions_and_Closures

import java.nio.charset.StandardCharsets

object Lesson2_Functions {
  def main(args:Array[String]): Unit ={
    /* Scala treats functions as first-class citizens. A function can be used like a variable. It can be passed as an
    * input to another function. It can be defined as an unnamed function literal, like a string literal. It can
    * be assigned to a variable. It can be defined inside another function. It can be returned as an output from
    * another function.
    *
    * A function in Scala is defined with the keyword def. A function definition starts with the function
    * name, which is followed by the comma-separated input parameters in parentheses along with their types.
    * The closing parenthesis is followed by a colon, function output type, equal sign, and the function body in
    * optional curly braces. An example is shown next.*/

    /*******************************************************************************************
      * ******************************* 2.1 Basic functions ****************************************
      * *****************************************************************************************/

    /*The add function does exact same thing as the addComplete,
     * 1. The return type is ommited since the compiler can infer it from the code. However, it is
     * recommended not to omit the return type of a function
    *
    * 2. The curly braces are also omitted in this version. They are required only if a function body consists of
    * more than one statement.
    *
    * 3. The keyword return is omitted since it is optional. Everything in Scala is an expression that returns
    * a value. The result of the last expression, represented by the last statement, in a function body becomes
    * the return value of that function.*/

def add(firstInput:Int,secondInput:Int)=firstInput+secondInput

    val sum = add(1,2)
    println("Value of sum: "+ sum)

    /*****************************************************************************************************
      * ****************************2.2 Methods********************************************************
      * **************************************************************************************************/

    /*A method is a function that is a member of an object. It is defined like and works the same as a function. The
    * only difference is that a method has access to all the fields of the object to which it belongs.*/

    /*In the following example, I create an instance of class people, and call it's method getAge and getName*/
    val toto=new People(23,"toto")
    val totoAge=toto.getAge()
    val totoName=toto.getName()
    println("Age value: "+totoAge)
    println("Name value: "+totoName)

    /*****************************************************************************************************
      * ****************************2.3 Local functions********************************************************
      * **************************************************************************************************/

    /* A function defined inside another function or method is called a local function. It has access to the variables
    * and input parameters of the enclosing function. A local function is visible only within the function in which
    * it is defined. This is a useful feature that allows you to group statements within a function without polluting
    * your application’s namespace.*/

    val factResult=factorial(5)
    println("factResult value: "+factResult)

    /* a nested method is only visible inside the enclosing method. If you try to call fact() outside of factorial(),
    * you will get a compiler error.*/

    /*****************************************************************************************************
      * ****************************2.4 High order methods********************************************************
      * **************************************************************************************************/

    /* A method that takes a function as an input parameter is called a higher-order method. Similarly, a high-order
    * function is a function that takes another function as input. Higher-order methods and functions help reduce
    * code duplication. In addition, they help you write concise code.
    * */
    /* The following function simulate a encryption function, it signs message with user sign then encrypt it
    * The third input is a function*/

    def encrypt(userSign:String, message:String, desEncrypt:(String)=>Array[Byte]):Array[Byte]={
      val signMessage=message.concat(userSign)
      desEncrypt(signMessage)
    }

    /*****************************************************************************************************
      * ****************************2.5 Function Literals********************************************************
      * **************************************************************************************************/

    /* A function literal is an unnamed or anonymous function in source code. It can be used in an application just
    * like a string literal. It can be passed as an input to a higher-order method or function. It can also be assigned
    * to a variable.
    * A function literal is defined with input parameters in parenthesis, followed by a right arrow and the
    * body of the function. The body of a functional literal is enclosed in optional curly braces. An example is
    * shown next.*/

    //(input:Int)=>{input+100}

    /* If the function body consists of a single statement, the curly braces can be omitted. A concise version of
    * the same function literal is shown next. (x: Int) => x + 100 */

        /* A function literal can be used as a parameter in functions. In the following example, we use a
        * function literal (x:String)=>x.getBytes(StandardCharsets.UTF_8) to simulate encryption functions*/
      val signMessageEnc=encrypt("pliu","helloWorld",(x:String)=>x.getBytes(StandardCharsets.UTF_8))

      println("signMessageEnc value:"+new String(signMessageEnc,StandardCharsets.UTF_8))
    /*****************************************************************************************************
      * ****************************2.6 Closures********************************************************
      * **************************************************************************************************/

    /* The body of a function literal typically uses only input parameters and local variables defined within the
    * function literal. However, Scala allows a function literal to use a variable from its environment. A closure is
    * a function literal that uses a non-local non-parameter variable captured from its environment. Sometimes
    * people use the terms function literal and closure interchangeably, but technically, they are not the same.*/

    val encodeExample=encodeWithSeed(4,5)
    println("encodeExample value :"+encodeExample)


  }

  /*The addComplete is the full version of function declaration*/
  def addComplete(firstInput: Int, secondInput: Int):Int={
    val sum =firstInput+secondInput
    return sum
  }

  /* This function use a recursive way to calculate the factorial of a number*/
  def factorial(i:Int):Int ={
    /*define a local function*/
    def fact(i:Int, accumulator:Int):Int={
      if(i<=1) accumulator
      else fact(i-1,i*accumulator)
    }
    fact(i,1)
  }

  /* This function is an example closure,   */
  def encodeWithSeed(num:Int,seed:Int):Long={
    def encode(x:Int,func:(Int)=>Int):Long ={
      val y = x+1000
      func(y)
    }
    /* the function literal takes seed from the environment, it's not a local variable or passed as parameters*/
    val result = encode(num,(n:Int)=>(n*seed))
    return result
  }
}
