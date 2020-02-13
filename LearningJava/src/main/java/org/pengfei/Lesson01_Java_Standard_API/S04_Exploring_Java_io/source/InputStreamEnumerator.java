package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Vector;

/* This class takes a list of file names, and build an enumeration of fileInputStream */
public class InputStreamEnumerator implements Enumeration<FileInputStream> {
    private Enumeration<String> files;

    public InputStreamEnumerator(Vector<String> files){
        this.files=files.elements();
    }

    @Override
    public boolean hasMoreElements() {
        return files.hasMoreElements();
    }

    @Override
    public FileInputStream nextElement() {
        try {
            return new FileInputStream(files.nextElement());
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
