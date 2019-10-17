package org.pengfei.Lesson00_Java_Basics.S02_Program_Control.source;

import java.io.IOException;
import java.util.Scanner;

public class KeyboardInputExp {
    public static void getKeyboardInput() throws IOException {
        System.out.println("Enter something, then press ENTER: ");



        /**
         * you will notice that, input only takes one char at a time, if you enter more than one char, the first
         * read() returns the first char. But the other chars are not lost, they are pending in the input buffer until
         * you read them.
         * For example, we enter hello, the first read will return h, then the for loop will return the rest*/
        char input;
        input = (char) System.in.read();

        System.out.println("Your input is: " + input);

        for (int i = 0; i < 4; i++) {

            input = (char) System.in.read();

            System.out.println("Your input is: " + input);
        }
    }

    public static void getKeyboardInputWithScanner(){
        Scanner scan= new Scanner(System.in);
        String text;
        System.out.println("Enter some string: ");
        text=scan.nextLine();
        System.out.println("Your input is: " + text);
        int num;
        System.out.println("Enter some int: ");
        num=scan.nextInt();
        System.out.println("Your input is: " + num);
    }
}
