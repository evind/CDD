package com.cdd.lab5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 25/01/2022
 * @license GPLv3
 * @briefDescription Driver program used to show a solution to the
 *                   Leaders-Followers problem.
 *
 * @fullDescription The Leaders-Followers problem requires threads to be to
 *                  proceed in pairs: a Leader thread cannot proceed unless
 *                  there is a Follower thread waiting, and vice-versa.
 *                  Successful output will be in pairs such as "Leader1
 *                  starts dancing" and "Follower1 starts dancing," or the
 *                  reverse.
 *
 */

/**
 * Driver containing the {@code main} class.
 */
public class Main {
    /** Maximum number of threads in thread pool */
    static final int MAX_T = 8;

    /**
     * Creates an ExclusiveQueue, 3 Leader instances, 3 Follower instances, and
     * a thread pool that will be used to execute them. The thread pool will then
     * be shut down.
     */
    public static void main(String[] args) {
        LeaderFollowerQueue queue = new LeaderFollowerQueue();
        // creates four tasks
        Runnable leader1 = new Leader("Leader1", queue);
        Runnable follower1 = new Follower("Follower1", queue);
        Runnable leader2 = new Leader("Leader2", queue);
        Runnable follower2 = new Follower("Follower2", queue);
        Runnable leader3 = new Leader("Leader3", queue);
        Runnable follower3 = new Follower("Follower3", queue);

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // pass the Runnables to the pool to execute
        pool.execute(leader1);
        pool.execute(follower1);
        pool.execute(leader2);
        pool.execute(follower2);
        pool.execute(leader3);
        pool.execute(follower3);

        // pool shutdown
        pool.shutdown();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
