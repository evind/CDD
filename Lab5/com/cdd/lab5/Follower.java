package com.cdd.lab5;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 25/01/2022
 * @license GPLv3
 * @briefDescription The Runnable implementation for Follower threads.
 *
 */

/**
 * Implements {@link Runnable} and demonstrates a Follower thread which "dances"
 * but only if there is a {@link Leader} thread waiting to "dance."
 */
public class Follower implements Runnable {
    /**
     * Name of the thread.
     */
    private final String name;

    /**
     * Shared {@link LeaderFollowerQueue} instance containing all necessary variable to
     * synchronise the {@link Leader} and Follower threads.
     */
    public final LeaderFollowerQueue q;

    /**
     * Follower constructor method.
     * @param name The name of this thread.
     * @param queue The {@link LeaderFollowerQueue} used to synchronise the thread.
     */
    public Follower(String name, LeaderFollowerQueue queue) {
        this.name = name;
        this.q = queue;
    }

    /**
     * First checks if any leaders are waiting to "dance," if so, the leader is released
     * and the pair "dance." If not, this thread is added to the follower queue and waits
     * to be released by a leader thread.
     * <p>
     * After "dancing," this thread will release the rendezvous semaphore that it's paired
     * leader thread is waiting at, allowing both to finish execution.
     */
    public void run() {

        try { q.mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }

        if (q.leaders > 0) {
            q.leaders--;
            q.leaderQ.release();
        } else {
            q.followers++;
            q.mutex.release();
            try { q.followerQ.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        System.out.println(name + " starts dancing...");
        q.rendezvous.release();
    }
}
