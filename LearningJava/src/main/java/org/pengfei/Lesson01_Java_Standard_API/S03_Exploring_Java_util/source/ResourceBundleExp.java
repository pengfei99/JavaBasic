package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleExp {

    public static void exp1(){
        // Load the default bundle
        ResourceBundle rd=ResourceBundle.getBundle("org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.resourcebundle.MyRB");
        System.out.println("English version: ");
        System.out.println("Title: "+rd.getString("title"));
        System.out.println("Stop Text: "+rd.getString("StopText"));
        System.out.println("Start Text: "+rd.getString("StartText"));

        // Load the german bundle
        rd=ResourceBundle.getBundle("org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.resourcebundle.MyRB", Locale.GERMAN);
        System.out.println("German version: ");
        System.out.println("Title: "+rd.getString("title"));
        System.out.println("Stop Text: "+rd.getString("StopText"));
        System.out.println("Start Text: "+rd.getString("StartText"));
    }

    public static void exp2(){
        System.out.println("Current Locale: " + Locale.getDefault());
        // Load the default bundle
        ResourceBundle rd=ResourceBundle.getBundle("resources/MyLabels",Locale.US);
        System.out.println("English version: ");
        System.out.println("Title: "+rd.getString("title"));
        System.out.println("Stop Text: "+rd.getString("StopText"));
        System.out.println("Start Text: "+rd.getString("StartText"));

        /*// Load the german bundle
        rd=ResourceBundle.getBundle("MyRB", Locale.GERMAN);
        System.out.println("German version: ");
        System.out.println("Title: "+rd.getString("title"));
        System.out.println("Stop Text: "+rd.getString("StopText"));
        System.out.println("Start Text: "+rd.getString("StartText"));*/
    }
}
