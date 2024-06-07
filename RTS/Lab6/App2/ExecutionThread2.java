package Lab6.App2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class ExecutionThread2 extends  Thread {
    Lock l1;
    Lock l2;

    CountDownLatch countDownLatch;
    int sleep_min, sleep_max, activity_min1, activity_max1;

    public ExecutionThread2(Lock l1, Lock l2, CountDownLatch countDownLatch, int sleep_min, int
            sleep_max, int activity_min, int activity_max) {
        this.countDownLatch = countDownLatch;
        this.l1 = l1;
        this.l2 = l2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min1 = activity_min;
        this.activity_max1 = activity_max;

    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");

        l1.lock();
        l2.lock();

        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (activity_max1 - activity_min1) + activity_min1);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        try {
            Thread.sleep(sleep_min * 500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        l2.unlock();
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

