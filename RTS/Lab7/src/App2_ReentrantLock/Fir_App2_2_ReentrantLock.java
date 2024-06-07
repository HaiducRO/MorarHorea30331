package App2_ReentrantLock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Fir_App2_2_ReentrantLock extends Thread {
    private final Lock lock1;
    private final Lock lock2;
    private final CyclicBarrier barrier;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;
    private final String name;

    public Fir_App2_2_ReentrantLock(Lock lock1, Lock lock2, CyclicBarrier barrier, int minActivity, int maxActivity, int transitionTime, String name) {
        this.lock1 = lock1;
        this.lock2 = lock2;
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
            if (lock1.tryLock(5000, TimeUnit.MILLISECONDS)) {
                if (lock2.tryLock(5000, TimeUnit.MILLISECONDS)) {
                    System.out.println(name + ": Token acquired.");
                    // State 2
                    System.out.println(name + ": Executing activity.");
                    int k = (int) Math.round(Math.random() * (maxActivity - minActivity) + minActivity);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                    System.out.println(name + ": Activity completed.");
                    System.out.println(name + ": Releasing tokens.");
                    try {
                        Thread.sleep(transitionTime * 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + ": Tokens released.");
                    lock2.unlock();
                    lock1.unlock();
                }
            }
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