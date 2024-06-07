package App2_ReentrantLock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Fir_App2_1_ReentrantLock extends Thread {
    private final Lock lock;
    private final CyclicBarrier barrier;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;
    private final String name;

    public Fir_App2_1_ReentrantLock(Lock lock, CyclicBarrier barrier, int minActivity, int maxActivity, int transitionTime, String name) {
        this.lock = lock;
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
            if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
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
                lock.unlock();
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