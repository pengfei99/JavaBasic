package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderExp {
    public static void exp1(){
        ProcessBuilder processBuilder=new ProcessBuilder();

        // In ping command2
        // here option -n only shows the numeric ip address, string urls are discard
        // -c 3 means ping will stop after sending count ECHO_REQUEST packets
        String command1="pwd",
                command2= "ping -n -c 3 google.com",
                command3="/home/pliu/IdeaProjects/JavaBasic/LearningJava/src/main/java/org/pengfei/Lesson01_Java_Standard_API/S02_Exploring_Java_lang/source/hello.sh";

        // run shell command pwd
        processBuilder.command("bash", "-c", command1);

        // ping
        processBuilder.command("bash", "-c", command2);

        // run a shell script, if you have permission denied problem. try chmod a+x
        processBuilder.command(command3);

        try {
            Process p=processBuilder.start();

            // note getInputStream of a process will block the process
            BufferedReader reader= new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while((line=reader.readLine())!=null){
                System.out.println("java: "+line);
            }
            int exitCode=p.waitFor();
            System.out.println("\nExited with error code: "+ exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
