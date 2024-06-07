package App2_Semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Fir_App2_1_Semaphore extends Thread {
    private final Semaphore semaphore;
    private final CyclicBarrier barrier;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;
    private final String name;

    public Fir_App2_1_Semaphore(Semaphore semaphore, CyclicBarrier barrier, int minActivity, int maxActivity, int transitionTime, String name) {
        this.semaphore = semaphore;
        this.barrier = barrier;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        // State 1
        System.out.println(name + " started execution.");
        try {
            semaphore.acquire(1);
            System.out.println(name + ": Token acquired.");
            // State 2
            System.out.println(name + ": Executing activity.");
            int k = (int) Math.round(Math.random() * (maxActivity - minActivity) + minActivity);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            System.out.println(name + ": Activity completed.");
            System.out.println(name + ": Releasing token.");
            try {
                Thread.sleep(transitionTime * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(name + ": Token released.");
            semaphore.release(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // State 3
        System.out.println(name + ": Waiting for the other threads to reach this point.");
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
