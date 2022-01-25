package com.cdd.lab3;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 23/01/2022
 * @license GPLv3
 * @briefDescription The Runnable implementation that will be executed by each thread.
 *
 */

/**
 * This class implements {@link Runnable} and demonstrates the capability of
 * the {@link ReusableBarrier} class to be used to synchronise N threads and
 * be reused (i.e. used in a loop).
 */
public class Task1 implements Runnable {
    /**
     * Name of the thread.
     */
    private final String name;

    /**
     * The {@link ReusableBarrier} that will be used to synchronise each thread.
     */
    private final ReusableBarrier barrier1;

    /**
     * The constructor method.
     *
     * @param task1    The name to be assigned to this instance.
     * @param barrier1 The {@link ReusableBarrier} to be used in synchronisation.
     */
    public Task1(String task1, ReusableBarrier barrier1) {
        name = task1;
        this.barrier1 = barrier1;
    }

    /**
     * This method contains the code to be executed by each thread. First, each
     * thread will print a message to signal that they are beginning execution.
     * Second, the {@link ReusableBarrier} is used to make the thread wait,
     * ensuring that each other thread prints it's beginning message before any
     * of them continue. After each thread has reached the barrier, the threads
     * are permitted to continue execution, printing a message signalling they
     * are stopping execution. The barrier is then signalled to reset so that
     * it may be reused. The same barrier is then used again in the next iteration
     * of the for loop.
     * <p>
     * Correctness can be seen in the output where N (e.g. 4) "Beginning..."
     * messages occur before N "Stopping..." messages every time, for 5 (or
     * however many) iterations.
     */
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println("Beginning " + name + ". i = " + i);     // rendezvous

            try {
                barrier1.waitForAll();                                  // barrier wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Stopping " + name + ". i = " + i);      // critical point

            try {
                barrier1.reset();                                       // barrier reset
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}