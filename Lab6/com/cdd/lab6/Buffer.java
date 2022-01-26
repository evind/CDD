package com.cdd.lab6;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 26/01/2022
 * @license GPLv3
 * @briefDescription A buffer class containing an array of integers and the
 *                   semaphores necessary to synchronise access to the buffer
 *                   by {@link Producer}/{@link Consumer} threads.
 *
 */

import java.util.concurrent.Semaphore;

/**
 * A buffer class containing an array of integers and the semaphores necessary
 * to synchronise access to the buffer by {@link Producer}/{@link Consumer
 * threads.
 */
public class Buffer {
    /** The array that will hold the buffer values. */
    private static int[] buffer;

    /** A count variable to keep track of the number of elements in the buffer. */
    private static int count;

    /** A semaphore that will be used as a mutex lock to synchronise read/writes. */
    public static Semaphore mutex;

    /** Used to control consumer's access to items on the buffer. */
    public static Semaphore items;

    /** Tracks the number of available spaces in the fixed-size buffer. */
    public static Semaphore spaces;

    /**
     * The main constructor method.
     * @param bufferSize The maximum number of elements that should fit in the buffer.
     */
    Buffer (int bufferSize) {
        buffer = new int[bufferSize];
        count = 0;
        mutex = new Semaphore(1);
        items = new Semaphore(0);
        spaces = new Semaphore(bufferSize);
    }

    /**
     * This method will be used by {@link Producer} threads to push data onto the buffer.
     * Checks if there are available spaces on the buffer via the {@code spaces} semaphore.
     * If so, the new value is added to the buffer and the count is incremented. {@code items}
     * is released to signal that an item is available for consumption by a Consumer.
     *
     * @param val The value of the produced data to push onto the buffer.
     */
    public void push(int val) {
        try { spaces.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        try { mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }

        buffer[count] = val;
        count++;

        mutex.release();
        items.release();
    }

    /**
     * This method will be used by {@link Consumer} threads to get data from the buffer
     * to consume. Checks if there are items available for consumption on the buffer. If
     * so, the data is retrieved and removed from the buffer. {@code spaces} is released
     * to signal that a space is again available for a Producer to write data.
     *
     * @return The value to be returned to the {@link Consumer} thread for consumption.
     */
    public int pop() {
        int val;
        try { items.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        try { mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }

        val = buffer[count-1];
        count--;

        mutex.release();
        spaces.release();

        return val;
    }
}
