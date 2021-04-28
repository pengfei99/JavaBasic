package org.pengfei.Lesson5_Basic_Data_Structure

import scala.collection.mutable

object Lesson5_Basic_Data_Structure {

  def main(args:Array[String]):Unit={

    //StringOperation()
    //TupleOperation()
    ListOperations()
    //ArrayOperations()
    //SetsOperations()
    //MapOperations()
    //VectorOperation()
    //SeqOperation()
  }

  /************************************************************************************
    * *******************************5.1 String **************************************
    * ********************************************************************************/
  def StringOperation():Unit={
    /* In Scala, as in Java, a string is an immutable object, that is, an object that cannot be modified.
    * On the other hand, objects that can be modified, like arrays, are called mutable objects. Strings are
    * very useful objects, we present important methods of java.lang.String class.*/
    val from:String="Foo Bar"
    var message:String="Hi, I love scala"
    val priority:Int=1

    /*************************** Length ********************************************/
    println("String length of message is: "+message.length())

    /********************************Concatenating Strings *************************/
    println(from.concat(message))
    /*****************************Format Strings **********************************/
    /* You have printf() and format() methods to print output with formatted numbers. The String class has an
    * equivalent class method, format(), that returns a String object rather than a PrintStream object.*/

    /* %s-> string, %d-> int %f->float*/
    val fs = printf ("Message %s from %s has priority %d", message,from,priority)
    println(fs)

    /************************** Scala String Interpolator *************************/
      /* The ‘s’ String Interpolator
       * The literal ‘s’ allows the usage of variable directly in processing a string, when you prepend ‘s’ to it.
       * Any String variable with in a scope that can be used with in a String. The following are the different
       * usages of ‘s’ String interpolator.*/
    val name="Toto"
    println(s"Hello, $name")
    /*Any arbitrary expression can be embedded in ${}*/
    println(s"1+1=${1+1}")
    /* The ‘ f ’ Interpolator
     * The literal ‘f’ interpolator allows to create a formatted String, similar to printf in C language.
     * While using ‘f’ interpolator, all variable references should be followed by the printf style format
     * specifiers such as %d, %i, %f, etc.
     *
     * Let us take an example of append floating point value (height = 1.9d) and String variable (name = “Toto”) with
     * normal string. The following code snippet of implementing ‘f’ Interpolator. Here $name%s to print
     * (String variable) James and $height%2.2f to print (floating point value) 1.90.

val height = 1.9d*/

    val height:Double=1.9d
    println(f"$name%s is $height%2.2f meters tall")

    /* The ‘raw’ interpolator is similar to ‘s’ interpolator except that it performs no escaping of literals
     * within a string. The following code snippets in a table will differ the usage of ‘s’ and ‘raw’
     * interpolators. In outputs of ‘s’ usage ‘\n’ effects as new line and in output of ‘raw’ usage the ‘\n’
     * will not effect. It will print the complete string with escape letters.*/
    println(s"Result = \n $name \n $height")
    println(raw"Result = \n $name \n $height")

    /* There are 46 method for string such as startsWith, endsWith, toLowerCase, toUpperCase etc.
    * You can check them at https://www.tutorialspoint.com/scala/scala_strings.htm*/


  }

  /************************************************************************************
    * *******************************5.2 Tuples(Collections) **************************************
    * ********************************************************************************/

  /* A tuple is a container for storing two or more elements of different types(unlike array or list, they must contain
   * elements of the same types). It is immutable; it cannot be modified after it has been created.
   *
   * Tuples is a implementation of Collections *******************/

  def TupleOperation():Unit={
    /********************************Tuple creation **************************************/
    val t=new Tuple3(1,"hello",Console)
    /*Tuple3 has 3 elements, Tuple10 has 10 elements, so it's better to use the short cut */
    val t1=(1,"hello",Console)

    /****************************Tuple element access **********************************/
    /* tuple index starts with 1 not 0*/
    val nums= (1,2,3,4)
    val sum=nums._1+nums._2+nums._3+nums._4
    println("Value of sum: "+sum)

    /************************** Iterate over the Tuple *******************************/
    nums.productIterator.foreach(i => println(s"Value = $i"))

    /****************************Swap Elements **************************************/
      /* swap only works for tuple2*/
    val t2=("foo","bar")
    println(t2.swap)
  }

  /************************************************************************************
    * *******************************5.3 Lists(Collections) ***************************
    * ********************************************************************************/

  /* A List is a linear sequence of elements of the same type. It is a recursive data structure, unlike an array, which
  * is a flat data structure. In addition, unlike an array, it is an immutable data structure; it cannot be modified
  * after it has been created. List is one of the most commonly used data structures in Scala and other functional languages.
  *
  * Although an element in a list can be accessed by its index, it is not an efficient data structure for
  * accessing elements by their indices. Access time is proportional to the position of an element in a list.
  *
  * Scala Lists are quite similar to arrays which means, all the elements of a list have the same
  * type but there are two important differences. First, lists are immutable, which means elements
  * of a list cannot be changed by assignment. */

  def ListOperations(): Unit ={

    /******************************* 5.3.1 Creating List ************************************/
    /* String List */
    val fruit1: List[String] = List("apples","oranges","pears")
    val fruit2: List[String] = List("bananas","watermelons")

    /* Int List */
    val nums: List[Int] = List(0,1,1,2,3,4)

    /* Two dimensional list */
    val dim: List[List[Int]]= List (
      List(1,0,0),
      List(0,1,0),
      List(0,0,1)
    )


    /****************************** 5.3.2 Basic operations on lists ************************/

    /* Get the first element */
    val firstFruit=fruit1.head
    println(firstFruit)
    /* tail method returns a list consisting of all elements except the first.*/
    val restOfTheFruit=fruit1.tail
    println(restOfTheFruit.toString())
    /* check empty or not */
    println(fruit2.isEmpty)

    /*************************** 5.3.3 Concatenating Lists ******************************/
      /* You can use either ::: operator or List.:::() method or List.concat() method to add two or more lists.*/
    val allFruits=fruit1:::fruit2
    val allFruits1=List.concat(fruit1,fruit2)
    /* when we add element to the list, it does change the origin list, it returns a new list
    * if you want simulate mutable list you can do as following var addElement*/
    var addElement=allFruits1:+"dragon balls"
    addElement=addElement:+"pengfei"
    println(" All fruits value after add element: "+ allFruits1)
    println("add Element value: "+addElement)

    /************************ 5.3.4 fill list with same values ***********************/

      /* fill 3 times apples */
    val apples = List.fill(3)("apples")
    println("apples value "+apples.toString())

    /*********************** 5.3.5 Tabulating a Function *****************************/

    val squareNums=List.tabulate(3)(n=>n*n)
    println("squareNums value: "+squareNums)
    val multi=List.tabulate(4,5)(_*_)
    println("mul value: "+multi)

    /*************************************************************************
    **********************Scala List operations******************************/

    /* Selects an element by its index in the list.*/
    val apple= fruit1.apply(0)
    println("Apple value "+apple)

    /*contains certain elemens in the list*/
    println("Has apple "+fruit1.contains("apples"))

    /* get a new list without any duplicate elements*/
    println(s"Distinct list: ${apples.distinct.toString()}")

    /* return all elements except first n ones*/
    val rest1=nums.drop(3)
    println(s"after drop : ${rest1.toString}")

    /* Returns all elements except last n ones.*/
    val rest2=nums.dropRight(3)
    println(s"after drop right: ${rest2.toString}")

    /* dropWhile removes the first element that match a predicate boolean function.
    * does not work well*/
    val dropWhile2=nums.dropWhile(_ - 2 == 0)
    println(s"after drop while ${dropWhile2.toString}")

    /************************************************************************************
      * ********************************Important method*********************************
      * **********************************************************************************/

    /* map : Evaluates a function over each element in the list, returning a list with the same number of elements*/
    val times2=nums.map(i=>i*2)
    println(s"times2 value ${times2.toString()}")


    /* foreach like map but returns nothing. foreach is intended for side-effects only*/
    nums.foreach(i=>println(i*2))

    /* filter : removes any elements where the function you pass in evaluates to false.*/
    val pairNums=nums.filter(i => i % 2 ==0)
    println(s"pairNums value ${pairNums.toString()}")

    /* zip aggregates the contents of two lists into a single list of pairs. If the number of elements
    * of two list is not equal, zip will stop for the short list*/

    val zipToPair=nums.zip(fruit1)
    println(s"zipToPair value ${zipToPair.toString()}")

    /* partition splits a list based on where it falls with respect to a predicate function, it will return
     * a list of list */
    val partitionList = nums.partition(i => i % 2 ==0)
    println(s"partitionList value: ${partitionList}")

    /* foldLeft :  can aggreage a function with each element of the list */
    val sumWithFold=nums.foldLeft(0){(m:Int,n:Int)=>println(s"m: $m, n: $n"); m+n}
    println(s"sumWithFold value $sumWithFold")
    /* 0 is the init value of m, m is the accumulator, n is the element of list, foldRight will do the same thing from
    * right side of the list*/

    /* flatten collapses one level of nested structure. if you have a list of list, you want to change it to list,
     * flatten is the function you need*/

    val flatList=dim.flatten
    println(s"flat List value: $flatList")

    /* flatMap takes a function that works on the nested lists and then concatenates the result back together,
       it returns a list, not list of list*/
    val flatMapList=dim.flatMap(x=>x.map(e=>e*2))
    println(s"flatMapList value: $flatMapList")

    /*We can do the above with map and flatten*/
    val flatMapList1=dim.map(x=>x.map(e=>e*2)).flatten
    println(s"flatMapList value: $flatMapList1")

    /* Reduce The reduce method returns a single value. As the name implies, it reduces a collection to a single value. The
     * input function to the reduce method takes two inputs at a time and returns one value. Essentially, the input
     * function is a binary operator that must be both associative and commutative.*/

    val sumByReduce = nums.reduce{(x,y)=>x+y}
    println(s"SumByReduce value is $sumByReduce")

  }

  /************************************************************************************
    * *******************************5.4 Array(Collections) ***************************
    * ********************************************************************************/

  /* An Array is an indexed sequence of elements. All the elements in an array are of the same type. It is a
  * mutable data structure; you can update an element in an array. However, you cannot add an element to an
  * array after it has been created. It has a fixed length */
  def ArrayOperations():Unit={

    /*create an empty array with array size*/
    val fruits:Array[String]=new Array[String](3)
    fruits(0)="apple"
    fruits(1)="banana"
    fruits(2)="organe"
    println(s"fruits value is ${fruits.toString}")
    fruits.foreach(println)
    /* create an array with all its element*/
    val nums=Array(1,2,3,4,5,6,1,3,5)
    println(s"First element of Array: ${nums(0)}")

    /*As array is mutable, so we can change the value of the 6er element*/
    println(s"six element of Array before: ${nums(5)}")
    nums(5)=10
    println(s"six element of Array after: ${nums(5)}")

    /*Concatenate two arrays*/
    val nums2=Array(10,11,12)
    val allNums=nums ++ nums2

    allNums.foreach(println)

    /******************************Important method *******************************/

      /*filter*/
    val paireNums:Array[Int]=nums.filter(x=>x%2==0)
    /*Array is a flat object, so to print element in it. It's harder to use .toString, we can use
    * foreach(println)*/
    println(s"paireNums value : ${paireNums.toString}")
    paireNums.foreach(println)
    /*foldright*/
    val sum=nums.foldRight(0){(m:Int,n:Int)=>m+n}
    println(s"sum of the fold $sum")

    /* You can notice that all method in List, we can call it in Array, because they implements the same interface
     * collection, for more operations just see the List section */

  }

  /************************************************************************************
    * *******************************5.5 Set(Collections) ***************************
    * ********************************************************************************/
  def SetsOperations():Unit={
    /* Set is an unordered collection of distinct elements. It does not contain duplicates. In addition, you cannot
     * access an element by its index, since it does not have one.
     *
     * There are two kinds of Sets, the immutable and the mutable. The difference between mutable and immutable
     * objects is that when an object is immutable, the object itself can't be changed.
     *
     * By default, Scala uses the immutable Set. If you want to use the mutable Set, you'll have to
     * import scala.collection.mutable.Set class explicitly. If you want to use both mutable and immutable sets
     * in the same collection, then you can continue to refer to the immutable Set as Set but you can refer to
     * the mutable Set as mutable.Set.*/

    var fruits:Set[String] = Set("apple","orange","pear","banana")
    val nums:Set[Int]=Set(1,2,3,4,5,5)

    /*Basic collection method, head, tail, isEmpty, contains works as other Collection implementations, but we can't
    * use apply to get element with it's index anymore, because set does not have index*/

    val firstFruit=fruits.head
    println(s"First fruit value: $firstFruit")
    val otherFruits=fruits.tail
    println(s"Tail of fruits value: $otherFruits")

    /*Concatenate two or more sets*/
    val myFruit:Set[String]=Set("water melon","dragon balls")
    val allFruits=myFruit ++ otherFruits
    val allFruits1=myFruit.++(otherFruits)

    /* find min, max, for string min, max works but has no sense */
    println(s" Min element in allFruits ${allFruits.min}")
    println(s" Max element in allFruits ${allFruits.max}")

    /* you can notice that, there is only one "5" in set nums, because set does not support duplicate */
    println(s" All elements in nums : $nums")
    println(s" Min element in nums: ${nums.min}")
    println(s" Max element in nums: ${nums.max}")

    /* find common values in sets, we can use Set.& or Set.intersection, we can do the diff also*/

    val nums2:Set[Int]=Set(2,5,7,8,9)
    val intersection=nums.intersect(nums2)
    println(s" Intersection of nums and nums2 : $intersection")

    /* the following will return Set(1,3,4), So Set1.diff(Set2) returns all elements in Set1 which does not in Set2  */
    val diff=nums.diff(nums2)
    println(s" Diff between nums and nums2 : $diff")

    /* mkString(sep: String): String
     * Displays all elements of this immutable set in a string using a separator string*/
val numString=nums.mkString(",")
    println(s"numString value is : $numString")
    val fruitString=allFruits.mkString(" ")
    println(s"fruitString value is : $fruitString")

  }

  /************************************************************************************
    * *******************************5.6 Map(Collections) ***************************
    * ********************************************************************************/
  def MapOperations():Unit={
    /* Map is a collection of key-value pairs. In other languages, it known as a dictionary, associative array, or hash
    * map. It is an efficient data structure for looking up a value by its key.
    *
    * Keys are unique in the Map, but values need not be unique. Maps are also called Hash tables. There are two
    * kinds of Maps, the immutable and the mutable. By default, Scala uses the immutable Map. If you want to use
    * the mutable Map, you'll have to import scala.collection.mutable.Map class explicitly. If you want to use both
    * mutable and immutable Maps in the same, then you can continue to refer to the immutable Map as Map but you can
    * refer to the mutable set as mutable.Map.*/

    val capitals:Map[String,String]=Map("USA"->"Washington D.C.", "UK" -> "London", "India" -> "New Delhi")
    val capitals1:Map[String,String]=Map("France"->"Paris","Germany"->"Berlin","USA"->"New York")
    val indiaCap=capitals.get("India")
    println(s"indiaCap value is : $indiaCap")

    val chinaCap=capitals.get("China")
    println(s"chinaCap value is : $chinaCap")

    /* You can notice that scala did not raise exception in chinaCap, it returns none, and indiaCap returns Some(String)
    * */

    /* Basic operation for a map*/

    /* Map.keys returns an iterable containing each key in the map(set of keys),
    * Map.values returns an iterable containing each value in the map*/
    println(s"keys in capitals : "+capitals.keys)
    println(s"values in capitals : "+capitals.values)

    /* Concatenate two maps, it will remove duplicate keys, the following example, washington D.C will be ecrase by
    * New york*/
    val allCapitals=capitals.++(capitals1)
    /* use keys.foreach to iterate all elements of a map*/
    allCapitals.keys.foreach{i=>
      print(s"key = $i, ")
      println(s"value = ${allCapitals(i)}")
    }

    /* Check existence of a key in Map*/

    println(s"Is china cap in the map: ${capitals.contains("China")}")

    /* remove a key from map, only works for mutable map*/
    val mutableCapitals=mutable.Map("USA"->"new york","China"->"Beijin")
    def newYork= mutableCapitals.remove("USA")
    println(s"new York value is : $newYork")
    println(s" map value after remoe: $mutableCapitals")


  }



  /************************************************************************************
    * *******************************5.7 Vector(Collections) ***************************
    * ********************************************************************************/
  def VectorOperation():Unit={
  /* The Vector class is a hybrid of the List and Array classes. It combines the performance characteristics of
   * both Array and List. It provides constant-time indexed access and constant-time linear access. It allows
   * both fast random access and fast functional updates.
   *
   * collection.immutable.Vector, it use Bitmapped Vector Trie as data structure*/

  val v1=Vector(0,10,20,30,40)

  /* Add element to the tail of a vector*/
  val v2=v1:+50

  println(s"Vector v1 value: $v1")
  println(s"Vector v2 value: $v2")

  /*get element of a vector, like array, index starts at 0*/
  val element3=v1(2)
    println(s"element3 value: $element3")
  }

  /************************************************************************************
    * *******************************5.8 Seq(Collections) ***************************
    * ********************************************************************************/
def SeqOperation():Unit={
  /*  Seq is a trait which represents indexed sequences that are guaranteed immutable. You can access elements by using
  * their indexes. It maintains insertion order of elements.
  *
  * Seq is also in the scala.collection.immutable.Seq
  *
  * Sequences support a number of methods to find occurrences of elements or subsequences. It returns a list.*/

  val numSeq:Seq[Int]=Seq(1,2,3,4,5,6,2)

  /*************************************Commonly used methods of Seq*********************************************/

  /*contains : check given element present in the seq*/
  println(s"Seq contains 3: ${numSeq.contains(3)}")

  /*copyToArray : copies the seq element to an array*/
  val numArray:Array[String]=new Array[String](5)
  //val res=numSeq.copyToArray(numArray,0,5)

  /* endsWith check if a seq ends with a subseq*/
  println(s"numSeq ends with Seq(5,6) : ${numSeq.endsWith(Seq(5,6))}")

  /* indexOf or lastIndexOf*/
  println(s"Index of 2 in numSeq: ${numSeq.indexOf(2)}")
  println(s"Last Index of 2 in numSeq: ${numSeq.lastIndexOf(2)}")

  /* head, tail, reverse like other*/

}

}
