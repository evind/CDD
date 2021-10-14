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
 * @author Evin Darling (C00144257)
 * @date 11/10/2021
 * @license GPLv3
 * @briefDescription This class implements an object representation of an
 *                   integer using an {@code AtomicInteger}.
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

    /**
     * This method will loop until it successfully increments the value of
     * the {@code AtomicInteger} by 1.
     */
    int inc(){
        while(true) {
            int currentVal = this.getValue();
            int newVal = currentVal + 1;
            if (value.compareAndSet(currentVal, newVal)) {
                return 0;
            }
        }
    }
}
