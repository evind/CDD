package com.cdd.lab3;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 23/01/2022
 * @license GPLv3
 * @briefDescription A reusable barrier that can be used by N threads to signal
 *                   when they have reached a rendezvous point before continuing
 *                   execution.
 *
 * @fullDescription
 *
 */

import java.util.concurrent.Semaphore;

/**
 * An instance of this ReusableBarrier can be shared between N threads and used to
 * force threads to wait for all other threads to arrive at a rendezvous point in
 * the code. After all threads signal they have arrived, the critical section of the
 * code can be executed by all threads, then {@code reset()} can be used to determine
 * when each thread has finished executing the critical section and reset the barrier
 * to a state where it can be reused.
 */
public class ReusableBarrier {
    /** A semaphore used as a mutex lock to control access to the `count` variable. */
    private final Semaphore mutex;

    /**
     * A semaphore used to make threads wait after they have reached the rendezvous point.
     * When every thread has arrived, it is released allowing each thread to proceed.
     */
    private static Semaphore turnstile1;

    /**
     * A semaphore used to make threads wait after they have finished executing their
     * critical code. When the last thread arrives, it is released, allowing the
     * barrier to be reset to it's initial state, so it will function in the next
     * iteration of a loop.
     */
    private static Semaphore turnstile2;

    /** The number of threads that will use the barrier. */
    private static int numOfThreads;

    /** Keeps track of the number of threads that have reached the rendezvous point. */
    private static int count;

    /**
     * The constructor method.
     * @param num the number of threads that will use the barrier.
     */
    ReusableBarrier(int num) {
        mutex = new Semaphore(1);
        turnstile1 = new Semaphore(0);
        turnstile2 = new Semaphore(1);
        numOfThreads = num;
        count = 0;
    }

    /**
     * This method is used to make each thread wait for the other threads after they
     * have reached a rendezvous point. Once each thread signals that they have arrived
     * all threads are allowed to continue.
     * <p>
     * {@code turnstile1} starts at 0, all threads will wait here until the last thread
     * trips the conditional ({@code count == numOfThreads}). {@code turnstile1} is then
     * released, allowing all threads waiting at {@code turnstile1.acquire()} to proceed
     * to {@code turnstile1.release()}, one by one.
     *
     * @throws InterruptedException in case any semaphore acquires fail.
     */
    public void waitForAll() throws InterruptedException {
        mutex.acquire();
        count++;
        if (count == numOfThreads)  {
            turnstile2.acquire();
            turnstile1.release();
        }
        mutex.release();

        turnstile1.acquire();
        turnstile1.release();
    }

    /**
     * This method is used to reset the barrier after each thread has signalled it.
     * Similar to {@code waitForAll()} threads will wait at {@code turnstile2.acquire()}
     * until the conditional is tripped ({@code count == 0} signalling that all threads
     * have finished. All threads are released and the @{link ReusableBarrier} state
     * is reset to a condition that allows it to be used in another iteration of a loop.
     *
     * @throws InterruptedException in case semaphore acquires fail.
     */
    public void reset() throws InterruptedException {
        mutex.acquire();
        count--;
        if (count == 0) {
            turnstile1.acquire();
            turnstile2.release();
        }
        mutex.release();

        turnstile2.acquire();
        turnstile2.release();
    }
}
