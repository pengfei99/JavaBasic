package org.pengfei.Lesson00_Java_Basics.S09_Using_IO.source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CompareFiles {
    private final String file1;
    private final String file2;

    public CompareFiles(String f1,String f2){
        file1=f1;
        file2=f2;
    }

    /*
    * break is used to exit (escape) the for-loop, while-loop, switch-statement that you are currently executing.

return will exit the entire method you are currently executing (and possibly return a value to the caller, optional).
    * */
    public boolean doCompare(){
        boolean result=true;
        int i,j;
        try(var fin1=new FileInputStream(file1); var fin2=new FileInputStream(file2)){
            do{
                i=fin1.read();
                j=fin2.read();
                if(i!=-1&&j!=-1) {
                    if(i!=j) {result=false;
                        System.out.println("i: "+i+" j: "+j);
                    break;}

                }
                else if(i==-1&&j==-1) break;
                else return false;
            }while (i!=-1&&j!=-1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void cleanCompare(){
        int i,j;
        try(var fin1=new FileInputStream(file1); var fin2=new FileInputStream(file2)){
            do{
                i=fin1.read();
                j=fin2.read();
                if(i!=j) break;
            }while (i!=-1&&j!=-1);
            if(i!=j) System.out.println(false);
            else System.out.println(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
