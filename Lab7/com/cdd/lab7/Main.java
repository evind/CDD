package com.cdd.lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 27/01/2022
 * @license GPLv3
 * @briefDescription Demonstrates a solution to the Dining Philosopher problem.
 *
 * @fullDescription The Dining Philosopher problem is one where a number of
 *                  threads' access to a limited set of resources must be
 *                  synchronised. There are 5 philosophers and 5 forks. Forks
 *                  can only be held by one philosopher at a time. Philosophers
 *                  need 2 forks to eat, deadlock should not be possible, starvation
 *                  must not be possible, and more than one philosopher should
 *                  be able to eat at a time.
 *
 */

/**
 * Driver containing the {@code main} class.
 */
public class Main {
    /** Maximum number of threads in thread pool. */
    static final int MAX_T = 8;

    /**
     * Creates 5 {@link Philosopher} threads and adds them to a thread pool for
     * execution.
     */
    public static void main(String[] args) {
        // creates four tasks
        Runnable philosopher0 = new Philosopher(0);
        Runnable philosopher1 = new Philosopher(1);
        Runnable philosopher2 = new Philosopher(2);
        Runnable philosopher3 = new Philosopher(3);
        Runnable philosopher4 = new Philosopher(4);

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // pass the Runnables to the pool to execute
        pool.execute(philosopher0);
        pool.execute(philosopher1);
        pool.execute(philosopher2);
        pool.execute(philosopher3);
        pool.execute(philosopher4);

        // pool shutdown
        pool.shutdown();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}