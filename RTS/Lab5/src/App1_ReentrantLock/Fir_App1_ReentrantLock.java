package App1_ReentrantLock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Fir_App1_ReentrantLock extends Thread {
    private final Lock lock1;
    private final Lock lock2;
    private final CyclicBarrier barrier;
    private final int minActivity1;
    private final int maxActivity1;
    private final int minActivity2;
    private final int maxActivity2;
    private final int transitionTime;
    private final String name;

    public Fir_App1_ReentrantLock(Lock lock1, Lock lock2, CyclicBarrier barrier, int minActivity1, int maxActivity1, int minActivity2, int maxActivity2, int transitionTime, String name) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.barrier = barrier;
        this.minActivity1 = minActivity1;
        this.maxActivity1 = maxActivity1;
        this.minActivity2 = minActivity2;
        this.maxActivity2 = maxActivity2;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        // State 1
        System.out.println(name + " started execution.");
        System.out.println(name + ": Executing first activity.");
        int k1 = (int) Math.round(Math.random() * (maxActivity1 - minActivity1) + minActivity1);
        for (int i = 0; i < k1 * 100000; i++) {
            i++;
            i--;
        }
        System.out.println(name + ": First activity completed.");

        try {
            if (lock1.tryLock(5000, TimeUnit.MILLISECONDS)) {
                System.out.println(name + ": first token acquired.");
                // State 2
                System.out.println(name + ": Executing second activity.");
                int k2 = (int) Math.round(Math.random() * (maxActivity2 - minActivity2) + minActivity2);
                for (int i = 0; i < k2 * 100000; i++) {
                    i++;
                    i--;
                }
                System.out.println(name + ": Second activity completed.");

                if (lock2.tryLock(5000, TimeUnit.MILLISECONDS)) {
                    System.out.println(name + ": second token acquired.");
                    // State 3
                    try {
                        Thread.sleep(transitionTime * 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println(name + ": first token released.");
                    lock1.unlock();
                    System.out.println(name + ": second token released.");
                    lock2.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // State 4
        System.out.println(name + ": Waiting for the other thread to reach this point.");
        try {
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
