package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.util.StringTokenizer;

public class UtilityClassesExp {
    public static void exp1(){
         String str="title=Java: The best programing language;"+
                 "author= pengfei;"+
                 "date=27/01/2020;";

        StringTokenizer st=new StringTokenizer(str,"=;");
        while(st.hasMoreElements()){
            String key=st.nextToken();
            String val=st.nextToken();
            System.out.println(key+"\t"+val);
        }

    }
}
