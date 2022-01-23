import java.util.concurrent.Semaphore;

public class ReusableBarrier {
    private Semaphore mutex;
    private static Semaphore turnstile1;
    private static Semaphore turnstile2;
    private static int numOfThreads;      // # of threads
    private static int count;             // # of threads that have arrived

    ReusableBarrier(int num) {
        mutex = new Semaphore(1);
        turnstile1 = new Semaphore(0);
        turnstile2 = new Semaphore(1);
        numOfThreads = num;
        count = 0;
    }

    public void waitForAll() throws InterruptedException {
        mutex.acquire();
        count++;
        if (count == numOfThreads)  {
            turnstile2.acquire();
            turnstile1.release();
        }
        mutex.release();

        turnstile1.acquire();
        turnstile1.release();
    }

    public void reset() throws InterruptedException {
        mutex.acquire();
        count--;
        if (count == 0) {
            turnstile1.acquire();
            turnstile2.release();
        }
        mutex.release();

        turnstile2.acquire();
        turnstile2.release();
    }
}