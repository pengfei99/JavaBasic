package org.pengfei.Lesson01_Java_Standard_API.S01_String_Handling.source;

public class StringBufferOperation {

    public static void exp01() {
        StringBuffer sb = new StringBuffer("Hello world");

        System.out.println("Buffer: " + sb);
        System.out.println("Length is: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());

        sb.ensureCapacity(43);
        System.out.println("Capacity after ensureCapacity: " + sb.capacity());
    }

    public static void exp02() {
        StringBuffer sb = new StringBuffer("Hello");

        System.out.println("Buffer source : " + sb);
        System.out.println("charAt(1) = " + sb.charAt(1));
        sb.setCharAt(1, 'i');
        System.out.println("setCharAt(1,i) = " + sb);
        sb.setLength(10);
        System.out.println("Buffer after setLength(10) : " + sb);
        sb.setLength(2);
        System.out.println("Buffer after setLength(2) : " + sb);

    }

    public static void exp03() {
        String str;
        StringBuffer sb = new StringBuffer(40);

        str = sb.append("a = ").append(2).toString();

        System.out.println(str);
    }

    public static void exp04() {
        StringBuffer sb = new StringBuffer("I Java.");
        sb.insert(2, "like ");
        System.out.println(sb);
    }

    public static void exp05() {
        StringBuffer sb = new StringBuffer("abcdef");
        System.out.println("Before reverse: " + sb);
        sb.reverse();
        System.out.println("After reverse: " + sb);


    }

    public static void exp06() {
        StringBuffer sb = new StringBuffer("This is a test");

        System.out.println("Before deletion: " + sb);

        sb.delete(4, 7);

        System.out.println("After delete(4,7): " + sb);

        sb.deleteCharAt(0);

        System.out.println("After deleteCharAt(0): " + sb);
    }

    public static void exp07(){
        StringBuffer sb = new StringBuffer("This is a test");

        System.out.println("Before deletion: " + sb);

        sb.replace(4, 7," was");

        System.out.println("After replace(4,7,was): " + sb);
    }
}
