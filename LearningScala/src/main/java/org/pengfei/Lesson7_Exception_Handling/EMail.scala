package org.pengfei.Lesson7_Exception_Handling

object EMail {

  //The injection method (optional)
  def apply(user:String,domain:String)=user+"@"+domain


  // The extraction method (mandatory)

  /*
  * The unapply method is what turns EMail into an extractor. In a sense, it
  * reverses the construction process of apply. Where apply takes two strings
  * and forms an email address string out of them, unapply takes an email address
  * and returns potentially two strings: the user and the domain of the address.
  * But unapply must also handle the case where the given string is not
  * an email address. That’s why unapply returns an Option-type over pairs of
  * strings. Its result is either Some(user, domain)*/
  def unapply(str: String): Option[(String,String)] = {
    val parts= str.split("@")
    if (parts.length == 2) Some(parts(0),parts(1))
    else None
  }

  /* This object defines both apply and unapply methods. The apply method has the same
  * meaning as always: it turns EMail into an object that can be applied to arguments
  * in parentheses in the same way a method is applied. So you can write EMail("John", "epfl.ch")
  * to construct the string "John@epfl.ch". To make this more explicit, you could also let EMail
  * inherit from Scala’s function type, like this: object EMail extends ((String, String) => String) { ... }*/

}
