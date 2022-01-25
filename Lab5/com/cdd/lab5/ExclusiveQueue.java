package com.cdd.lab5;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 25/01/2022
 * @license GPLv3
 * @briefDescription A simple class containing the necessary variables
 *                   to synchronise {@link com.cdd.lab5.Leader} and
 *                   {@link com.cdd.lab5.Follower} threads.
 *
 */

import java.util.concurrent.Semaphore;

/**
 *
 */
public class ExclusiveQueue {
    /** Keeps track of number of leader threads waiting to "dance." */
    public int leaders;

    /** Keeps track of number of follower threads waiting to "dance." */
    public int followers;

    /** A semaphore used as a mutex lock to control access to the leaders/followers variables. */
    public Semaphore mutex;

    /** A semaphore acting as a queue for the leader threads. */
    public Semaphore leaderQ;

    /** A semaphore acting as a queue for the follower threads. */
    public Semaphore followerQ;

    /** A semaphore used to confirm that a pair of threads are finished "dancing." */
    public Semaphore rendezvous;

    /**
     * Simple constructor method. {@code leaderQ} and {@code followerQ} are both
     * initialised to 0 so it will not be possible to signal unless a thread is
     * waiting.
     */
    ExclusiveQueue () {
        leaders = 0;
        followers = 0;
        mutex = new Semaphore(1);
        leaderQ = new Semaphore(0);
        followerQ = new Semaphore(0);
        rendezvous = new Semaphore(0);
    }
}
