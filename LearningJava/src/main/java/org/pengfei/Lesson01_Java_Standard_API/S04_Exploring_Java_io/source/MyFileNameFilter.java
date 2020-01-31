package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source;

import java.io.File;
import java.io.FilenameFilter;

public class MyFileNameFilter implements FilenameFilter {
    private final String searchPattern;
    public MyFileNameFilter(String searchPattern){
        this.searchPattern=searchPattern;
    }
    @Override
    public boolean accept(File dir, String name) {
        return name.contains(searchPattern);
    }
}
