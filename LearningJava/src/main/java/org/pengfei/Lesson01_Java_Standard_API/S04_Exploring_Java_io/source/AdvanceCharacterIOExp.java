package org.pengfei.Lesson01_Java_Standard_API.S04_Exploring_Java_io.source;

import java.io.*;

public class AdvanceCharacterIOExp {

    public static void exp1(String filePath) {
        try (FileReader fr = new FileReader(filePath)) {
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp2(String fPath0, String fPath1, String fPath2) {
        String str = "Now is the time for all good men\nto come to the aid of their country\nand pay their due taxes";
        char[] buf = new char[str.length()];
        str.getChars(0, str.length(), buf, 0);
        try (FileWriter f0 = new FileWriter(fPath0);
             FileWriter f1 = new FileWriter(fPath1);
             FileWriter f2 = new FileWriter(fPath2);
        ) {
            //write to the first file
            for (int i = 0; i < buf.length; i += 2) f0.write(buf[i]);

            // write to the second file
            f1.write(buf);

            // write to the third
            f2.write(buf, buf.length - buf.length / 4, buf.length / 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp3() {
        String str = "abcdefghigklmn";
        int length = str.length();
        char[] buf = new char[length];
        str.getChars(0, length, buf, 0);
        int c;
        System.out.println("Read the full buffer: ");
        try (CharArrayReader in = new CharArrayReader(buf)) {
            while ((c = in.read()) != -1) System.out.print((char) c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Read the first 5 character of the buffer: ");
        try (CharArrayReader in2 = new CharArrayReader(buf, 0, 5)) {
            while ((c = in2.read()) != -1) System.out.print((char) c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp4(String filePath) {
        String str = "This is some text in an array";
        int length = str.length();
        char[] buf = new char[length];
        str.getChars(0, length, buf, 0);

        CharArrayWriter f = new CharArrayWriter();
        try {
            //write the content of buf to the array writer
            f.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //print bufferWriter as string
        System.out.println("Buffer as a string:");
        System.out.println(f.toString());

        //print bufferWriter as an array
        System.out.println("Buffer as an Array:");
        char[] chars = f.toCharArray();
        for (char c : chars) System.out.print(c);

        // Write to a file
        try (FileWriter fOut = new FileWriter(filePath)) {
            f.writeTo(fOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Doing a reset");
        f.reset();

        for (int i = 0; i < 3; i++) f.write('X');
        System.out.println(f.toString());
    }

    public static void exp5() {
        String str = "This is a &copy; copyright symbol. but this is &copy not.\n";
        int length = str.length(), c;
        char[] chars = new char[length];
        str.getChars(0, length, chars, 0);
        CharArrayReader in = new CharArrayReader(chars);
        boolean marked = false;
        try (BufferedReader f = new BufferedReader(in)) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '&':
                        if (!marked) {
                            f.mark(32);
                            marked = true;
                        } else marked = false;
                        break;
                    case ';':
                        if (marked) {
                            System.out.print("(c)");
                            marked = false;
                        } else System.out.print((char) c);
                        break;
                    case ' ':
                        if (marked) {
                            marked = false;
                            f.reset();
                            System.out.print("&");
                        } else System.out.print((char) c);
                        break;
                    default:
                        if (!marked) System.out.print((char) c);
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exp6() {
        String str = "if (a == 4) then a = 0;\n";
        int length = str.length(), c;
        char[] buf = new char[length];
        str.getChars(0, length, buf, 0);

        CharArrayReader in = new CharArrayReader(buf);

        try (PushbackReader f = new PushbackReader(in)) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '=':
                        if ((c=f.read())=='=') {
                            System.out.print("equals");
                        } else {
                            System.out.print("assigns");
                            f.unread(c);
                        }
                        break;
                    default:
                        System.out.print((char) c);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exp7(){
        String str;
        Console con=System.console();

        // if no console, exit the program
        if(con==null) {
            System.out.println("Can't create console");
            return;
        }

        //Read a string and display it
        str=con.readLine("Enter a string: ");
        con.printf("Here is your input: %s\n",str);

    }
}
