package org.pengfei.Lesson01_Java_Standard_API.S05_Exploring_nio.source;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/* Create a custom version of SimpleFileVisitor*/
public class MyFileVisitor extends SimpleFileVisitor<Path> {
    /* overwrite the visitFile method
    * here, we only print the file name that we are visiting, we can do more sophisticated
    * functionality such as filter the files and copy the special files to a backup devices.
    * */
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs){
        System.out.println(path);
        return FileVisitResult.CONTINUE;
    }
}
