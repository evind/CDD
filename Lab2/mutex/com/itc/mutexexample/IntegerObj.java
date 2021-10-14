/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.mutexexample;
import java.util.concurrent.Semaphore;

/**
 *
 * @author joe
 * @author Evin Darling (C00144257)
 * @date 11/10/2021
 * @license GPLv3
 * @briefDescription This class implements an object representation of an
 *                   integer.
 * @fullDescription A Semaphore is initialised to allow only 1 thread at a time,
 *                  so that it can effectively be used as a mutex lock - 
 *                  limiting access to the critical section to one thread at
 *                  a time.
 */
class IntegerObj {
    private Semaphore mutex = new Semaphore(1);
    int value;
    IntegerObj(int val) {
        this.value = val;
    }

    /**
     * Attempts to acquire a lock before incrementing `value` by 1. If a lock
     * can be acquired, only one thread may execute the critical section at a
     * time. After the critical section has executed the lock will be released.
     */
    int inc(){
        try {
            mutex.acquire();
            this.value++;
        } catch (InterruptedException e) {
            // exception
        } finally {
            mutex.release();
        }
        return this.value;
    }
}
