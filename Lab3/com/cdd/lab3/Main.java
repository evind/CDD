package com.cdd.lab3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 23/01/2022
 * @license GPLv3
 * @briefDescription Driver program used to show a reusable barrier in action.
 *
 * @fullDescription Creates an instance of {@code ReusableBarrier} which will
 *                  be used to synchronise N threads. Each thread will begin
 *                  executing {@code Task1.run()}, wait at a certain point for
 *                  each other thread to signal that they have also arrived at
 *                  this point (via shared access to the {@code ReusableBarrier}
 *                  before continuing to execute the remaining code. Each thread
 *                  will then signal the {@code ReusableBarrier} to reset so
 *                  that it can be reused (e.g. in a loop).
 *
 */

/**
 * Driver containing the {@code main} class.
 */
public class Main {
    /** Maximum number of threads in thread pool */
    static final int MAX_T = 8;

    /**
     * Creates a ReusableBarrier, 4 Task1 instances and a thread pool that
     * will be used to execute them. The thread pool will then be shut down.
     */
    public static void main(String[] args) {
        ReusableBarrier barrier1 = new ReusableBarrier(4);
        // creates four tasks
        Runnable r1 = new Task1("Task 1", barrier1);
        Runnable r2 = new Task1("Task 2", barrier1);
        Runnable r3 = new Task1("Task 3", barrier1);
        Runnable r4 = new Task1("Task 4", barrier1);

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // passes the Task1 objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);

        // pool shutdown ( Step 4)
        pool.shutdown();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
