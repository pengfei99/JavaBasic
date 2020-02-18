package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class PathFilesOperationExample {
    public static void exp1(String path) {
        Path p = Path.of(path);

        /* get basic info of a path*/
        int elementNb = p.getNameCount();
        System.out.println("Path elements number: " + elementNb);

        // the last elements is the file name, if it is a file. getName(0) returns the first element.
        System.out.println("File name: " + p.getName(elementNb - 1));

        System.out.println("Path: " + p);

        System.out.println("Absolute Path: " + p.toAbsolutePath());
        System.out.println("Parent Path: " + p.getParent());

        /* check some properties of a file*/
        if (Files.exists(p)) System.out.println("File exists");
        else System.out.println("File does not exist");

        try {
            if (Files.isHidden(p)) System.out.println("File is hidden");
            else System.out.println("File is not hidden");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Files.isReadable(p)) System.out.println("File is readable");
        else System.out.println("File is not readable");

        if (Files.isWritable(p)) System.out.println("File is writable");
        else System.out.println("File is not writable");

        /* get file properties with BasicFileAttributes*/
        try {
            var attrs = Files.readAttributes(p, BasicFileAttributes.class);
            if (attrs.isDirectory()) {
                System.out.println("It's a directory");
            } else {
                System.out.println("It's not a directory");
            }

            if (attrs.isRegularFile()) {
                System.out.println("It is a normal file");
            } else {
                System.out.println("It is a normal file");
            }

            if (attrs.isSymbolicLink()) {
                System.out.println("It's a symbolic link");
            } else {
                System.out.println("It's not a symbolic link");
            }

            System.out.println("file last modified: " + attrs.lastModifiedTime());
            System.out.println("File size: " + attrs.size() + " Bytes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exp2(String path) {
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Path.of(path))) {
            System.out.println("Directory of: " + path);
            for (Path p : dirStream) {
                var attrs = Files.readAttributes(p, BasicFileAttributes.class);
                if (attrs.isDirectory()) System.out.print("<DIR> ");
                else System.out.print("     ");
                System.out.println(p.getName(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp3(String path) {
        /* we create a Directory stream with a filter option, this option can contains two forms of expressions.
         * - [chars]: We can use any one character in it to match any one characters. * or ? will be treated as
         *            normal character, not a wildcard. A range cna be specified by use of a hyphen, such as [a-z]
         * - {globlist}: Matches any one of the globs specified in a comma-separated list of globs.
         * We can use * or ? by adding \, so \*, \?, \\ will be used as wildcard not a simple character.
         */
        // starts with t followed by some character then ends with txt or java or class
        String glob1 = "[t]*.{txt,java,class}";
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Path.of(path), glob1)) {
            for (Path p : dirStream) {
                var attrs = Files.readAttributes(p, BasicFileAttributes.class);
                if (attrs.isDirectory()) System.out.print("<DIR> ");
                else System.out.print("     ");
                System.out.println(p.getName(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp4(String path) {
        //create a anonymous class which implements the Filter interface
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            // we want all the file which size > 200 bytes
            @Override
            public boolean accept(Path entry) throws IOException {
                if (Files.isRegularFile(entry) && Files.size(entry) > 200) return true;
                else return false;
            }
        };

        // create the DirectoryStream with the filter
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Path.of(path), filter)) {
            System.out.println("In directory: " + path + " All files which size > 200 bytes: ");
            for (Path p : dirStream) {
                System.out.println(p.getName(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp5(String path) {
        System.out.println("Directory tree starting with: " + path + ":\n");

        try {
            Files.walkFileTree(Path.of(path), new MyFileVisitor());
        } catch (AccessDeniedException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}