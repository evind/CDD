package com.cdd.lab5;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 25/01/2022
 * @license GPLv3
 * @briefDescription The Runnable implementation for Leader threads.
 *
 */

/**
 * Implements {@link Runnable} and demonstrates a Leader thread which "dances"
 * but only if there is a {@link Follower} thread waiting to "dance."
 */
public class Leader implements Runnable {
    /**
     * Name of the thread.
     */
    private final String name;

    /**
     * Shared {@link LeaderFollowerQueue} instance containing all necessary variable to
     * synchronise the Leader and {@link Follower} threads.
     */
    public final LeaderFollowerQueue q;

    /**
     * Leader constructor method.
     * @param name The name of this thread.
     * @param queue The {@link LeaderFollowerQueue} used to synchronise the thread.
     */
    public Leader(String name, LeaderFollowerQueue queue) {
        this.name = name;
        this.q = queue;
    }

    /**
     * First checks if there are any followers waiting to "dance," if so, the follower is
     * released and the pair "dance." If not, this thread is added to the leader queue
     * and waits to be released by a follower thread.
     * <p>
     * After "dancing," this thread will wait at the rendezvous semaphore for it's paired
     * follower thread to release it, allowing both to finish execution.
     */
    public void run() {

        try { q.mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }

        if (q.followers > 0) {
            q.followers--;
            q.followerQ.release();
        } else {
            q.leaders++;
            q.mutex.release();
            try { q.leaderQ.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        System.out.println(name + " starts dancing...");
        try { q.rendezvous.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        q.mutex.release();
    }
}
