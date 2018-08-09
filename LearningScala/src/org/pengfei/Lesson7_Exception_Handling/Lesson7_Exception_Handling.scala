package org.pengfei.Lesson7_Exception_Handling

import scala.util.Try
import scala.util.control.Exception._

object Lesson7_Exception_Handling {
/* Scala's exceptions work like exceptions in many other languages like Java. Instead of returning a value in the
 * normal way, a method can terminate by throwing an exception. However, Scala doesn't actually have checked
 * exceptions.
 *
 *
 * When you want to handle exceptions, you use a try{...}catch{...} block like you would in Java except that the
 * catch block uses matching to identify and handle the exceptions.*/
  def main(args:Array[String]): Unit ={
    /**************************************************************************************
      * ************************7.1 Throwing  exceptions **********************************
      * ***********************************************************************************/
   /* check the method square to see how to throw exceptions*/
    println(s"show me the square ${square("3")}")

    /* In scala , unhandled exception will stop the program*/
    /**************************************************************************************
      * ************************7.2 Handling  exceptions (java like)************************
      * ***********************************************************************************/

    /* We can use try catch as in Java to handle exceptions, In scala we only catch once, then we use case
     * to list and handle all possible exceptions */
    try{
      val goodSquare=square("3")
      val badSquare=square("Me")
    }
    catch{
      case e:IllegalArgumentException=>{println("The input argument must be a digital number")}
      case e: Exception=>{println("Put some exception handling code here")}
    }

    /*finally clause executes the code inside it whether the expression or program prematurely terminates
    or successfully executes. try the following code with both good and bad argument */

    try{
      val badSquare=square("3")
    }
    catch{
      case e:IllegalArgumentException=>{
        println("The input argument must be a digital number")
      }
    }
    finally {
      println("This will be always be executed no matter what happens in the try")
    }

    /**************************************************************************************
      * ********************** 7.3 Handling  exceptions with scala try type ***************
      * ***********************************************************************************/
   /* The most common approach when you want to propagate an exception, is to encoding such possibility using the
   * Scala type system. This means the return type will provide the information that an exception may be thrown.
   * Scala offers the scala.util.Try type, which represents a computation that may either result in an exception
   * (scala.util.Failure), or return a successfully computed value (scala.util.Success). This is very similar to the
   * Some(T) and None approach*/

    println(s"Scala try type shows good square value ${Try(square("3"))}")
    println(s"Scala try type shows bad square value ${Try(square("sdsd"))}")

    val tryRes=Try(square("sdfsdf"))
    println(s"It's an failure ${tryRes.isFailure}")
    println(s"It's a success ${tryRes.isSuccess}")

    /* The scala.util.Try class provides many convenient methods like the usual .map, .flatMap, .fold, .getOrElse,
    and many others*/

    /********** Very Important, only non-fatal exceptions are caught on Try ********************/

    /**************************************************************************************
      * ********************** 7.4 Non fatal  exceptions in scala  ***********************
      * ***********************************************************************************/

    /* Non fatal exceptions is a scala notion, it does not exist in java or jvm.
    * NonFatal is just a convenient extractor which is defined in scala.util.control. We still use the non-fatal error
    * as Exception, fatal error as Error*/

    /* An Extractor in scala is an object that has a method called unapply as one of its members, The purpose of
     * that unapply method is to match a value and take it apart. Often, the extractor object also defines a dual method
     * apply for building values, but this not required. The below code called an extractor object for EMail address*/
    val pengfeiEmail=EMail.apply("pengfei","pengfei.org")
    println(s"pengfeiEmail value is ${pengfeiEmail}")
    val res:Tuple2[String,String]=EMail.unapply("pengfei@pengfei.org").getOrElse(0).asInstanceOf[Tuple2[String,String]]
    println(s"Name vaule is ${res._1}")
    println(s"Address vaule is ${res._2}")

    /* Now we know what extractor and NonFatal is , we could give the difference between exception and
    * NonFatal(exception)
    *
    * try { operation() }
    * catch {
    * case e: Exception => errorHandler(e)
    * }
    * If the exception throwed in try is a subclass of Exception, it will be caught with the above
    * code, but with the following code
    * try { operation() }
    * catch {
    * case NonFatal(e) => errorHandler(e)
    * }
    * The NonFatal(e) will match(and catch the exception) only when NonFatal.apply(e) returns true
    * In another world, NonFatal is just a subset of Exception which performs additional logic on the
    * matched exceptions.
*/

/**************************************************************************************
  * ********************** 7.5 Catch objects *****************************
  * ***********************************************************************************/

    /* There’s still another exception handling mechanism in Scala, which is encapsulated in the
    * scala.util.control.Exception class. Unfortunately it’s barely known, and there are even fewer
    * examples of its usage.
    *
    * It’s a neat mechanism which allows you to compose exception handlers where you can specify the behaviour upon
    * exceptions found. You can for instance wrap the block passed into the handler in an Option or a Try. You can
    * test it easily since the class provides some default handlers. Just start by importing it into your scope:
    * */

    val catchObj=scala.util.control.Exception.allCatch.opt(square("2"))
    println(s"catchObj value is : ${catchObj}")
    val catchObj2=scala.util.control.Exception.allCatch.opt(square("abc"))
    println(s"catchObj value is : ${catchObj2}")

    /* We can notice that, with .opt it handels exception,if an exception catched, it reutrns none*/

    val catchObj3=scala.util.control.Exception.allCatch.either(square("2"))
    println(s"catchObj value is : ${catchObj3}")
    val catchObj4=scala.util.control.Exception.allCatch.either(square("abc"))
    println(s"catchObj value is : ${catchObj4}")

    val catchObj5=scala.util.control.Exception.allCatch.withTry(square("2"))
    println(s"catchObj value is : ${catchObj5}")
    val catchObj6=scala.util.control.Exception.allCatch.withTry(square("abc"))
    println(s"catchObj value is : ${catchObj6}")

    /*.either and .withTry is very similar to .opt, it returns option like type
    * For .either, it reutrns Right(T) or Left(e:Exception)
    * For .withTry, it returns Success(T) or Failure(e:Exception)
    * */
  }

  /* In this method, read a string as argument, convert it to int return its square, if cant, throw
  IllegalArgumentException, Unlike java , I don't need to have throw statment in the method declaration*/
  def square(arg:String):Int={
    if(arg forall Character.isDigit)
      {val argInt=Integer.parseInt(arg.trim)
       return argInt*argInt
      }
    else throw new IllegalArgumentException
  }

  /*******************index *************************************/

  /*
  * The NonFatal extractor source code
  *
  * object NonFatal {
   /**
    * Returns true if the provided `Throwable` is to be considered non-fatal, or false if it is to be considered fatal
    */
   def apply(t: Throwable): Boolean = t match {
     case _: StackOverflowError => true // StackOverflowError ok even though it is a VirtualMachineError
     // VirtualMachineError includes OutOfMemoryError and other fatal errors
     case _: VirtualMachineError | _: ThreadDeath | _: InterruptedException | _: LinkageError | _: ControlThrowable | _: NotImplementedError => false
     case _ => true
   }
  /**
   * Returns Some(t) if NonFatal(t) == true, otherwise None
   */
  def unapply(t: Throwable): Option[Throwable] = if (apply(t)) Some(t) else None
}
  * */
}
