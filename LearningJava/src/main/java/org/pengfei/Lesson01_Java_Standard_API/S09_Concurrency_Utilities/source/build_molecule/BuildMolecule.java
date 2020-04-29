package org.pengfei.Lesson01_Java_Standard_API.S09_Concurrency_Utilities.source.build_molecule;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

/**
 * A molecule has a group of atoms, atoms may belongs to different types, and the number of atoms of each type may
 * be different too. So we need to control we have all the types and the number of each type must be correct.
 * The idea is we use a semaphore to control the number of each type. We use a cyclicBarrier to control the
 * total number of atoms. If these numbers are all correct, then we are sure we have all the types of atoms.
 * To build the next molecule, we need to release the semaphore, the cyclicBarrier reset automatically after barrier is
 * reached. So we introduce a custom phaser, each time a phase is finished(a molecule is build), the phaser release
 * the semaphore
 *
 * In the following example, we still build h2o, so we have one semaphore for h with 2 permit, one semaphore for o with
 * 1 permit. We could imagine, if we want to build Nahco3, we need one semaphore for Na,h,c with 1 permit, and a semaphore
 * for o with 3 permit.
 * */
public class BuildMolecule {

    private final static int COUNT_H = 2;
    private final static int COUNT_O = 1;
    private final static int TOTAL_COUNT = COUNT_H + COUNT_O;

    private final Semaphore hSemaphore = new Semaphore(COUNT_H);
    private final Semaphore oSemaphore = new Semaphore(COUNT_O);
    private final CyclicBarrier preReleaseBarrier = new CyclicBarrier(TOTAL_COUNT);
    private final Phaser postReleasePhaser = new Phaser(TOTAL_COUNT) {
        protected boolean onAdvance(int phase, int parties) {
            // Release semaphores after molecule is built
            hSemaphore.release(COUNT_H);
            oSemaphore.release(COUNT_O);
            // Don't terminate phaser, if you want to terminate the phaser, onAdvance must return true
            return false;
        }
    };

    public void hydrogen() throws InterruptedException {
        hSemaphore.acquire();

        try {
            preReleaseBarrier.await();
            System.out.println("H");
            //no need to use the use arriveAndAwaitAdvance() to suspend the thread, because semaphore already did.
            postReleasePhaser.arrive();

        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen() throws InterruptedException {
        oSemaphore.acquire();
        try {
            preReleaseBarrier.await();
            System.out.println("O");
            //no need to use the use arriveAndAwaitAdvance() to suspend the thread, because semaphore already did.
            postReleasePhaser.arrive();

        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        BuildMolecule molecule=new BuildMolecule();
        ThreadForBuildMolecule h1=new ThreadForBuildMolecule("h","h1",molecule);
        ThreadForBuildMolecule h2=new ThreadForBuildMolecule("h","h2",molecule);
        ThreadForBuildMolecule h3=new ThreadForBuildMolecule("h","h3",molecule);
        ThreadForBuildMolecule o1=new ThreadForBuildMolecule("o","o1",molecule);
        ThreadForBuildMolecule o2=new ThreadForBuildMolecule("o","o2",molecule);
        h1.start();
        h2.start();
        h3.start();
        o1.start();
        o2.start();

        // we have 3 h and 2 o, it prints ohh, and in deadlock, because it needs one more h to print a new h2o
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new ThreadForBuildMolecule("h","h4",molecule).start();

    }
    }
