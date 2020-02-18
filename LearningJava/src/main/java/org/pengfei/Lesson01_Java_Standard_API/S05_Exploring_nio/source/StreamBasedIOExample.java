package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class StreamBasedIOExample {
    public static void exp1(String path){
        int i;
        // create path
        // create a input stream link to the path
        try(InputStream fin= Files.newInputStream(Path.of(path))) {
            do{
                i=fin.read();
                if(i!=-1) System.out.print((char)i);

            }while (i!=-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp2(String path){
        int i;

        // create a buffered input Stream

        try(BufferedInputStream bin=new BufferedInputStream(Files.newInputStream(Path.of(path)))) {
            do{
                i=bin.read();
                if(i!=-1){
                    switch(i){
                        case 'A': System.out.print('a');break;
                        default:System.out.print((char) i);break;

                    }
                }
            }while (i!=-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp3(String path){
        //create a buffered output stream
        try(OutputStream out=new BufferedOutputStream(Files.newOutputStream(Path.of(path)))) {
            for(int i=0;i<26;i++) out.write((byte)('A'+i));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
