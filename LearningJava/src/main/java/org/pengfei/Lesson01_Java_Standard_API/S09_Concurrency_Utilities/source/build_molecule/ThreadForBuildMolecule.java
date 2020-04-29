package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.build_molecule;

import org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.semaphore.build_hho_semaphore.H2O;

public class ThreadForBuildMolecule extends Thread {
    private String threadName;
    private BuildMolecule molecule;
    private String id;

    public ThreadForBuildMolecule(String threadName, String id, BuildMolecule molecule) {
        super(id);
        this.threadName = threadName;
        this.id=id;
        this.molecule=molecule;
    }

    public void run(){
        if(threadName.equals("h")){
            try {
               // System.out.print(id+" prints: ");
                molecule.hydrogen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(threadName.equals("o")){
            try {
               // System.out.print(id+" prints: ");
                molecule.oxygen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else throw new IllegalArgumentException("wrong thread name");
    }
}
