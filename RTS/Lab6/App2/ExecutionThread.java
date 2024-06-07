package Lab6.App2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
public class ExecutionThread extends Thread {
    Lock l1;

    CountDownLatch countDownLatch;
    int sleep_min, sleep_max, activity_min1, activity_max1;
    public ExecutionThread(Lock l1, CountDownLatch countDownLatch, int sleep_min, int
            sleep_max, int activity_min, int activity_max) {
        this.countDownLatch=countDownLatch;
        this.l1 = l1;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min1 = activity_min;
        this.activity_max1 = activity_max;
    }
    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        l1.lock();
            System.out.println(this.getName() + " - STATE 2");
            int k = (int) Math.round(Math.random() * (activity_max1
                    - activity_min1) + activity_min1);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }


        try {
            Thread.sleep(sleep_min * 500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        l1.unlock();

        System.out.println(this.getName() + " - STATE 3");

        countDownLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
