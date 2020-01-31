package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
            for(int i=0;i<buf.length;i+=2) f0.write(buf[i]);

            // write entire buf to second
            f1.write(buf);

            // write bytes starts at 3/4 buf length to end of but to third file
            f2.write(buf,buf.length-buf.length/4,buf.length/4);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
