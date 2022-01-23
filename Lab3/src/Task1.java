public class Task1 implements Runnable {
    private final String name;
    private final ReusableBarrier barrier1;

    public Task1(String task1, ReusableBarrier barrier1) {
        name = task1;
        this.barrier1 = barrier1;
    }

    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println("Beginning " + name + ". i = " + i);    // rendezvouz

            try {
                barrier1.waitForAll();                       // barrier wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Stopping " + name + ". i = " + i);     // critical point

            try {
                barrier1.reset();                       // barrier reset
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
