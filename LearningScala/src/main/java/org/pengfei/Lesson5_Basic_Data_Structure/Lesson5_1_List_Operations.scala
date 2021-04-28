package org.pengfei.Lesson5_Basic_Data_Structure

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}

object Lesson5_1_List_Operations {
  def main(args: Array[String]): Unit = {
    foldExp1()
    foldExp2()
    foldExp3()
  }

  /** ************************************* Fold, FoldLeft, FoldRight *************************************************/

  /*
  * fold, foldLeft and FoldRight can loop through a list. Then apply an aggregate function on each element of this list.
  * It has four arguments:
  * - start_value
  * - accumulator
  * - current_element
  * - a function which apply on accumulator and current_element
  * */

  /** ************************************** 1. Simple example *******************************************************/
  /*
  * In this simple example, the start_value=0. The function is the addition of the accumulator and the current element
  * In the first turn of the loop, in the accumulator takes the value of start_value. In the second turn, it takes the
  * value of the result returned by the first turn. In the fold function, we don't have specific order when loop through
  * the list.
  */
  def foldExp1() = {
    val numbers = List(5, 4, 8, 6, 2)
    val start_value = 0
    val sum = numbers.fold(start_value) { (accumulator, current_element_of_list) =>
      accumulator + current_element_of_list
    }
    println(s"The sum of list numbers is ${sum}")
  }

  /************************************ 2. Advance example ******************************************************/
  /*
* In this example, the start_value is an empty List of String. The function convert a Person object to
* a String(also add a title to the person's name), then add the String to the accumulator.
*
* This time, we use foldLeft, we will loop through the list from left to right in order.
*/
  def foldExp2() = {
    val personList = Person("Hugh Jass", 25, 'male) ::
      Person("Biggus Dickus", 43, 'male) ::
      Person("Incontinentia Buttocks", 37, 'female) ::
      Nil
    val start_value = List[String]()
    val stringList = personList.foldLeft(start_value) { (accumulator, current_element) =>
      val title = current_element.sex match {
        case 'male => "Mr."
        case 'female => "Ms."
      }
      accumulator :+ s"$title ${current_element.name}, ${current_element.age}"
    }

    println(s"First element of the list is ${stringList(0)}")
    // Mr. Hugh Jass, 25
    println(s"Third element of the list is ${stringList(2)}")
    // Ms. Incontinentia Buttocks, 37

  }

  /************************************* 3. Ultimate example ************************************************/
  /*
* In this example, the start_value is a data frame. The function rename the column name. The rename function lower
* the letter case, then replace all space by "_". sourceDF.columns returns a list of column name of the data frame.
*
*/
  def foldExp3()={
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val spark = SparkSession.builder().master("local[2]").appName("test").getOrCreate()
    import spark.implicits._
    val sourceDF = Seq(("funny", "joke")).toDF("A b C", "de F")

    val renamedDf=sourceDF.columns.foldLeft(sourceDF) { (accumulator, columnName) =>
      accumulator.withColumnRenamed(columnName, columnName.toLowerCase().replace(" ", "_"))
    }
    renamedDf.show()
  }

  /***************** 4. The common point and difference between fold, foldLeft, and foldRight ********************/

  /** 4.1 The common point:
    * - They all apply an aggregate function on the list by using a start_value
    * - The type of the start value must be the same as the return value
    * */


  /** 4.2 The primary difference is the order in which the fold operation iterates through the collection in question.
  * - foldLeft: It starts on the left side and iterates to the right;
  * - foldRight: It starts on the right side and iterates to the left.
  * - fold: It has no particular order.
  *
  * Because fold does not go in any particular order, there are constraints on the start value and thus return value
  * - The first constraint: It is that the start value must be a supertype of the object you're folding. In our
    *                       first example we were folding on a List[Int]. As a result the start value type must
    *                       be Int. Int is a supertype of List[Int].
    * The second constraint: The start value must be neutral. It means the start value must not change the result if
    *                        items of the list are applied in different order. For example, the neutral value for an
    *                        addition operation would be 0; 1 for multiplication; Nil for lists concatenation, etc.
    * */
}
