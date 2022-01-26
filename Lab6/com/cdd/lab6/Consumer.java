package com.cdd.lab6;

public class Consumer implements Runnable {
    private final String name;
    public final Buffer buffer;

    public Consumer(String name, Buffer buffer) {
        this.name = name;
        this.buffer = buffer;
    }

    public void run() {
        while(true) {
            int y = buffer.pop();

            System.out.println(name + ": Got " + y);
        }
    }
}
