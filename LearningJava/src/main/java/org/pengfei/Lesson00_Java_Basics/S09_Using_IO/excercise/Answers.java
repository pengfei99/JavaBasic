package org.pengfei.Lesson00_Java_Basics.S09_Using_IO.excercise;

import java.io.*;
import java.util.RandomAccess;

public class Answers {
    private static String filePath = "/tmp/test";

    public static void exp3() {

        try (FileInputStream fin = new FileInputStream(filePath)) {
            int i = 0;
            do {
                i = fin.read();
                if (i == -1) continue;
                System.out.print((char) i);
            } while (i != -1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp4() {
        try (var fr = new BufferedReader(new FileReader(filePath))) {
            var str = fr.readLine();
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp5() {
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            raf.seek(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp6() {
        String str = "123.123";
        Double d = Double.valueOf(str);
        System.out.println(d + d.getClass().getName());
    }

    public static void exp7(String source, String destination){
        try {
            FileInputStream fin=new FileInputStream(source);
            FileOutputStream fout=new FileOutputStream(destination);
            int c=0;
            do{
                c = fin.read();
                if(c==-1) continue;
                if(((char)c)==' ') fout.write('-');
                else fout.write(c);
            }while (c!=-1);
            fin.close();
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        // Answers.exp3();

        // Answers.exp4();
        //Answers.exp6();
        Answers.exp7("/tmp/test1","/tmp/test2");
    }


}
