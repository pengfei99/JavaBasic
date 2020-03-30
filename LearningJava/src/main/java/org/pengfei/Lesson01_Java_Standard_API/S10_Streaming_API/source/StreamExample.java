package org.pengfei.Lesson01_Java_Standard_API.S10_Streaming_API.source;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

    public static void exp1() {
        int size = 10;
        // prepare data source
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(new Random().nextInt(100));
        }

        // show the first 10 elements of the list
        System.out.println("The first 10 elements: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(data.get(i) + " ");
        }
        System.out.println();

        //obtain a stream of the array list
        Stream<Integer> myStream = data.stream();

        /*Perform some stream operation*/
        // get the min and max value of the list
        // note min() returns an value of type Optional(like scala). An Optional instance can either contain a value
        // or be empty. We can check if a value is present by using isPresent(). This can avoid null exception.
        // Assuming	that a value is available, it can be obtained by calling get(), or if you are using JDK 10 or later,
        // orElseThrow().

        Optional<Integer> min = myStream.min(Integer::compareTo);
        // here, Integer::compareTo refers the compareTo() method of the Integer class. It compares two Integer Objs
        if (min.isPresent()) System.out.println("Min value: " + min.get());

        // notice, if we don't obtain a new stream here, we get an exception, because min, max are terminal operation
        // which consume the stream. It means the stream are not usable anymore after calling a terminal operation.
        myStream = data.stream(); // try to comment out this line, and see what happens.
        Optional<Integer> max = myStream.max(Integer::compareTo);
        if (max.isPresent()) System.out.println("Max value: " + max.get());

        // sort the stream
        myStream = data.stream();
        Stream<Integer> sortedStream = myStream.sorted();
        // Display the elements in the stream by using forEach(), here forEach is a terminal operation
        System.out.println("Sorted stream: ");
        sortedStream.forEach((n) -> System.out.print(n + " "));
        System.out.println();

        // get a stream which only contains the odd value
        myStream = data.stream();
        // sorted() is intermediate, so we can combine it with filter
        Stream<Integer> oddValues = myStream.sorted().filter((n) -> (n % 2) == 1);
        // display the elements
        System.out.println("Odd values: ");
        oddValues.forEach((n) -> System.out.print(n + " "));
        System.out.println();

        // get a stream which only contains the odd value and > 50
        myStream = data.stream();
        // sorted(), filter() is intermediate, so we can combine them
        oddValues = myStream.sorted().filter((n) -> (n % 2) == 1).filter((n) -> (n > 50));
        // display the elements
        System.out.println("Odd values which is greater than 50: ");
        oddValues.forEach((n) -> System.out.print(n + " "));
        System.out.println();

    }

    public static void exp2() {
        ArrayList<Integer> myList = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            myList.add(r.nextInt(10));
        }

        //show the element of the list
        System.out.println(myList.toString());
        //get stream from list
        Stream<Integer> myStream = myList.stream();

        // use the first version of reduce +
        // Integer in the stream are converted to int via auto-unboxing.
        Optional<Integer> result1 = myStream.reduce((a, b) -> a + b);
        System.out.println("The result of + first version: " + result1.get());

        // use the second version of reduce +, with identityVal=0, reduce consume the steam. So we need to create a
        // new stream to use reduce again.
        Integer result2 = myList.stream().reduce(0, (a, b) -> a + b);
        System.out.println("The result of + second version: " + result2);


        // use the first version of reduce *

        Optional<Integer> result3 = myList.stream().reduce((a, b) -> a * b);
        System.out.println("The result of * first version: " + result3.get());

        // use the second version of reduce, with identityVal=1, reduce consume the steam. So we need to create a
        // new stream to use reduce again.
        Integer result4 = myList.stream().reduce(1, (a, b) -> a * b);
        System.out.println("The result of * second version: " + result4);


        // get the product of only the even values with reduce
        // note in reduce, the accumulator is a function. It must return a value.
        // Here, we need to use the second version. Because, a holds the current result and b holds the next element.
        // At the beginning, a is the identityVal which is 1, and b is the first element of the stream.
        Integer result5 = myList.stream().reduce(1, (a, b) -> {
            if (b % 2 == 0) return a * b;
            else return a;
        });
        System.out.println("The result of even value product: " + result5);
    }


    public static void exp3() {
        ArrayList<Integer> myList = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            myList.add(r.nextInt(10));
        }

        //show the element of the list
        System.out.println(myList.toString());
        //get parallel stream from list
        Stream<Integer> myParallelStream = myList.parallelStream();


        // parallel stream on a normal multi reduce
        // here, identityVal=1, the first (a,b)->a*b is the accumulator, the second (a,b)->a*b is the combiner, which
        // combines the result returned by the accumulator.
        Integer result1 = myParallelStream.reduce(1, (a, b) -> a * b, (a, b) -> a * b);
        System.out.println("The result of parallel multi: " + result1);



        /* parallel stream on a power 2 sum, you can notice, in this reduce, the accumulator and combiner are different
         * if we omit the combiner, use reduce(0, (a, b) -> (a + (b * b))); System will take the accumulator also as
         * the combiner, which will return a false result.
         *  */
        Integer result2 = myList.parallelStream().reduce(0, (a, b) -> (a + (b * b)), (a, b) -> a + b);
        System.out.println("The result of sum of power 2: " + result2);

    }

    public static void exp4() {
        ArrayList<Integer> myList = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            myList.add(r.nextInt(10));
        }

        //show the element of the list
        System.out.println(myList.toString());

        //get stream from list
        Stream<Integer> myStream = myList.stream();

        //we use the map and reduce together to reproduce the second parallel stream reduce of exp3.
        Integer result = myStream.map(a -> a * a).reduce(0, (a, b) -> a + b);

        System.out.println("The result of sum of power 2: " + result);
    }

    public static void exp5() {
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();

        myList.add(new NamePhoneEmail("alice", "0101010", "alice@pengfei.org"));
        myList.add(new NamePhoneEmail("bob", "0121030", "bob@pengfei.org"));
        myList.add(new NamePhoneEmail("mike", "0102015", "mike@pengfei.org"));

        Stream<NamePhone> namePhoneStream = myList.stream().map(a -> new NamePhone(a.name, a.phoneNum));
        namePhoneStream.forEach(a -> System.out.println("Name: " + a.name + ", phone: " + a.phoneNum));

        // combine map with other operations.

        // before map, we can filter
        System.out.println("The content of filtered stream");
        Stream<NamePhone> filteredStream = myList.stream().filter(a -> (a.name == "bob"))
                .map(a -> new NamePhone(a.name, a.phoneNum));
        filteredStream.forEach(a -> System.out.println("Name: " + a.name + ", phone: " + a.phoneNum));

    }

    public static void exp6() {
        ArrayList<Double> myList = new ArrayList<>();
        //Random r=new Random();

        /*for(int i=0;i<10;i++){
            // he nextDouble() method only return value between 1 and 0.
            myList.add(r.nextDouble());
        }*/
        myList.add(3.1);
        myList.add(1.4);
        myList.add(2.0);
        myList.add(1.6);
        System.out.println(myList.toString());

        IntStream intStream = myList.stream().mapToInt(a -> (int) Math.ceil(a));
        System.out.println("The stream contents: ");
        intStream.forEach(a -> System.out.print(a));
    }

    public static void exp7() {
        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //it will convert String[][] to Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);

        //We use flatMap convert Stream<String[]> to Stream<String>
        Stream<String> stringStream = temp.flatMap(x -> Arrays.stream(x));
        System.out.println("The stream contents: ");
        stringStream.forEach(System.out::print);
        System.out.println();


        Stream<String> stream = Arrays.stream(data) // stream of string array
                .flatMap(x -> Arrays.stream(x)) // stream of string
                .filter(x -> "a".equals(x.toString())); // filter the stream
        System.out.println("The filtered stream contents: ");
        stream.forEach(System.out::println);


    }

    public static void exp8() {
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();

        myList.add(new NamePhoneEmail("alice", "0101010", "alice@pengfei.org"));
        myList.add(new NamePhoneEmail("bob", "0121030", "bob@pengfei.org"));
        myList.add(new NamePhoneEmail("mike", "0102015", "mike@pengfei.org"));

        Stream<NamePhone> namePhoneStream = myList.stream().map(a -> new NamePhone(a.name, a.phoneNum));
        System.out.println("Collect stream elements to a list");
        List<NamePhone> nList = namePhoneStream.collect(Collectors.toList());
        for (NamePhone np : nList) {
            System.out.println(np.name + " : " + np.phoneNum);
        }

        System.out.println("Collect stream elements to a Set");
        Set<NamePhone> nSet = myList.stream().map(a -> new NamePhone(a.name, a.phoneNum)).collect(Collectors.toSet());
        for (NamePhone np : nSet) {
            System.out.println(np.name + " : " + np.phoneNum);
        }
    }

    public static void exp9() {
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();

        myList.add(new NamePhoneEmail("alice", "0101010", "alice@pengfei.org"));
        myList.add(new NamePhoneEmail("bob", "0121030", "bob@pengfei.org"));
        myList.add(new NamePhoneEmail("mike", "0102015", "mike@pengfei.org"));

        Stream<NamePhone> namePhoneStream = myList.stream().map(a -> new NamePhone(a.name, a.phoneNum));
        System.out.println("Collect stream elements to a list");
        // Here we use the second version of the collect method.
        /*
        * ()->new LinkedList<>() defines that target is a linked list
        * (list, element)->list.add(element) defines that collector add each element into the linked list
        * (listA,listB)->listA.addAll(listB) defines that combiner combines partial lists to the final list
        * */
        List<NamePhone> nList = namePhoneStream.collect(
                ()->new LinkedList<>(),
                (list, element)->list.add(element),
                (listA,listB)->listA.addAll(listB)

        );
        for (NamePhone np : nList) {
            System.out.println(np.name + " : " + np.phoneNum);
        }


        System.out.println("Collect stream elements to a Set");
        // In this version, we don't use the lambda expression in the collect method. We use the method and/or
        // constructor references
        /*
        * HashSet::new -> It calls the HashSet constructor new. It equals lambda expression: ()->new HashSet<>()
        * HashSet::add -> It calls the HashSet method add(). It equals lambda expression: (set,element)-> set.add(element)
        * HashSet::addAll -> It calls the HashSet method addAll(). It equals lambda expression: (setA,setB)-> setA.addAll(setB)
         * */
        Set<NamePhone> nSet = myList.stream().map(a -> new NamePhone(a.name, a.phoneNum))
                .collect(HashSet::new,
                        HashSet::add,
                        HashSet::addAll);
        for (NamePhone np : nSet) {
            System.out.println(np.name + " : " + np.phoneNum);
        }
    }
}
