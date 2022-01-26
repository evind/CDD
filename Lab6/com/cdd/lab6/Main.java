package com.cdd.lab6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 26/01/2022
 * @license GPLv3
 * @briefDescription Demonstrates a solution to the Producer-Consumer problem.
 *
 * @fullDescription The Producer-Consumer problem requires that Producer threads
 *                  produce data and Consumer threads consume that data in some
 *                  way. These events must be synchronised such that if there is
 *                  no data currently in the Buffer (a data structure used to
 *                  store/receive the produced data), Consumer threads will wait
 *                  until a Producer thread places data in there. This solution
 *                  uses a fixed size Buffer so producers must also wait before
 *                  adding more data to the Buffer if it is full.
 *
 */

/**
 * Driver containing the {@code main} class.
 */
public class Main {
    /** Maximum number of threads in thread pool */
    static final int MAX_T = 8;

    /** The buffer that will be used by the {@link Producer} and {@link Consumer threads}. */
    static Buffer buffer = new Buffer(10);

    /**
     * Creates {@link Producer} and {@link Consumer} threads and adds the to a thread
     * pool for execution.
     */
    public static void main(String[] args) {
        // creates four tasks
        Runnable producer1 = new Producer("Producer1", buffer);
        Runnable producer2 = new Producer("Producer2", buffer);
        Runnable consumer1 = new Consumer("Consumer1", buffer);
        Runnable consumer2 = new Consumer("Consumer2", buffer);

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // pass the Runnables to the pool to execute
        pool.execute(producer1);
        pool.execute(producer2);
        pool.execute(consumer1);
        pool.execute(consumer2);

        // pool shutdown
        pool.shutdown();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
