package com.cdd.lab6;

import java.util.concurrent.Semaphore;

public class Buffer {
    private static int[] buffer;
    private static int count;
    public static Semaphore mutex;
    public static Semaphore items;
    public static Semaphore spaces;

    Buffer (int bufferSize) {
        buffer = new int[bufferSize];
        count = 0;
        mutex = new Semaphore(1);
        items = new Semaphore(0);
        spaces = new Semaphore(bufferSize);
    }
    
    public void push(int val) {
        try { spaces.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        try { mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }

        buffer[count] = val;
        count++;

        mutex.release();
        items.release();
    }

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
