package com.cdd.lab6;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 26/01/2022
 * @license GPLv3
 * @briefDescription A consumer task that will demonstrate the retrieving and
 *                   consumption of data (by printing it to the screen) from the
 *                   shared buffer.
 *
 */

/**
 * A consumer task that will retrieve values from the shared buffer and consume
 * them (by printing them to the screen). If there are no values in the buffer
 * the thread will wait until a value is added.
 */
public class Consumer implements Runnable {
    /** The name of the thread. */
    private final String name;

    /** The {@link Buffer} where values should be retrieved from. */
    public final Buffer buffer;

    /**
     * The main constructor method.
     * @param name The name of the thread.
     * @param buffer The shared buffer to be used.
     */
    public Consumer(String name, Buffer buffer) {
        this.name = name;
        this.buffer = buffer;
    }

    /**
     * Indefinitely tries to retrieve values from the shared buffer and consumes them
     * (by printing a message with the value to the screen). If there are no values
     * on the buffer this thread will wait until a value is added.
     */
    public void run() {
        while(true) {
            int y = buffer.pop();

            System.out.println(name + ": Got " + y);
        }
    }
}
