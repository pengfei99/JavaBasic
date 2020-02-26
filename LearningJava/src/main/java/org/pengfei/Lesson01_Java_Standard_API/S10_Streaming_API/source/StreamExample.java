package org.pengfei.Lesson01_Java_Standard_API.S10_Streaming_API.source;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class StreamExample {

    public static void exp1(){
        int size=10;
        // prepare data source
        ArrayList<Integer> data=new ArrayList<>();
        for(int i=0;i<size;i++){
            data.add(new Random().nextInt(100));
        }

        // show the first 10 elements of the list
        System.out.println("The first 10 elements: ");
        for(int i=0;i<10;i++){
            System.out.print(data.get(i)+" ");
        }
        System.out.println();

        //obtain a stream of the array list
        Stream<Integer> myStream=data.stream();

        /*Perform some stream operation*/
        // get the min and max value of the list
        // note min() returns an value of type Optional(like scala). An Optional instance can either contain a value
        // or be empty. We can check if a value is present by using isPresent(). This can avoid null exception.
        // Assuming	that a value is available, it can be obtained by calling get(), or if you are using JDK 10 or later,
        // orElseThrow().

        Optional<Integer> min = myStream.min(Integer::compareTo);
        // here, Integer::compareTo refers the compareTo() method of the Integer class. It compares two Integer Objs
        if(min.isPresent()) System.out.println("Min value: "+min.get());

        // notice, if we don't obtain a new stream here, we get an exception, because min, max are terminal operation
        // which consume the stream. It means the stream are not usable anymore after calling a terminal operation.
        myStream=data.stream(); // try to comment out this line, and see what happens.
        Optional<Integer> max = myStream.max(Integer::compareTo);
        if(max.isPresent()) System.out.println("Max value: "+max.get());

        // sort the stream
        myStream=data.stream();
        Stream<Integer> sortedStream=myStream.sorted();
        // Display the elements in the stream by using forEach(), here forEach is a terminal operation
        System.out.println("Sorted stream: ");
        sortedStream.forEach((n)->System.out.print(n+" "));
        System.out.println();

        // get a stream which only contains the odd value
        myStream=data.stream();
        // sorted() is intermediate, so we can combine it with filter
        Stream<Integer> oddValues=myStream.sorted().filter((n)-> (n%2)==1);
        // display the elements
        System.out.println("Odd values: ");
        oddValues.forEach((n)->System.out.print(n+" "));
        System.out.println();

        // get a stream which only contains the odd value and > 50
        myStream=data.stream();
        // sorted(), filter() is intermediate, so we can combine them
        oddValues=myStream.sorted().filter((n)-> (n%2)==1).filter((n)->(n>50));
        // display the elements
        System.out.println("Odd values which is greater than 50: ");
        oddValues.forEach((n)->System.out.print(n+" "));
        System.out.println();

    }
}
