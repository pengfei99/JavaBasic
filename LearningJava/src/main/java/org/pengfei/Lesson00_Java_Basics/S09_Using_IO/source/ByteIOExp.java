package org.pengfei.Lesson00_Java_Basics.S09_Using_IO.source;

import java.io.*;

public class ByteIOExp {

    public static void exp1(){
        byte data[] = new byte[10];
        System.out.println("Enter some characters.");
        try {
            System.in.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("You entered: ");
        for(int i=0; i<data.length;i++){
            // if we don't convert byte to char, you will see the byte code (97) for char(a)
            System.out.print((char) data[i]);
        }
    }

    public static void exp2(){
        // every char is represented as a int (byte code)
        int b='a';

        // println will use b.toString to print value, so you will see 97 in output
        System.out.println(b);
        // write will take be as byte code, and translate it to char, 97->a
        System.out.write(b);

        System.out.write('\n');

    }


    public static void exp3(String filePath){
        FileInputStream fileInputStream=null;
        int i;
        try {
            fileInputStream=new FileInputStream(filePath);
            // here we use do while, because we need to assign i with a value before we test the loop condition.
            do{
               i =fileInputStream.read();
                if(i !=1) System.out.print((char) i);
            }while (i!=-1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // we do the close in finally clause to make sure close will be execute no matter what happens in upper try catch
        finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void exp4(String inputFile,String outputFile){
        FileInputStream fin=null;
        FileOutputStream fout=null;
        int i;
        try{
            fin=new FileInputStream(inputFile);
            fout=new FileOutputStream(outputFile);

            do{
                i=fin.read();
                if(i!=-1) fout.write(i);
                else break;
            }while (i!=1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void exp5(String inputFile){
        int i;
        // try with resources
        try(FileInputStream fin=new FileInputStream(inputFile)){
            do{
                i = fin.read();
                if (i!=-1) System.out.print((char) i);
                else break;
            }while(i!=-1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp6(String inputFile){
        int i;
        // use local variable type inference
        try(var fin=new FileInputStream(inputFile)){
            do{
                i = fin.read();
                if (i!=-1) System.out.print((char) i);
                else break;
            }while(i!=-1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Multiple resources
    public static void exp7(String inputFile,String outputFile){
        int i;
        // use local variable type inference
        try(var fin=new FileInputStream(inputFile);var fout=new FileOutputStream(outputFile)){
            do{
                i = fin.read();
                if (i!=-1) fout.write(i);
                else break;
            }while(i!=-1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp8(String filePath){
        int i=10;
        double d = 88.88;


        // first we write the above data to a file, note that the data in the file are not human readable
        try(var fout=new DataOutputStream(new FileOutputStream(filePath))){
            fout.writeInt(i);
            fout.writeDouble(d);
            fout.writeBoolean(true);

            // if we write two double, they are stored like a list, when we read the data, it's FIFO.
            fout.writeDouble(8.8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Second we read what we have write
        try(var fin=new DataInputStream(new FileInputStream(filePath))){
            int iRead=fin.readInt();
            System.out.println("Reading int i "+iRead);
            Double dRead=fin.readDouble();
            System.out.println("Reading double d "+dRead);
            boolean bRead=fin.readBoolean();
            System.out.println("Reading boolean b "+bRead);

            // if we write once and read twice, it generate EOFException.
            // if we write twice and read twice, it's ok
            dRead=0.0;
            dRead=fin.readDouble();
            System.out.println("Reading double d "+dRead);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp9(String filePath){
        double data[]={19.4,10.1,123.54,33.0,87.9,74.25};
        double d;

        // open and use a random access file
        // we can both read and write this file
        try(var raf=new RandomAccessFile(filePath,"rw")){
            // write data to file
            for(int i=0;i<data.length;i++){
                raf.writeDouble(data[i]);
            }

            // Now, reposition the pointer to the first double value
            raf.seek(0);
            d=raf.readDouble();
            System.out.println("First value is "+d);

            // goto the second value, remember the value is long which represent position in byte.
            // Each double value is 8 bytes long, so the second value start position is 8
            raf.seek(8);
            d=raf.readDouble();
            System.out.println("Second value is "+d);

            // fourth value
            raf.seek(8*3);
            d=raf.readDouble();
            System.out.println("Second value is "+d);

            // now, we want to print every value
            for(int i=0;i<data.length;i++){
                raf.seek(8*i);
                d=raf.readDouble();
                System.out.println("The "+ i+" value is "+d);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // We can't
    public static void exp10(){
        Console console=System.console();
        System.out.println("Please enter your name followed by enter");
        String name=console.readLine();
        System.out.println("Please enter your password followed by enter");
        char[] pwd=console.readPassword();

        System.out.println("your name is"+name+"your password "+pwd.toString());

    }
}
