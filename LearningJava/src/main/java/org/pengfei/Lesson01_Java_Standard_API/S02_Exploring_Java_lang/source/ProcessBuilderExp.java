package org.pengfei.Lesson01_Java_Standard_API.S02_Exploring_Java_lang.source;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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
            // waitFor() method will let the java main process waits the called process to finish. when the called
            // process finish, an exit code will be returned (0 if it finishes correctly).
            int exitCode=p.waitFor();
            System.out.println("\nExited with error code: "+ exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // redirect output to a file
    public static void exp2(){
        String homeDir=System.getProperty("user.home");
        ProcessBuilder processBuilder=new ProcessBuilder();
        processBuilder.command("bash","-c","date");

        // redirect standard output to a file
        File fileName = new File(String.format("%s/Documents/tmp/output.txt", homeDir));
        processBuilder.redirectOutput(fileName);

        try {
            Process p=processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // redirect input and output, we cat the input file, as the output is a file, so it's like copy one file to another
    public static void exp3(){
        ProcessBuilder processBuilder=new ProcessBuilder();
        try {
            processBuilder.command("cat")
                    .redirectInput(new File("src/resources","input.txt"))
                    .redirectOutput(new File("src/resources","output.txt"))
                    .start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

// get and set the process executing environment variables
    public static void exp4(){
        ProcessBuilder pb=new ProcessBuilder();
        Map<String, String> env = pb.environment();

        env.forEach((key,value)->{
            System.out.printf("%s = %s %n", key, value);
        });

        // we can also only get one attribute
        System.out.printf("%s %n",env.get("mode"));

        // we can reset values or put new values in env, and all attributes in env can be accessed by the subprocess
        // The following example, we add an attribute mode, and use subprocess bash to get the mode value.
        env.put("mode","development");
        pb.command("bash","-c","echo $mode");
        try {
            pb.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // changing working directory
    public static void exp5(){
        ProcessBuilder pb=new ProcessBuilder();
        pb.command("bash","-c","pwd");

        // reset working directory to /tmp
        pb.directory(new File("/tmp"));

        try {
            Process p=pb.start();
            var reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while((line=reader.readLine())!=null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ProcessBuilder non-blocking operation
    public static void exp6(){
        // we use executor to run the process
        ExecutorService executor = Executors.newSingleThreadExecutor();

        ProcessBuilder pb = new ProcessBuilder();

        pb.command("bash","-c", "ping -n -c 3 google.com");

        try{
            Process process = pb.start();
            System.out.println("processing ping command >>> ");

            ProcessTask task = new ProcessTask(process.getInputStream());
            Future<List<String>> future = executor.submit(task);

            // non-blocking, doing other tasks, while the executor runs the task
            // if blocking, the two println will wait the ping finish to run.
            System.out.println("doing task1 ...");
            System.out.println("doing task2 ...");

            List<String> results = future.get(5, TimeUnit.SECONDS);
            for(String res: results){
                System.out.println(res);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }

    }

    //nested class implements Callable
    private static class ProcessTask implements Callable<List<String>>{

        private InputStream inputStream;

        public ProcessTask(InputStream in){
            this.inputStream=in;
        }
        @Override
        public List<String> call() throws Exception {
            return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.toList());
        }
    }

}
