/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.mutexexample;

/**
 *
 * @author joe
 * @author Evin Darling (C00144257)
 * @date 11/10/2021
 * @license GPLv3
 * @briefDescription This class implements an object representation of an
 *                   integer.
 * @fullDescription This class' `inc()` functions is implemented with the
 *                  `synchronized` keyword. This makes it so this instance
 *                  method can only be executed by one thread at a time.
 */
class IntegerObj {
    int value;
    IntegerObj(int val) {
        this.value = val;
    }

    /**
     * Increments `value` by 1. Synchronized so that only one thread can execute
     * it at a time.
     */
    synchronized int inc(){
        this.value++;
        return this.value;
    }
}
