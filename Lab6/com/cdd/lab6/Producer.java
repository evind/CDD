package com.cdd.lab6;

/**
 *
 * @author Evin Darling (C00144257)
 * @date 26/01/2022
 * @license GPLv3
 * @briefDescription A producer task that will demonstrate the production and adding
 *                   of data to be consumed by {@link Consumer} threads.
 *
 */

import java.util.Random;

/**
 * A producer task that will producer a random integer values and push them onto
 * a shared buffer as long as the buffer is not full. If the buffer is full, it
 * will wait until there is space to add new values.
 */
public class Producer implements Runnable {
    /** The name of the thread. */
    private final String name;

    /** The {@link Buffer} where produced values should be written to. */
    public final Buffer buffer;

    /**
     * The main constructor method.
     * @param name The name of the thread.
     * @param buffer The shared buffer to be used.
     */
    public Producer(String name, Buffer buffer) {
        this.name = name;
        this.buffer = buffer;
    }

    /**
     * Indefinitely produces random integer values between 0-100, then writes them
     * to the shared buffer.
     */
    public void run() {
        while(true) {
            Random rand = new Random();
            int x = rand.nextInt(100);
            System.out.println(name + ": " + x);

            buffer.push(x);
        }

        /**
         * The following code can be executed in place of the above to demonstrate
         * what happens when the producers stop producing. The consumer threads
         * wait indefinitely for values to be produced.
         */
//        for (int i = 0; i < 100; i++) {
//            Random rand = new Random();
//            int x = rand.nextInt(100);
//            System.out.println(name + ": " + x);
//
//            buffer.push(x);
//        }
    }
}