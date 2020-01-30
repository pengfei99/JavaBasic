package org.pengfei.Lesson01_Java_Standard_API.S03_Exploring_Java_util.source.TimerTask;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    @Override
    public void run() {
     System.out.println("Timer task executed");
    }
}
