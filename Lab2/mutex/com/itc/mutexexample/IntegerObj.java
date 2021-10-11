/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.mutexexample;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author joe
 */
class IntegerObj {
    private ReentrantLock mutex = new ReentrantLock();
    int value;
    IntegerObj(int val) {
        this.value = val;
    }
    int inc(){
        mutex.lock();
        this.value++;
        mutex.unlock();
        return this.value;
    }
}
