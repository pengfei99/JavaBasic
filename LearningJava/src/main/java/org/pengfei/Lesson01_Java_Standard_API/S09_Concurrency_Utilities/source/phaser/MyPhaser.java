package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.phaser;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.SynchronizationObjExample;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {
    int numPhases;

    public MyPhaser(int parties, int phaseCount){
        super(parties);
        numPhases=phaseCount-1;
    }

    //override onAdvance() to execute the specified number of phases. Note each time onAdvance() is called, it is
    // passed the current phase and the number of registered parties.
    protected boolean onAdvance(int p, int regParties){
        //println for illustration only, do not display anything in real world app.
        System.out.println("Phase "+p+" completed\n");

        // if all phases have completed or no parties registered, return true to stop the phaser.
        if(p==numPhases||regParties==0) return true;
        // otherwise return false
        return false;
    }
}
