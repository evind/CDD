/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itcarlow.runnabletest;

/**
 * This class implements Java's {@link java.lang.Runnable Runnable} interface.
 *
 * @author KEHOEJ
 */
class RunnableDemo implements Runnable {
   /**
    * This holds the Thread instance.
    */
   private Thread t;

   /**
    * The name used to identify the Thread instance.
    */
   private String threadName;
   
   /**
    * Constructor method.
    *
    * @param name A string use to label this instance.
    */
   RunnableDemo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   /**
    * Implementation of the {@link java.lang.Runnable#run run()} method from
    * the {@link java.lang.Runnable Runnable} interface. This method directs 
    * it's thread to print it's name and number counting down from 4 to 1,
    * sleeping for 50 milliseconds in between each iteration.
    */
   @Override
   public void run() {
      System.out.println("Running " +  threadName );
      try {
         for(int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + ", " + i);
            // Let the thread sleep for a while.
            Thread.sleep(50);
         }
      } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   /**
    * If no thread already exists, this function creates an instance of the
    * Java {@link java.lang.Thread Thread} class, then calls it's
    * {@link java.lang.Thread#start start()} function - beginning execution
    * by calling the {@code run()} method of this thread.
    */
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}
