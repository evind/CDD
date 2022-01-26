package com.cdd.lab6;

import java.util.Random;

public class Producer implements Runnable {
    private final String name;
    public final Buffer buffer;

    public Producer(String name, Buffer buffer) {
        this.name = name;
        this.buffer = buffer;
    }

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