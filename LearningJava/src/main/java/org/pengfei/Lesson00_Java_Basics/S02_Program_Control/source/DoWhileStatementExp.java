package org.pengfei.Lesson00_Java_Basics.S02_Program_Control.source;

import java.io.IOException;

public class DoWhileStatementExp {

    public static void exp1() throws IOException {
        char ch;
        do{
            System.out.println("Press a key followed by Enter: ");
            ch=(char)System.in.read();
            System.out.println("The character you entered is : "+ch);
        }while(ch!='q');
    }
}
