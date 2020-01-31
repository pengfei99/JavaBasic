package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileExp {

    public static void exp1(){
        //f1 is a directory
        File f1=new File("/tmp");

        //f2 is a file
        File f2=new File(f1,"test1");

        //get meta data of a file
        System.out.println("File Name: "+f2.getName());
        System.out.println("File Path: "+f2.getPath());
        System.out.println("File abs Path: "+f2.getAbsolutePath());
        System.out.println("Parent: "+f2.getParent());
        System.out.println(f2.exists() ? "exists": "does not exist");
        System.out.println(f2.canWrite() ? "is writeable": " not writeable");
        System.out.println(f2.canRead() ? "is readable": " not readable");
        System.out.println("is "+(f2.isDirectory() ? "": " not "+"a directory"));
        System.out.println("is "+(f2.isFile() ? "a normal file": "might be a named pipe"));
        System.out.println("File last modified time: "+f2.lastModified());
        System.out.println("File size: "+f2.length()+" Bytes");

    }

    public static void exp2(String filePath){
        File f=new File(filePath);
        Path path=f.toPath();
        try {
            BasicFileAttributes metadata= Files.readAttributes(path,BasicFileAttributes.class);
            System.out.println("File creation time: "+metadata.creationTime());
            System.out.println("File last modified time: "+metadata.lastModifiedTime());
            System.out.println("File last access time: "+metadata.lastAccessTime());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp3(String filePath){
        /* renameTo is not reliable, use apache.commons.io package, for example moveFile(),*/
        File f=new File(filePath);
        boolean renamed = f.renameTo(new File("toto"));
        if(renamed) System.out.println("Rename Success");
        else System.out.println("Rename Failed");

        boolean deleted = f.delete();
        if(deleted) System.out.println("delete Success");
        else System.out.println("delete Failed");
    }

    public static void exp4(String path){
        File f=new File(path);

        //check if its a dir or not
        if(f.isDirectory()){
            String[] fContents = f.list();
            int dirCount=0,fileCount=0;
            List<String> dirList=new ArrayList<>();
            List<String> fileList=new ArrayList<>();
            for(String c:fContents){
                File tmp=new File(path+"/"+c);
                if(tmp.isDirectory()) {
                    dirCount++;
                    dirList.add(c);
                }
                else{
                    fileCount++;
                    fileList.add(c);
                }

            }

            System.out.println("Directory of "+path+ " contains: "+dirCount+" directories and "+ fileCount+" files");
            System.out.println("Files list: ");
            fileList.forEach(x->System.out.println(x));
            System.out.println();
            System.out.println("Dirs list: ");
            dirList.forEach(x->System.out.println(x));
        }
    }

    public static void exp5(String path){
        File f=new File(path);

        // create a filenamefilter which finds files name contains "test"
        FilenameFilter getTest=new MyFileNameFilter("test");

        String[] result = f.list(getTest);
        for(String c:result)
        System.out.println(c);
    }
}
