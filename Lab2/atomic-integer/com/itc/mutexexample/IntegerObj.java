/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.mutexexample;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author joe
 */
class IntegerObj {
    private final AtomicInteger value;
    //int value;
    IntegerObj(int val) {
        this.value = new AtomicInteger(val);
    }

    public int getValue() {
      return this.value.get();
    }

    int inc(){
        while(true) {
            int currentVal = this.getValue();
            int newVal = currentVal + 1;
            if (value.compareAndSet(currentVal, newVal)) {
                return 0;  // return val does not matter
            }
        }
    }
}
