package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source;

import java.io.*;
import java.util.Vector;

public class AdvanceByteIOExp {

    public static void exp1(String path) {
        int size;

        try (FileInputStream f = new FileInputStream(path)) {
            System.out.println("Total available bytes: " + (size = f.available()));
            // divise file into 40 parts.
            int n = size / 40;

            // get first part
            System.out.println("First " + n + " bytes of the file one read() at a time");
            for (int i = 0; i < n; i++) System.out.print((char) f.read());
            System.out.println("\nStill Available: " + f.available());


            //get second part
            System.out.println("Reading the next " + n + " with one read(b[])");
            byte b[] = new byte[n];
            if (f.read(b) != n) {
                System.err.println("couldn't read " + n + " bytes.");
            }
            System.out.println(new String(b, 0, n));
            System.out.println("\nStill Available: " + (size = f.available()));

            //skip some content
            System.out.println("Skipping half of the remaining bytes with skip()");
            f.skip(size / 2);
            System.out.println("\nStill Available: " + f.available());

            //reading the rest of the file
            System.out.println("Reading " + n / 2 + " into the end of array");
            if (f.read(b, n / 2, n / 2) != n / 2) {
                System.err.println("couldn't read " + n / 2 + " bytes.");
            }
            System.out.println(new String(b, 0, b.length));
            System.out.println("\nStill Available: " + f.available());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp2() {
        String source = "2020-01-22 09:48:54.211 [daemon] INFO: Kotlin compiler daemon version <unknown>\n" +
                "2020-01-22 09:48:54.257 [daemon] INFO: daemon JVM args: -Djava.awt.headless=true -Djava.rmi.server.host ";
        // convert string to bytes
        byte buf[] = source.getBytes();
        try (FileOutputStream f0 = new FileOutputStream("/tmp/test");
             FileOutputStream f1 = new FileOutputStream("/tmp/test1");
             FileOutputStream f2 = new FileOutputStream("/tmp/test2");
        ) {
// write to first file
            for (int i = 0; i < buf.length; i += 2) f0.write(buf[i]);

            // write entire buf to second
            f1.write(buf);

            // write bytes starts at 3/4 buf length to end of but to third file
            f2.write(buf, buf.length - buf.length / 4, buf.length / 4);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void exp3() {
        String str = "abcdefghijklmnopqrstuvwxyz";
        byte[] b = str.getBytes();

        //the binput1 input stream contains entire string,
        ByteArrayInputStream binput1 = new ByteArrayInputStream(b);

        //the binput2 contains only the first three character of the string
        ByteArrayInputStream binput2 = new ByteArrayInputStream(b, 0, 3);
        int c = -1;
        do {
            c = binput2.read();
            if (c == -1) continue;
            System.out.print((char) c);
        } while (c != -1);
        System.out.println();
        //after the loop the pointer of binput2 is at the end of stream. so when we do read, it returns -1. To reset
        //the pointer to the begining of the stream, use reset method
        System.out.println("Current pointer position returns: " + binput2.read());
        binput2.reset();
        System.out.println("Current pointer position returns: " + binput2.read());

        // we can also use mark to set a specific position to reset(), in the following example we mark c(99) as the
        // start, so when we reset, the pointer does not start from a but from c.

        for (int i = 0; i < 10; i++) {
            c = binput1.read();
            if (c == -1) break;
            //mark c
            if (c == 99) binput1.mark(3);

            //if i=5 reset once
            if (i == 5) binput1.reset();
            System.out.print((char) c);
        }

    }

    public static void exp4() {
        String str = "My life will rock";
        byte[] b = str.getBytes();
        ByteArrayOutputStream boutput = new ByteArrayOutputStream();

        try {
            boutput.write(b);
        } catch (IOException e) {
            System.out.println("Error writing to buffer");
        }

        System.out.println("ByteArrayOutputStream as string: " + boutput.toString());

        System.out.println("ByteArrayOutputStream to array: ");
        byte[] bb = boutput.toByteArray();
        for (int i = 0; i < bb.length; i++) {
            System.out.print((char) bb[i]);
        }
        System.out.println();


        // write to a file
        try (FileOutputStream f2 = new FileOutputStream("/tmp/test.txt")) {
            //write to a outputStream
            boutput.writeTo(f2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("doing a reset");
        boutput.reset();

        for (int i = 0; i < 3; i++) boutput.write('X');
        System.out.println("After reset, inserting 3 X: " + boutput.toString());
    }

    public static void exp5() {
        String str = "This is a &copy; copyright symbol. but this is &copy not.\n";
        byte[] b = str.getBytes();

        ByteArrayInputStream bin = new ByteArrayInputStream(b);
        int c;
        boolean marked = false;

        try (BufferedInputStream f = new BufferedInputStream(bin)) {
            do {
                c = f.read();
                if (c == -1) continue;
                switch (c) {
                    case '&':
                        //f.mark(32) marks the & position and preserves the mark fo the next 32 bytes read.
                        if (!marked) {
                            f.mark(32);
                            marked = true;
                        } else marked = false;
                        break;
                    case ';':
                        if (marked) {
                            marked = false;
                            System.out.print("(c)");
                        } else System.out.print((char) c);
                        break;
                    case ' ':
                        if (marked) {
                            marked = false;
                            f.reset();
                            System.out.print("&");
                        } else System.out.print((char) c);
                        break;
                    default:
                        if (!marked) System.out.print((char) c);
                        break;
                }
            } while (c != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp6() {

        // build a byte array stream to read a string as byte
        String str = "if (a == 4) then a = 0;\n";
        byte[] b = str.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(b);

        int c;
        try (PushbackInputStream f = new PushbackInputStream(in)) {
            do {
                c = f.read();
                if (c == -1) continue;
                switch (c) {
                    case '=':
                        // we read another byte, the pointer of stream goes further(position of '='+1)
                        // then we check if the following byte is = or not, if yes, then we encouter ==, so replace it
                        // by equal, otherwise, we need to push back the new read byte.
                        c = f.read();
                        if (c == '=') System.out.print("equal");
                        else {
                            System.out.print("assign");
                            //push back c
                            f.unread(c);
                        }
                        break;
                    default:
                        System.out.print((char) c);
                        break;
                }
            } while (c != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp7(){
        Vector<String> files=new Vector<String>();
        files.addElement("/tmp/test1");
        files.addElement("/tmp/test2");
        files.addElement("/tmp/test3");

        //call my InputStreamEnumerator
        InputStreamEnumerator myInputs=new InputStreamEnumerator(files);
        //build the SequenceInputStream
        InputStream f=new SequenceInputStream(myInputs);

        int c;
        try{
            while((c=f.read())!=-1){
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("Error Opening File.");
        }finally {
            try{
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void exp8(){
        //f1 is a directory
        File f1=new File("/tmp");

        //f2 is a file
        File f2=new File(f1,"test");

        try (PrintStream f=new PrintStream(f2,"UTF-8");){
            f.println("Here are some numeric values in different formats. \n");
            f.printf("%d %(d %+d %05d\n",3,-3,3,3);

            f.println();
            f.printf("Default floating-point format: %f\n", 1234567.123);
            f.printf("Floating-point with commas: %,f\n", 1234567.123);
            f.printf("Negative Default floating-point format: %,f\n", -1234567.123);

            f.println();
            f.printf("Line up positive and negative values:\n");
            f.printf("% ,.2f\n% ,.2f\n", 1234567.123,-1234567.123);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void exp9(String filePath){
        // use DataOutputStream to write data on a file
        try(DataOutputStream dout=new DataOutputStream(new FileOutputStream(filePath))){
            dout.writeDouble(88.8);
            dout.writeInt(88);
            dout.writeBoolean(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // now read data
        try(DataInputStream din=new DataInputStream(new FileInputStream(filePath))) {
           double d=din.readDouble();
           int i=din.readInt();
           boolean b=din.readBoolean();
           System.out.println("here are the values: "+d+" "+i+" "+b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
