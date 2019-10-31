package org.pengfei.Lesson00_Java_Basics.S09_Using_IO.source;

import java.io.*;
import java.lang.management.BufferPoolMXBean;
import java.util.Scanner;

public class CharacterIOExp {

    public static void exp1() throws IOException {
        char c;
        var br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a character, type * to quit");

        do {
            c = (char) br.read();
            if (c == '*') break;
            System.out.println(c);

        } while (c != '*');
    }

    public static void exp2() throws IOException {
        String msg;
        var br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter a message, type 'stop' to quit");
        do {
            msg = br.readLine();
            if (msg.equals("stop")) break;
            System.out.println("Message is: " + msg);
        } while (!msg.equals("stop"));
    }

    public static void exp3() {
        var pw = new PrintWriter(System.out, true);
        int i = 10;
        double d = 123.65;

        pw.println("Using a PrintWriter");
        pw.println("又见巴西：巴西，作为一个大国，为何自认最受上帝垂爱？");

        //System.out.println("又见巴西");
        pw.println(i);
        pw.println(d);
        pw.println(i + "+ " + d + " is " + (i + d));
    }


    public static void exp4(String filePath) {
        String msg;
        var bfr = new BufferedReader(new InputStreamReader(System.in));
        try (var fw = new FileWriter(filePath,true)) {

            do {
                msg = bfr.readLine();
                if (msg.equals("stop")) {
                    break;
                }
                fw.write(msg + "\r\n");
            } while (!msg.equals("stop"));
        } catch (IOException exc) {
                 exc.printStackTrace();
        }
    }

    public static void exp5(String filePath){
        String msg;

        try(var br=new BufferedReader(new FileReader(filePath))){
           while((msg=br.readLine())!=null){
               System.out.println(msg);
           }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp6() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str;
        int n;
        double sum=0.0;
        double avg,t;

        System.out.println("How many numbers will you enter: ");
        str=br.readLine();

        try{
            n=Integer.parseInt(str);
        } catch (NumberFormatException e) {
            n=0;
            System.out.println("Invalid format");
        }
        System.out.println("Enter "+n+" values.");
        for(int i=0;i<n;i++){
            System.out.print(": ");
            str=br.readLine();
            try{
                t=Double.parseDouble(str);
            } catch (NumberFormatException e) {
                t=0.0;
                System.out.println("Invalid format");
            }
            sum +=t;
        }
        avg =sum/n;
        System.out.println( "Average is "+avg);
    }

    public static void exp7(){
        Scanner scan=new Scanner(System.in);

        System.out.println("Enter a double followed by enter");

        if(scan.hasNextDouble()){
            double d=scan.nextDouble();
            System.out.println("Double value : "+d);
        }
    }
}