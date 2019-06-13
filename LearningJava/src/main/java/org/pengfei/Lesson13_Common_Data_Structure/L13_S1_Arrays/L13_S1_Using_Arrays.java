package org.pengfei.Lesson13_Common_Data_Structure.L13_S1_Arrays;

public class L13_S1_Using_Arrays {
    /************************************************************************************************************
     * ******************************** 13.1 Arrays ************************************************************
     * ********************************************************************************************************/

    /* Array is the data structure that we most frequently used. An array is the data structure that contains
     * a collection of similar type data elements.
     *
     * Arrays Vs Linked list
     *
     * In the array the elements belong to indexes, i.e., if you want to get into the fourth element you have
     * to write the variable name with its index or location within the square bracket. In a linked list though,
     * you have to start from the head and work your way through until you get to the fourth element.
     *
     * Accessing element:
     * - In an array, it's fast.
     * - In a linked list, the worst case(last element) takes linear time O(n), so it is quite a bit slower.
     *
     * Insertion and Deletion:
     * - In an array, it consume a lot of time. Because, when we insert a new element in the middle of the Array,
     *   we have to free space for it, which means we need to shift all existing elements behind it.
     * - In Linked lists, the performance of these operations is fast.
     *
     * Overall Memory consumption:
     * - Arrays are of fixed size. So we must know the upper limit on the number of elements in advance. Also, generally,
     *   the allocated memory is equal to the upper limit irrespective of the usage, and in practical uses, the upper
     *   limit is rarely reached. In an array, memory is assigned during compile time.
     * - Linked lists are dynamic and flexible and can expand and contract its size. In a Linked list, memory is
     *   allocated during execution or runtime.
     *
     * Per-element Memory consumption:
     * - In an array, the requirement of memory is less due to actual data being stored within the index.
     * - In Linked lists, there is a need for more memory due to storage of additional next and previous
     *   referencing elements.
     *
     * But, overall memory utilization is inefficient in the array. Conversely, memory utilization is efficient
     * in the linked list.
     *
     *
     *
     * */

    public static void main(String[] args){

        /* We use a class ScoreBoard to store the GameEntry. The core of the ScoreBoard class is an Array
          * It has an add and a remove method.  */
        GameEntry g1=new GameEntry("SC1",6000);
        GameEntry g2=new GameEntry("SC2",6001);
        GameEntry g3=new GameEntry("World_of_War",6002);

        ScoreBoard sb=new ScoreBoard(5);

        sb.add(g1);
        sb.add(g2);
        sb.add(g3);

        sb.showScores();


    }

    /*****************************************************************************************************
     * **************************************** Index ****************************************************
     * ***************************************************************************************************/

    /*************************************** Java Naming conventions *************************************/
    /* Java naming convention is a rule to follow as you decide what to name your identifiers such as class, package,
    * variable, constant, method, etc.*/

    /****************************************I.1 Class **********************************************/

    /* - It should start with the uppercase letter.
     * - It should be a noun such as Color, Button, System, Thread, etc.
     * - Use appropriate words, instead of acronyms.
     * - Ex: Teacher, Student, Person
     * */

    /****************************************I.2 Interface **********************************************/

    /* - It should start with the uppercase letter.
       - It should be an adjective such as Runnable, Remote, ActionListener.
       - Use appropriate words, instead of acronyms.
       - Ex: Printable, Iterable, Readable
    */

    /****************************************I.3 Method **********************************************/
    /* - It should start with lowercase letter.
    *  - It should be a verb such as main(), print(), println().
    *  - If the name contains multiple words, start it with a lowercase letter
    *    followed by an uppercase letter such as actionPerformed().
    *    */

    /****************************************I.4 Variable **********************************************/

    /* - It should start with a lowercase letter such as id, name.
     * - It should not start with the special characters like & (ampersand), $ (dollar), _ (underscore).
     * - If the name contains multiple words, start it with the lowercase letter followed by an uppercase letter
     *   such as firstName, lastName.
     * - Avoid using one-character variables such as x, y, z.
    * */

    /****************************************I.5 Package **********************************************/

    /* - It should be a lowercase letter such as java, lang.
     * - If the name contains multiple words, it should be separated by dots (.) such as java.util, java.lang.
     * - Ex: org.pengfei.ml; com.java;*/

    /****************************************I.6 Constant **********************************************/
    /* - It should be in uppercase letters such as RED, YELLOW.
     * - If the name contains multiple words, it should be separated by an underscore(_) such as MAX_PRIORITY.
     * - It may contain digits but not as the first letter.
     * - Ex: static final int MIN_AGE*/

    /****************************** I.7 CamelCase in java naming conventions *************************************/
    /*
    * Java follows camel-case syntax for naming the class, interface, method, and variable.
    * If the name is combined with two words, the second word will start with uppercase letter always such as
    * actionPerformed(), firstName, ActionEvent, ActionListener, etc.
    * */

}
