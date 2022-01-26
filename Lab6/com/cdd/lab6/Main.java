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
 * @briefDescription
 *
 * @fullDescription
 *
 */

public class Main {
    /** Maximum number of threads in thread pool */
    static final int MAX_T = 8;

    static Buffer buffer = new Buffer(10);

    public static void main(String[] args) {
        // creates four tasks
        Runnable producer1 = new Producer("Producer1", buffer);
        Runnable producer2 = new Consumer("Producer2", buffer);
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
