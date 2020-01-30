package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.resourcebundle;

import java.util.ListResourceBundle;

public class MyRB_de extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] resources=new Object[3][2];

        // first key value pair for title
        resources[0][0]="title";
        resources[0][1]="Mein Programm";

        // second key value pair for stop text
        resources[1][0]="StopText";
        resources[1][1]="Anschlag";

        // third key value pair for start text
        resources[2][0]="StartText";
        resources[2][1]="Anfang";

        return resources;
    }
}
