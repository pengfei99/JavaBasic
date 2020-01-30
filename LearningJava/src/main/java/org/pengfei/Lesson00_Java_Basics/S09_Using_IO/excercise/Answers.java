package org.pengfei.Lesson00_Java_Basics.S09_Using_IO.excercise;

import java.io.*;

public class Answers {
    private static String filePath="/tmp/test";
    public static void exp3(){

        try( FileInputStream fin=new FileInputStream(filePath)) {
           int i=0;
           do{
               i=fin.read();
               if(i==-1) continue;
               System.out.print((char) i);
           } while (i!=-1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp4(){
        try(var fr=new BufferedReader(new FileReader(filePath))){
            var str=fr.readLine();
            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
       // Answers.exp3();

        Answers.exp4();
    }

}
