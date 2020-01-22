package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.util.*;

public class CollectionClassesExp {

    public static void exp1() {
        ArrayList<String> a1 = new ArrayList<>();
        System.out.println("Initial size of the al: " + a1.size());


        // add some elements, without index, it adds the element at the tail of the list
        a1.add("A");
        a1.add("B");
        a1.add("C");
        a1.add("D");
        a1.add("E");
        a1.add("F");
        // with index, it adds the element at the index position, and the existing element are shifted up
        int i = 1;
        System.out.println("Element at position " + i + " is " + a1.get(i));

        // add a new element at position i
        a1.add(i, "A2");
        // check now element at position i and i+1
        System.out.println("Element at position " + i + " is " + a1.get(i));
        System.out.println("Element at position " + (i + 1) + " is " + a1.get(i + 1));

        // get size after addition
        System.out.println("Current size of the al: " + a1.size());

        // show the content of the list
        System.out.println("Contents of the list: " + a1);

        // remove element
        a1.remove("F");

        // when we remove an element at position i, all elements behind i are shifted down
        a1.remove(i);

        // after remove check now element at position i and i+1
        System.out.println("Element at position " + i + " is " + a1.get(i));
        System.out.println("Element at position " + (i + 1) + " is " + a1.get(i + 1));

        // get size after remove
        System.out.println("Current size of the al: " + a1.size());

        // show the content of the list
        System.out.println("Contents of the list: " + a1);

        // reallocate the size of the array, The capacity is not publically exposed, so we can't get the capacity.
        a1.ensureCapacity(10);
        a1.trimToSize();
        // get size after remove, size is the count of elements inside the list. Not the total capacity.
        System.out.println("Size of the a1 after ensureCapacity(10): " + a1.size());
        a1.trimToSize();
    }

    public static void exp2() {
        ArrayList<Integer> a1 = new ArrayList<>();
        // autoboxing convert primitive int to object Integer
        a1.add(1);
        a1.add(2);
        a1.add(3);
        a1.add(4);

        // print the a1 content
        System.out.println("a1 content: " + a1);

        Integer[] a2 = new Integer[a1.size()];
        // we use the second overload version of the toArray method
        a2 = a1.toArray(a2);
        int sum = 0;
        for (int i : a2) {
            sum += i;
        }
        System.out.println("Sum is : " + sum);
    }

    public static void exp3() {
        LinkedList<String> l1 = new LinkedList<>();
        l1.add("F");
        l1.add("B");
        l1.add("D");
        l1.add("E");
        l1.add("C");
        l1.add("Z");
        l1.add("A");

        System.out.println("Initial Content of l1: " + l1);
        // even it's implemented with a linked list data structure, it still supports the index
        int i = 1;
        l1.add(i, "A2");

        System.out.println("After add(1,A2), content of l1: " + l1);

        //notice like array list, after remove, index changes, and elements behind are shifted.
        l1.remove("F");
        l1.remove(i);

        System.out.println("After remove, content of l1: " + l1);

        //use the method of Deque interface

        System.out.println("The first element of the list: " + l1.peekFirst());

        //note peekFirst() and peek() return the same thing
        l1.addFirst("F");
        System.out.println("The first element of the list after addFirst(F): " + l1.peek());


        /*toArray works also for the linked list*/
        LinkedList<Integer> l2 = new LinkedList<>();
        l2.add(1);
        l2.add(1);
        l2.add(1);
        l2.add(1);
        System.out.println("Initial content of l2 : " + l2);

        Integer a2[] = new Integer[l2.size()];
        a2 = l2.toArray(a2);
        int sum = 0;
        for (Integer j : a2) sum += i;
        System.out.println("Sum of l2: " + sum);
    }

    public static void exp4() {
        HashSet<String> hs = new HashSet<>();
        hs.add("A");
        hs.add("B");
        hs.add("C");
        hs.add("D");

        System.out.println("Initial content of hs: " + hs);
        // try to add duplicate
        boolean flag = hs.add("A");
        System.out.println("Adding duplicate element A : " + flag);

        // The returned copy Set is immutable, so we can't cast it to HashSet
        Set<String> copy = Set.copyOf(hs);
        System.out.println("Content of the copy of hs: " + hs);


    }

    public static void exp5() {
        LinkedHashSet<String> lhs = new LinkedHashSet<>();
        lhs.add("First");
        lhs.add("Second");
        lhs.add("Third");
        lhs.add("Fourth");
        lhs.add("Fifth");

        System.out.println("Initial content of lhs: " + lhs);
        // try to add duplicate
    }

    public static void exp6() {
        TreeSet<Integer> ts = new TreeSet<>();

        ts.add(10);
        ts.add(3);
        ts.add(7);
        ts.add(1);
        ts.add(5);

        System.out.println("Initial content of the tree: " + ts);

        // get a sub set between 2(not include) and 5(include)
        System.out.println("Subset between 2 and 5 of the tree: " + ts.subSet(2, false, 5, true));
    }

    public static void exp7() {
        // Creating empty priority queue
        PriorityQueue<String> pQueue = new PriorityQueue<String>();

        // Adding items to the pQueue using add()
        pQueue.add("C");
        pQueue.add("C++");
        pQueue.add("Java");
        pQueue.add("Python");

        // Printing the most priority element
        System.out.println("Head value using peek function:"
                + pQueue.peek());

        // Printing all elements
        System.out.println("The queue elements:");
        Iterator itr = pQueue.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());

        // Removing the top priority element (or head) and
        // printing the modified pQueue using poll()
        String removedObj = pQueue.poll();
        System.out.println("After removing an element " + removedObj + " with poll function:");
        Iterator<String> itr2 = pQueue.iterator();
        while (itr2.hasNext())
            System.out.println(itr2.next());

        // Removing Java using remove(), it returns boolean
        pQueue.remove("Java");
        System.out.println("after removing Java with"
                + " remove function:");
        Iterator<String> itr3 = pQueue.iterator();
        while (itr3.hasNext())
            System.out.println(itr3.next());
        // Check if an element is present using contains()
        boolean b = pQueue.contains("C");
        System.out.println("Priority queue contains C "
                + "or not?: " + b);

        // Getting objects from the queue using toArray()
        // in an array and print the array
        Object[] arr = pQueue.toArray();
        System.out.println("Value in array: ");
        for (int i = 0; i < arr.length; i++)
            System.out.println("Value: " + arr[i].toString());
    }

    public static void exp8() {
        // Intializing an deque
        Deque<Integer> de_que = new ArrayDeque<Integer>(10);

        // add() method to insert
        de_que.add(10);
        de_que.add(20);
        de_que.add(30);
        de_que.add(40);
        de_que.add(50);
        for (Integer element : de_que) {
            System.out.println("Element : " + element);
        }

        System.out.println("Using clear() ");

        // clear() method
        de_que.clear();

        // addFirst() method to insert at start
        de_que.addFirst(564);
        de_que.addFirst(291);

        // addLast() method to insert at end
        de_que.addLast(24);
        de_que.addLast(14);

        System.out.println("Above elements are removed now");

        // Iterator() :
        System.out.println("Elements of deque using Iterator :");
        for (Iterator itr = de_que.iterator(); itr.hasNext(); ) {
            System.out.println(itr.next());
        }

        // descendingIterator() : to reverse the deque order
        System.out.println("Elements of deque in reverse order :");
        for (Iterator dItr = de_que.descendingIterator();
             dItr.hasNext(); ) {
            System.out.println(dItr.next());
        }

        // element() method : to get Head element
        System.out.println("\nHead Element using element(): " +
                de_que.element());

        // getFirst() method : to get Head element
        System.out.println("Head Element using getFirst(): " +
                de_que.getFirst());

        // getLast() method : to get last element
        System.out.println("Last Element using getLast(): " +
                de_que.getLast());

        // toArray() method :
        Object[] arr = de_que.toArray();
        System.out.println("\nArray Size : " + arr.length);

        System.out.print("Array elements : ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(" " + arr[i]);

        // peek() method : to get head
        System.out.println("\nHead element : " + de_que.peek());

        // poll() method : to get head
        System.out.println("Head element poll : " + de_que.poll());

        // push() method :
        de_que.push(265);
        de_que.push(984);
        de_que.push(2365);

        // remove() method : to get head
        System.out.println("Head element remove : " + de_que.remove());

        System.out.println("The final array is: " + de_que);
    }

    public static void exp9() {

        // Creating a set
        EnumSet<Color> set1, set2, set3, set4;

        // Adding elements
        set1 = EnumSet.of(Color.Black, Color.Blue, Color.White, Color.Yellow);
        set2 = EnumSet.complementOf(set1);
        set3 = EnumSet.allOf(Color.class);
        set4 = EnumSet.range(Color.Yellow, Color.Blue);
        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        System.out.println("Set 3: " + set3);
        System.out.println("Set 4: " + set4);
    }

    public static void exp10() {
        ArrayList<String> a1 = new ArrayList<>();

        // add some elements, without index, it adds the element at the tail of the list
        a1.add("A");
        a1.add("B");
        a1.add("C");
        a1.add("D");
        a1.add("E");
        a1.add("F");

        System.out.println("Initial content of a1: " + a1);

        //get iterator
        Iterator<String> it = a1.iterator();
        while(it.hasNext()){
            String element=it.next();
            System.out.println("element : "+element);
            if(element.equals("F")) it.remove();
        }

        System.out.println("Current content of a1 after first iteration: " + a1);

        //get listIterator, note it has a constructor which you can specify the start point of the iterator with an index.
        ListIterator<String> lit = a1.listIterator();
        while (lit.hasNext()){
            String e=lit.next();
            lit.set(e+"+");
        }
        System.out.println("Current content of a1 after second iteration: " + a1);

        //now lit is pointing at the end of the list, we can cycle back by using previous
        while (lit.hasPrevious()){
            String e=lit.previous();
            lit.set(e+"+");
        }
        System.out.println("Current content of a1 after third iteration: " + a1);
    }

    public static void exp11(){
        ArrayList<Integer> a1 = new ArrayList<>();
        // autoboxing convert primitive int to object Integer
        a1.add(1);
        a1.add(2);
        a1.add(3);
        a1.add(4);
        a1.add(5);
        System.out.println("Content of a1: "+a1);
        int sum=0;

        // auto unboxing
        for(int i:a1){
            System.out.println("element: "+i);
            sum+=i;
        }
        System.out.println("Sum of values: "+sum);
    }

    public static void exp12(){
        ArrayList<Double> a1=new ArrayList<>();
        a1.add(1.0);
        a1.add(2.0);
        a1.add(3.0);
        a1.add(4.0);
        a1.add(5.0);
        System.out.println("Content of a1: "+a1);

        System.out.println("use tryAdvance to display contents of a1 ");
        // get a spliterator
        Spliterator<Double> split = a1.spliterator();
        // use tryAdvance to display contents
        while(split.tryAdvance(n-> System.out.println(n)));

        System.out.println("display the characteristics of a spliterator: "+split.characteristics());

        // the old iterator points to the end of the list, need to get a new instance which points the start
        split=a1.spliterator();
        // create a new list which contains square roots
        ArrayList<Double> a2=new ArrayList<>();
        while (split.tryAdvance(n->a2.add(Math.sqrt(n))));

        System.out.println("use orEachRemaining to display contents of a2: ");
        // use forEachRemaining to display contents of a2
        split=a2.spliterator();
        split.forEachRemaining(n->System.out.println(n));

    }

    public static void exp13(){
        ArrayList<Person> a1=new ArrayList<>();
        a1.add(new Person("toto",12));
        a1.add(new Person("titi",23));
        a1.add(new Person("tata",34));
        a1.add(new Person("foo",54));

        Spliterator<Person> split = a1.spliterator();
        while (split.tryAdvance(n->System.out.println("Element: "+n.toString())));
    }

    public static void exp14(){
        ArrayList<Person> a1=new ArrayList<>();
        LinkedList<String> l1=new LinkedList<>();
        System.out.println( "a1 instanceof RandomAccess is: "+ (a1 instanceof RandomAccess));
        System.out.println( "l1 instanceof RandomAccess is: "+ (l1 instanceof RandomAccess));
    }
    
}
