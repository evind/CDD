package com.cdd.lab7;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 27/01/2022
 * @license GPLv3
 * @briefDescription The philosopher class simulates the philosopher's from the
 *                   the problem. They will 1. think, 2. get forks, 3. eat, and
 *                   4. put forks down afterwards.
 *
 */

import java.util.concurrent.Semaphore;

/**
 * This class implements {@link Runnable} to simulate the philosopher. It contains
 * all necessary synchronisation tools that are shared between every philosopher
 * thread.
 */
public class Philosopher implements Runnable {
    /** The name of the thread. */
    private final int philosopherID;

    /** An array containing the "forks" (ie semaphore's initialised to 1) */
    static Semaphore[] forks;

    /**
     * This semaphore(4) ensures that only 4 philosophers are allowed to sit at
     * the table at one time, eliminating the possibily of deadlock.
     */
    static Semaphore footman;

    /**
     * Counter that keeps track of how many times each philosopher has eaten.
     * Helpful for visualising starvation (or lack thereof).
     */
    private int eaten;

    /**
     * Main constructor method.
     * @param id
     */
    public Philosopher(int id) {
        this.philosopherID = id;
        forks = new Semaphore[5];
        footman = new Semaphore(4);
        this.eaten = 0;

        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    /**
     * This function gives access to the fork left of the philosopher who called it.
     * @param j The index of the fork to the left of the philosopher.
     * @return The index of the fork to the left of the philosopher.
     */
    public int left(int j) {
        return j;
    }

    /**
     * This function gives access to the for to the right of the philosopher who called
     * it. Modulo 5 ensures a value between 0-4 is always returned.
     * @param j The index of the fork to the right of the philosopher.
     * @return  The index of the fork to the right of the philosopher.
     */
    public int right(int j) {
        return (j + 1) % 5;
    }

    /**
     * Attempts to pick up right and left forks for the calling philosopher. The
     * {@code footman} semaphore blocks a 5th philosopher from picking up any forks
     * if 4 others have already done so. The semaphore representing the right and
     * left forks are acquired: causing a philosopher to wait if one of these has
     * already been acquired (picked up) by another philosopher, otherwise allowing
     * them to pick it up otherwise.
     * @throws InterruptedException
     */
    public void getForks() throws InterruptedException {
        footman.acquire();
        forks[right(philosopherID)].acquire();
        System.out.println("philosopher" + philosopherID + ": got right fork (" + right(philosopherID) + ")");
        forks[left(philosopherID)].acquire();
        System.out.println("philosopher" + philosopherID + ": got left fork (" + left(philosopherID) + ")");
    }

    /**
     * Allows the philosopher to "put down" his right and left forks. This is done by
     * releasing the fork's semaphore. The {@code footman} is also released, signalling
     * that the philosopher has finished eating and another may be allowed to proceed.
     */
    public void putForks() {
        forks[right(philosopherID)].release();
        forks[left(philosopherID)].release();
        footman.release();
    }

    /**
     * Each philosopher will think, get forks, eat, replace forks. Thinking and
     * eating are represented by statements printed to the screen. A philosopher
     * will have to wait when calling {@code getForks} if 1. there are already 4
     * philosophers at the table or 2. there is not a left and right fork available.
     */
    public void run() {
        while(true) {
            System.out.println("philosopher" + philosopherID + ": Thinking...");

            try { getForks(); } catch (InterruptedException e) { e.printStackTrace(); }

            System.out.println("philosopher" + philosopherID + ": Eating..." + " [" + eaten + " times]");

            this.eaten++;

            putForks();

            // throttling
//            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
