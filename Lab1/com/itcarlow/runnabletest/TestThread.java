/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itcarlow.runnabletest;

/**
 * Driver program for demonstrating threads in Java using the Runnable interface.
 *
 * @author KEHOEJ
 */
public class TestThread {
    /**
     * The main method creates two instances of the {@code RunnableDemo} class
     * and calls their {@code start()} function.
     *
     * @param args Unused.
     */
    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo( "Thread-1");
        R1.start();
        RunnableDemo R2 = new RunnableDemo( "Thread-2");
        R2.start();
    }   
    
}
