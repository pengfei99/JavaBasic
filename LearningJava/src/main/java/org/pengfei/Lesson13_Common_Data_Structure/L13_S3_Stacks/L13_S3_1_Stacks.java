package org.pengfei.Lesson13_Common_Data_Structure.L13_S3_Stacks;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S2_Linked_lists.MySinglyLinkedList;

public class L13_S3_1_Stacks {

    public static void main(String[] args) {

        L13_S3_1_Stacks stackExamples= new L13_S3_1_Stacks();
        /**********************************************************************************
         * *****************************13.3 Stacks ************************************
         * *******************************************************************************/

        /*****************************13.3.1 Introduction *******************************/
        /* A stack is a collection of objects that are inserted and removed according to the
         * last-in, first-out (LIFO) principle. A user may insert objects into a stack at any
         * time, but may only access or remove the most recently inserted objects that remains
         * (at the so-called "top" of the stack)
         *
         * Application exmaples:
         * 1. Web browsers store the addresses of recently visited sites on a stack.
         * Each time a user visits a new site, that site’s address is “pushed” onto the
         * stack of addresses. The browser then allows the user to “pop” back to previously
         * visited sites using the “back” button.
         *
         * 2. Text editors usually provide an “undo” mechanism that cancels recent
         * editing operations and reverts to former states of a document. This undo
         * operation can be accomplished by keeping text changes in a stack.
         *
         * The Stack abstract data type supports the following methods:
         * - push(e) : Adds element e to the top of the stack
         * - pop() : Removes and returns the top element from the stack (or null if the stack is empty)
         * - top() : Returns the top element of the stack, without removing it (or null if the stack is empty)
         * - size() : Returns the number of elements in the stack
         * - isEmpty(): Returns a boolean indicating whether the stack is empty.
         *
         *
         * */

        /*****************************13.3.2 A stack interface in Java *******************************/

        /* In this lesson, we define a API in the form of a Java interface, which describes the names
         * of the methods that the stack ADT (abstract data type) supports and how they are to be declared
         * and used.
         *
         * We rely on Java's generics framework <E>, which allows the elements in the stack can belong to any
         * object type
         *
         * The java.util.Stack Class
         * Because of the importance of the stack ADT, Java has included, since its original version,
         * a concrete class named java.util.Stack. But the current documentation for the Stack class
         * recommends that it not be used, as LIFO functionality (and more) is provided by a more general
         * data structure known as a double-ended queue
         *
         * The java.util.stack implements these methods with different names
         * isEmpty()-> empty() in java.util.stack
         * top() -> peek()
         * the rest are the same*/

        /*****************************13.3.3 Array based Stack Implementation *******************************/

        /* See the ArrayBasedStack class for the implementation code.
         *
         * You could notice the implementation is simple and efficient. Nevertheless, this
         * implementation has a major draw-back, the fixed capacity of the array, which limits
         * the ultimate size of the stack.
         *
         * The running time complexity of each method in this implementation are as followed
         * size -> o(1)
         * isEmpty -> o(1)
         * top -> o(1)
         * push -> o(1)
         * pop -> o(1)
         *
         * The space usage is o(N)
         *
         * The Garbage Collection in Java
         *
         * In the pop method implementation, we have done data[t]=null, it seems useless for the method logic
         * We do this to assist Java's garbage collection mechanism, which searches memory for objects
         * that are no longer actively referenced. If we continue to store a reference to the array, the
         * garbage collector will ignore it.
         *
         * */
      // stackExamples.arrayBasedStackExamples();

        /************************** 13.3.3 Singly linked list based Stack Implementation **************************/

        /*  First, we need to decide if the top of the stack is at the front or back of the list. As the list is singly
        * linked, we can easily add and remove at the front of the list, */


        //stackExamples.singlyLinkedListBasedStackExamples();

        /****************************** 13.3.4  Stack Application examples ***********************************************/
        /* As stack implements a LIFO protocol, a stack can be used as a general toll to reverse a data sequence.*/
       //stackExamples.reverseExamples();

        /* We can also use stack to check if delimiter couples (e.g. (),{},[]) are matched in an expression or not, with
         * the same spirit, we can use it check if a html tag matches or not */
       stackExamples.tagMatchExamples();
    }

    public void tagMatchExamples(){
        String expression="[{(1+3)*(2-4)}*4]";
        Boolean eMatch=StackApplicationExample.isMatched(expression);
        System.out.println("The expression is match: "+eMatch);
        String bE="[{(1+3)*(2-4)})*4]";
        Boolean bEMatch=StackApplicationExample.isMatched(bE);
        System.out.println("The expression is match: "+bEMatch);

        String html="<body>\n" +
                "<center>\n" +
                "<h1> The Little Boat </h1>\n" +
                "</center>\n" +
                "<p> The storm tossed the little\n" +
                "boat like a cheap sneaker in an\n" +
                "old washing machine. The three\n" +
                "drunken fishermen were used to\n" +
                "such treatment, of course, but\n" +
                "not the tree salesman, who even as\n" +
                "a stowaway now felt that he\n" +
                "had overpaid for the voyage. </p>\n" +
                "<ol>\n" +
                "<li> Will the salesman die? </li>\n" +
                "<li> What color is the boat? </li>\n" +
                "<li> And what about Naomi? </li>\n" +
                "</ol>\n" +
                "</body>";

        String bHtml="<body/>";

        Boolean hMatch=StackApplicationExample.isHTMLMatched(html);
        System.out.println("The html is match: "+hMatch);

        Boolean bHMatch=StackApplicationExample.isHTMLMatched(bHtml);
        System.out.println("The html is match: "+bHMatch);
    }

    public void arrayBasedStackExamples(){
        Stack<Integer> S = new ArrayBasedStack<>( ); // contents: ()
        S.push(5); // contents: (5)
        S.push(3); // contents: (5, 3)
        System.out.println(S.size( )); // contents: (5, 3) outputs 2
        System.out.println(S.pop( )); // contents: (5) outputs 3
        System.out.println(S.isEmpty( )); // contents: (5) outputs false
        System.out.println(S.pop( )); // contents: () outputs 5
        System.out.println(S.isEmpty( )); // contents: () outputs true
        System.out.println(S.pop( )); // contents: () outputs null
        S.push(7); // contents: (7)
        S.push(9); // contents: (7, 9)
        System.out.println(S.top( )); // contents: (7, 9) outputs 9
        S.push(4); // contents: (7, 9, 4)
        System.out.println(S.size( )); // contents: (7, 9, 4) outputs 3
        System.out.println(S.pop( )); // contents: (7, 9) outputs 4
        S.push(6); // contents: (7, 9, 6)
        S.push(8); // contents: (7, 9, 6, 8)
        System.out.println(S.pop( )); // contents: (7, 9, 6) outputs 8
    }

    public void singlyLinkedListBasedStackExamples(){
        SinglyLinkedListBasedStack<Integer> S= new SinglyLinkedListBasedStack<Integer>();
        S.push(5); // contents: (5)
        S.push(3); // contents: (5, 3)
        System.out.println(S.size( )); // contents: (5, 3) outputs 2
        System.out.println(S.pop( )); // contents: (5) outputs 3
        System.out.println(S.isEmpty( )); // contents: (5) outputs false
        System.out.println(S.pop( )); // contents: () outputs 5
        System.out.println(S.isEmpty( )); // contents: () outputs true
        System.out.println(S.pop( )); // contents: () outputs null
        S.push(7); // contents: (7)
        S.push(9); // contents: (7, 9)
        System.out.println(S.top( )); // contents: (7, 9) outputs 9
        S.push(4); // contents: (7, 9, 4)
        System.out.println(S.size( )); // contents: (7, 9, 4) outputs 3
        System.out.println(S.pop( )); // contents: (7, 9) outputs 4
        S.push(6); // contents: (7, 9, 6)
        S.push(8); // contents: (7, 9, 6, 8)
        System.out.println(S.pop( )); // contents: (7, 9, 6) outputs 8
    }


    public void reverseExamples(){
        /*Test for reverse array*/
        Integer[] myArray=new Integer[10];
        for(int i=0;i<10;i++){
            myArray[i]=i;
        }
        /*We can also use java auto boxing to initiate a new list
        *
        * eg. Integer[] a={4,8,15,16,23,42}
        *     String[] s={"hello","world","toto"}
        * */


        this.reverseArray(myArray);
        for(int i=0;i<10;i++){
            System.out.println(myArray[i]);
        }

        /* Test for reverse list*/
        MySinglyLinkedList<Integer> l=new MySinglyLinkedList<Integer>();
        for(int i=0;i<5;i++){
            l.addFirst(i);
        }
        this.reverseList(l);

        for(int i=0;i<5;i++){
            System.out.println(l.removeFirst());
        }
    }

    /* A generic method for reversing an array by using stack*/
    public  <E> void reverseArray(E[] a){
        Stack<E> buffer=new ArrayBasedStack<>(a.length);
        /*fill the buffer stack with array element*/
        for(int i=0;i<a.length;i++){
            buffer.push(a[i]);
        }
        /*fill the array with the buffer stack */
        for(int i=0;i<a.length;i++){
            a[i]=buffer.pop();
        }

    }

    /* A generic method for reversing an array by using stack*/
    public  <E> void reverseList(MySinglyLinkedList<E> l){
        Stack<E> buffer=new SinglyLinkedListBasedStack<>();
        /*fill the buffer stack with list element*/
        while(!l.isEmpty()){
            buffer.push(l.removeFirst());
        }

        /*fill the list with the buffer stack */
       while(!buffer.isEmpty()){
           l.addFirst(buffer.pop());
       }

    }
}
