package org.pengfei.Lesson3_Classes

object Lesson3_Classes {
def main(args:Array[String]):Unit={

  /**************************************************************************************************************
    * *************************** 3. Classe Introduction ******************************************************
    * **********************************************************************************************************/

  /* A class is an object-oriented programming concept. It provides a higher-level programming abstraction. At
  * a very basic level, it is a code organization technique that allows you to bundle data and all of its operations
  * together. Conceptually, it represents an entity with properties and behavior.
  *
  * A class in Scala is similar to that in other object-oriented languages. It consists of fields and methods.
  * A field is a variable, which is used to store data. A method contains executable code. It is a function defined
  * inside a class. A method has access to all the fields of a class.
  *
  * A class is a template or blueprint for creating objects at runtime. An object is an instance of a class. A
  * class is defined in source code, whereas an object exists at runtime. A class is defined using the keyword
  * class. A class definition starts with the class name, followed by comma-separated class parameters in
  * parentheses, and then fields and methods enclosed in curly braces.*/

  /*********************************************************************************************************
    * ****************************** 3.1 Class constructor***********************************************
    * *****************************************************************************************************/
  /* The class person takes a firstName and a lastName, then build a fullName as parameter, it has a getter on
  * the fullName*/

  //val toto=new Person("toto","happy")

  /* We can notice, unlike java, scala consider the whole body as constructor, it will execute all the code inside his
  * class body*/

  /* If your classes needs constructors with different parameters, you can use auxiliary constructors*/

//  val p1=new Person_aux_constructors("toto","happy")
//  println(p1.fullName)
//  println(p1.age)
//  println(p1.address)
//
//  val p2=new Person_aux_constructors(18,"toto","happy")
//  println(p2.fullName)
//  println(p2.age)
//  println(p2.address)
//
//  val p3=new Person_aux_constructors("China",18,"toto","happy")
//  println(p3.fullName)
//  println(p3.age)
//  println(p3.address)



  /*********************************************************************************************************
    * ****************************** 3.2 Class parameters visibilities*************************************
    * *****************************************************************************************************/

    /* When we define a class, if we put var before all parameters, it can be get and set without writing specific
    * getter and setter*/
 val toto1= new Person1("toto","happy")
  println(toto1.firstName)
  println(toto1.lastName)
  println(toto1.fullName)
  toto1.firstName="titi"
  toto1.lastName="notHappy"
  println(toto1.firstName)
  println(toto1.lastName)
  println(toto1.fullName)
  toto1.fullName="notUpdated"
  println(toto1.fullName)

  /* When we define a class, if we put val before all parameters, it can be get without writing specific
   * getter, but it can't be reset, because val is not mutable*/
  val toto2= new Person2("toto","happy")
  println(toto2.firstName)
  println(toto2.lastName)
  println(toto2.fullName)
  //toto2.firstName="titi"
  //toto2.lastName="notHappy"
  //toto2.fullName="notUpdated"

  /* When we define a class, if we put nothing before all parameters, the parameters can't be get or set without
   * writing specific getter and setter. But the classe field can be get*/
  val toto3= new Person3("toto","happy")
  //println(toto3.firstName)
  //println(toto3.lastName)
  println(toto3.fullName)
  println("My getter return: "+toto3.getName())

  /* When we define a classe, if we put private in front of fields, the fields can't be get anymore, you have to write
  * your own getter*/
  val toto4= new Person4("toto","happy")
  println(toto4.getName())


  /* A class is generally used as a mutable data structure. An object has a state, which changes with time.
   * Therefore, a class may have fields that are defined using var.
   *
   * Since Scala runs on the JVM, you do not need to explicitly delete an object. The Java garbage collector
   * automatically removes objects that are no longer in use.*/

  /*
  * **************************Access modifier***************************************/

  /*
  * All fields and methods can use access modifier(e.g. private, protected, public)
  * A private member is visible only inside the class or object that contains the member definition.
  * A protected member is only accessible from subclasses of the class in which the member is defined.
  * A public member can be accessed from anywhere, unlike private, protected, there is no explicit modifier
  * for public members. In another word, by default in scala, verything is public*/

  /*********************************************************************************************************
    * ****************************** 3.3 Case class*************************************
    * *****************************************************************************************************/

  /* Scala provides a few syntactic conveniences to a case class.
   *
   * First, it creates a factory method with the same name. Therefore, you can create an instance of
   * a case class without using the keyword new. For example, the following code is valid.*/


  val request =Message("toto","titi","I miss you so much")

  /*
  * Second, all parameters of case class implicitly get a val prefix. For example case class message is equal to
  * class Message(val from: String, val to: String, val content: String), This means all parameters can be accessible
  * from out side the the class*/

  println(request.from)
  println(request.to)
  println(request.content)

  /*
  * Third, Scala adds toString,hashCode,equals and copy to case class as default methods.*/

  val request2=request.copy()
  println("Value of request copy: "+request2.toString)
  println(request.equals(request2))
 /* if you give nothing as parameters in copy, it will copy exact the same thing, You can also modify certain parameters
  *  */
  val request3=request.copy("haha")
  println("Value of request copy with parameters: "+request3.toString)
  println(request.equals(request3))

  /*********************************************************************************************************
    * ************************************** 3.4 Singletons *****************************************
    * *****************************************************************************************************/

  /*
  * One of the common design patterns in object-oriented programming is to define a class that can be
 * instantiated only once. A class that can be instantiated only once is called a singleton. Scala provides the
 * keyword object for defining a singleton class.*/

  println(Toto.firstName)
  println(Toto.lastName)
  println(Toto.fullName)
  Toto.firstName="titi"
  Toto.lastName="notHappy"
  println(Toto.firstName)
  println(Toto.lastName)

  /* Object provide access to its parameters, if you use val or var*/

  /*********************************************************************************************************
    * ************************************** 3.5 Abstract class *****************************************
    * *****************************************************************************************************/

  /*
   * A class which is declared with abstract keyword is known as abstract class. An abstract class can
   * have abstract methods and non-abstract methods as well. Abstract class is used to achieve abstraction.
   * Abstraction is a process in which we hide complex implementation details and show only functionality to
   * the user.*/

  val driver0=new ADriver("foo","bar")
  driver0.drive()
  val carType0=driver0.whichCarICanDrive()
  println(carType0)
  println(driver0.getName())

  /*********************************************************************************************************
    * ************************************** 3.6 Traits *****************************************
    * *****************************************************************************************************/

  /*
  * A trait represents an interface supported by a hierarchy of related classes. It is an abstraction mechanism
  * that helps development of modular, reusable, and extensible code.
  *
  * Conceptually, an interface is defined by a set of methods. An interface in Java only includes method
  * signatures. Every class that inherits an interface must provide an implementation of the interface methods.
  *
  * Scala traits are similar to Java interfaces. However, unlike a Java interface, a Scala trait can include
  * implementation of a method. In addition, a Scala trait can include fields. A class can reuse the fields and
  * methods implemented in a trait.
  *
  * A trait looks similar to an abstract class. Both can contain fields and methods. The key difference is that
  * 1. A class can inherit from only one abstract class, but it can inherit from any number of traits.
  * 2. Abstract class are fully interoperable with java, Traits are fully interoperable only if they do not contain
  *    any implementation code
  * 3. Abstract classes can have constructor parameters as well as type parameters. Traits can have only type
  *    parameters. There was some discussion that in future even traits can have constructor parameters
  *    */

  val driver1=new Driver("titi","happy")
  driver1.drive()
  val carType=driver1.whichCarICanDrive()
  println(carType)
}

  case class Message(from:String,to:String,content:String)


}
