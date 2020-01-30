package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScannerExp {

    public static void exp1(String filePath) {
        try {
            FileReader fin = new FileReader(filePath);
            Scanner sc = new Scanner(fin);

            //read file line by line
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                System.out.println(str);
            }
            // close scanner and fileReader
            sc.close();
            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void exp2() {
        System.out.println("Please enter something or q to quit");
        Scanner sc = new Scanner(System.in);
        String str = null;
        do {
            str = sc.nextLine();
            if (str.equals("q")) continue;
            System.out.println(str);
        } while (!str.equals("q"));

        sc.close();

    }

    public static void exp3() {
        System.out.println("Please enter some numbers to average or q to quit");
        Scanner sc = new Scanner(System.in);
        int count = 0;
        double sum = 0.0;

        // read and sum numbers
        while (sc.hasNext()) {
            // check the token is a double or not
            if (sc.hasNextDouble()) {
                sum += sc.nextDouble();
                count++;
            }
            // if not double, check it equals q, if q exit normally, otherwise raise error
            else {
                String str = sc.next();
                if (str.equals("q")) break;
                else {
                    System.out.println("Data format error");
                    return;
                }
            }
        }

        sc.close();
        System.out.println("Average is " + sum / count);
    }

    public static void exp4(String filePath) {
        // write some value to a file
        try {
            FileWriter fout = new FileWriter(filePath);
            fout.write("2 3.4 5 6 7 8 9.9 q");
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create a fileReader
        FileReader fin = null;
        try {
            fin = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(fin);
        int count = 0;
        double sum = 0.0;

        // read and sum numbers
        while (sc.hasNext()) {
            // check the token is a double or not
            if (sc.hasNextDouble()) {
                sum += sc.nextDouble();
                count++;
            }
            // if not double, check it equals q, if q exit normally, otherwise raise error
            else {
                String str = sc.next();
                if (str.equals("q")) break;
                else {
                    System.out.println("Data format error");
                    return;
                }
            }
        }

        sc.close();
        System.out.println("Average is " + sum / count);
    }

    public static void exp5() {
        System.out.println("Please enter something or q to quit");

        try (Scanner sc = new Scanner(System.in);) {
            String str = null;
            do {
                str = sc.nextLine();
                if (str.equals("q")) continue;
                System.out.println(str);
            } while (!str.equals("q"));
        }
    }

    public static void exp6() {
        System.out.println("Please enter something or q to quit");

        try (Scanner sc = new Scanner(System.in);) {
            while (sc.hasNext()) {
                //check the token is int or not
                if (sc.hasNextInt()) {
                    System.out.println(sc.nextInt() + " is an Integer");
                }
                //check the token is double or not
                else if (sc.hasNextDouble()) {
                    System.out.println(sc.nextDouble() + " is a Double");
                }
                // check the token is a boolean or not
                else if (sc.hasNextBoolean()) {
                    System.out.println(sc.nextBoolean() + " is a Boolean");
                }

                // for all other types, get the value as string
                else {
                    String str = sc.next();
                    if (str.equals("q")) break;
                    System.out.println(str);
                }
            }
        }
    }


    public static void exp7(String filePath) {
        // write some value to a file
        try {
            FileWriter fout = new FileWriter(filePath);
            fout.write("2,3.4,5, 6,7,8;9.9,q");
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create a fileReader
        FileReader fin = null;
        try {
            fin = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(fin);

        //set delimiter to match a comma and zero or more spaces as delimiter or ; followed by zero or more spaces.
        sc.useDelimiter("(, *)|(; *)");
        int count = 0;
        double sum = 0.0;

        // read and sum numbers
        while (sc.hasNext()) {
            // check the token is a double or not
            if (sc.hasNextDouble()) {
                sum += sc.nextDouble();
                count++;
            }
            // if not double, check it equals q, if q exit normally, otherwise raise error
            else {
                String str = sc.next();
                if (str.equals("q")) break;
                else {
                    System.out.println("Data format error");
                    return;
                }
            }
        }

        sc.close();
        System.out.println("Average is " + sum / count);
    }

    public static void exp8() {
        String str = "Name: haha, Age: 18, ID: 77";

        try (Scanner sc = new Scanner(str)) {

            //Find the first occurrence of age, and place the iterator to it. So when next() method is called,
            // the element just after the target occurrence is returned.
            sc.findInLine("Age:");

            if (sc.hasNext()) System.out.println(sc.next());
            else System.out.println("Error!");

        }
    }

}
