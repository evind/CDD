/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.mutexexample;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author joe
 * @author Evin Darling (C00144257)
 * @date 11/10/2021
 * @license GPLv3
 * @briefDescription Implentation of the {@link java.lang.Runnable Runnable}
 *                   interface.
 *
 * @fullDescription This class is composed of an {@code IntegerObj} and a
 *                  {@code run()} method and is inteded to be executed in
 *                  a thread pool.
 */
public class Task implements Runnable {
private String name;
    private IntegerObj total;
    public Task(String task_1, IntegerObj total) {
        name=task_1;
        this.total = total;
    }
    
    /**
     * Attempt to increment the value of `total` by 1, 500 times, sleeping
     * briefly every 100 incrementations.
     */
    public void run()
    {
        try
        {
            for (int i = 0; i<500; i++)
            {
                total.inc();
                if (i%100==0){
                   Thread.sleep(100); 
                }
                
            }
            System.out.println(name+" complete");
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
