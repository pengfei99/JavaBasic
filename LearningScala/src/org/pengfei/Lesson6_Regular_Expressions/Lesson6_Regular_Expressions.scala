package org.pengfei.Lesson6_Regular_Expressions

import scala.util.control.Breaks
import scala.util.matching.Regex

object Lesson6_Regular_Expressions {

  /*
  * Regular expressions are patterns that permit you to “match” various string values in a variety of ways.
  * Scala uses import scala.util.matching.Regex to implement regular expression concept.
  *
  * A pattern is simply one or more characters that represent a set of possible match characters. In regular
  * expression matching, you use a character or set of characters to represent the strings you want to match in
  * the text. A regular expression is a way of describing a set of strings using common properties for example,
  * strings that start with an “A” and end with an exclamation mark.*/
def main(args:Array[String]): Unit ={
/***********************************************************************************************
  * ************************* 6.1 Basic functions *********************************************
  * *******************************************************************************************/
  /* first you need to define a pattern, the numberPattern defines a single number pattern*/
  val numberPattern:Regex="[0-9]".r
  /* A regex pattern provides many method such as findFirstMatchIn, It will return a Some(String) or None
  * */
  findMatch("Pengfei1",numberPattern)
  /* findAllIn will return a scala.util.matching.Regex.MatchIterator, if findAllIn find any matches, it will return
  a non-empty iterator, otherwise it will return an empty iterator*/
  val allMatches=numberPattern.findAllIn("Pengfei1 phone number 123456")
  allMatches.foreach(println)

  /* This iterator can be transformered into many implementation of the collection such as toArray, toList, toSeq,
     toVector*/


  /***********************************************************************************************
    * ************************* 6.2 Regular expression Examples *********************************************
    * *******************************************************************************************/

  /* Match Hello or hi*/
  val  greetingPattern:Regex="(hi)|(Hello)".r
  /* Note that the white space between () in the pattern is important, "([hH])(i|(ello))" "([hH]) (i|(ello))" is
  * different. with space it will match H ello, h ello, h i or H i*/
  val greetingPattern1:Regex="([hH])(i|(ello))".r
  val greetingMatches=greetingPattern.findAllIn("Hello hi hachite hali hi")
  greetingMatches.foreach(println)

  val greetingMatches1=greetingPattern1.findAllIn("Hello hi hachite hali hi")
  greetingMatches1.foreach(println)

  /* Match text start with digits followed by letters, example 1000 bananas*/

  val complexPattern1:Regex="([0-9]+) ([A-Za-z]+)".r
  val complexPMatches1=complexPattern1.findAllIn("100 apples, none orange, 88 bananas")
  complexPMatches1.foreach(println)

  /* Match validate dates format, "Jan 11, 2015" "11 Jan, 2015" "01/11/2015" "11-01-2015" */
  val dateExamples="Jan 11, 2015 11 Jan, 2015 01/11/2015 11-01-2015 jan 111 12 2013"
  /*Pattern1 matches Jan 11, 2015*/
  val datePattern1:Regex="([A-Z][a-z]+) (\\d{2})(,) (\\d{4})".r
  val dateMatche1=datePattern1.findAllIn(dateExamples)
   dateMatche1.foreach(println)

  /*Pattern2 matches 11 Jan, 2015*/
  val datePattern2:Regex="(\\d{2}) ([A-Z][a-z]+)(,) (\\d{4})".r
  val dateMatche2=datePattern2.findAllIn(dateExamples)
  dateMatche2.foreach(println)

  /*Pattern3 matches 01/11/2015, 11-01-2015*/
  val datePattern3:Regex="(\\d{2})(/|-)(\\d{2})(/|-)(\\d{4})".r
  val dateMatche3=datePattern3.findAllIn(dateExamples)
  dateMatche3.foreach(println)

  /*check if a date is validate*/
  val patterns=List(datePattern1,datePattern2,datePattern3)
  val res=checkDateValidation(dateExamples,patterns)
  println(s"dateExamples has valide date: ${res}")

  println(s"BadDateExamples has valide date: ${checkDateValidation("2015",patterns)}")}

  def findMatch(source:String,pattern:Regex):Unit={
    pattern.findFirstMatchIn(source) match {
      case Some(x) => println(s"Find first match: $x")
      case None => println(s"Find nothing")
    }
  }

  def checkDateValidation(date:String,patterns:List[Regex]):Boolean={
    var result:Boolean=false
    val loop=Breaks
    loop.breakable{
      for(pattern<-patterns){
        if (!pattern.findAllIn(date).isEmpty){
          result=true
          loop.break
        }
      }
    }
    return result
  }


  /* reg exp index*/

  /*
  *
  * Subexpression	|  Matches
  * ^	Matches  |  beginning of line.
  * $	| Matches end of line.
  * .	   |  Matches any single character except newline. Using m option allows it to match newline as well.
  * [...]	| Matches any single character in brackets.
  * [^...]	| Matches any single character not in brackets
  * \\A |  Beginning of entire string
  * \\z	  | End of entire string
  * \\Z	 | End of entire string except allowable final line terminator.
  * re*	|  Matches 0 or more occurrences of preceding expression.
  * re+	|  Matches 1 or more of the previous thing
  * re?	| Matches 0 or 1 occurrence of preceding expression.
  * re{n}	| Matches exactly n number of occurrences of preceding expression.
  * re{n,} |	Matches n or more occurrences of preceding expression.
  * re{ n, m}	| Matches at least n and at most m occurrences of preceding expression.
  * a|b	| Matches either a or b.
  * (re)	| Groups regular expressions and remembers matched text.
  * (?: re)	| Groups regular expressions without remembering matched text.
  * (?> re)	| Matches independent pattern without backtracking.
  * \\w	| Matches word characters.
  * \\W	| Matches nonword characters.
  * \\s	|  Matches whitespace. Equivalent to [\t\n\r\f].
  * \\S	|  Matches nonwhitespace.
  * \\d	| Matches digits. Equivalent to [0-9].
  * \\D	| Matches nondigits.
  * \\A	| Matches beginning of string.
  * \\Z	| Matches end of string. If a newline exists, it matches just before newline.
  * \\z	| Matches end of string.
  * \\G	| Matches point where last match finished.
  * \\n	| Back-reference to capture group number "n"
  * \\b	| Matches word boundaries when outside brackets. Matches backspace (0x08) when inside brackets.
  * \\B	| Matches nonword boundaries.
  * \\n, \\t, etc.	| Matches newlines, carriage returns, tabs, etc.
  * \\Q	| Escape (quote) all characters up to \\E
  * \\E	| Ends quoting begun with \\Q
  * */
}
