package org.pengfei.Lesson8_Scala_IO

import java.io.{BufferedReader, File, FileReader, PrintWriter}

import scala.io.{BufferedSource, Source, StdIn}

object Lesson8_Scala_IO {
def main(args:Array[String])={
  /* In this Lesson, we will see how scala do IO*/

  //javaIOOperation()
  scalaIOOperation()


}

  def scalaIOOperation():Unit={
    /*********************************************************************************************
      * ***************************************** 8.2 Use scala io********************************
      * ****************************************************************************************/

    /********************* Read line from command line *********************************/
    /* Above scala 2.11, use scala.io.StdIn.readLine(), Because Console.readLine*/
    /*println("Please enter your input: ")
    val line=StdIn.readLine()
    println("Thanks, you just typed: "+line)*/

    /********************** Reading File Content *************************************/
    val filePath="/tmp/test.txt"
    /*it returns a BufferedSource */
    val file:BufferedSource=Source.fromFile(filePath)
    //println(s"file value: ${file.getClass.getName}")
    /*it returns a bufferedLineIterator, we can get a List or Array from this iterator*/
    val lines=file.getLines()
    //val linesList=lines.toList
    //val linesArray=lines.toArray
    println(s"${lines.getClass.getName}")
    for(line<-lines){
      println(line)
    }
    // we must close after, otherwise it will be alive till the jvm close
    file.close()

    /*Scala does not offer any special file writing capabilities so far.*/
  }

  def javaIOOperation():Unit={
    /*********************************************************************************************
      * ***************************************** 8.1 Use java io***********************************
      * ****************************************************************************************/

    /* Scala is open to make use of any Java objects and java.io.File is one of the objects which can be used in Scala
    * programming to read and write files. In java, we have four main ways to write in a file
    * 1. FileOutputStream
    * 2. FileWriter
    * 3. PrintWriter
    * 4. OutputStreamWriter
    * PrintWriter offers some additional methods for formatting such as println and printf. In addition, FileWriter
    * throws IOException in case of any I/O failure. PrintWriter methods do not throws IOException, instead they set a
    * boolean flag which can be obtained using checkError(). PrintWriter automatically invokes flush after every byte
    * of data is written. In case of FileWriter, caller has to take care of invoking flush.*/

    /* Write to file*/
    val filePath="/tmp/test.txt"
    val file=new File(filePath)
    val writer = new PrintWriter(file)
    writer.println("Hello scala")
    writer.println("Another line")
    writer.write("third line")
    writer.write("Still in the third line")
    writer.close()

    /* Read lines from file, There are four main ways to read file
    * 1. BufferedReader
    * 2. Scanner
    * 3. Files
    * 4. RandomAccessFile*/
    val fileReader: FileReader = new FileReader(file)
    val bufferedReader=new BufferedReader(fileReader)
    val stringBuffer = new StringBuffer()
    /* In scala you can initialize variables to some default value, */
    var line:String = bufferedReader.readLine()
    while(line!=null){
      stringBuffer.append(line)
      stringBuffer.append("\n")
      line=bufferedReader.readLine()
    }
    fileReader.close()
    println("Contents of file: ")
    println(stringBuffer.toString)

  }
}
