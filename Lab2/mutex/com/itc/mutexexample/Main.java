/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.mutexexample;
import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joe
 * @author Evin Darling (C00144257)
 * @date 11/10/2021
 * @license GPLv3
 * @briefDescription Driver program that demonstrates a solution to the race
 *                   condition using a {@code Semaphore} as a mutex lock.
 *
 * @fullDescription This program creates a number of threads and adds them
 *                  to a thread pool for execution. Each task is passed a
 *                  reference to an IntegerObj instance, then attempts to
 *                  increment it's `total` value by 1, 500 times.
 *
 *                  Since there are 4 tasks incrementing the value 500 times,
 *                  the end value is expected to be 2000. This will always be
 *                  the case due to the usage of {@code Semaphore} that is
 *                  limited to 1 allowed thread at a time.
 */

/**
 * Main class holding the main driver function.
 */
public class Main {
    
      // Maximum number of threads in thread pool
    static final int MAX_T = 8;             
  
    public static void main(String[] args)
    {
        IntegerObj total= new IntegerObj(0);
        // creates five tasks
        Runnable r1 = new Task("task 1",total);
        Runnable r2 = new Task("task 2",total);
        Runnable r3 = new Task("task 3",total);
        Runnable r4 = new Task("task 4",total);    
          
        // creates a thread pool with MAX_T no. of 
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);  
         
        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
          
        // pool shutdown ( Step 4)
        pool.shutdown();    
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("total is: "+total.value);
    }
}
