package Lab6.App3;

import java.util.concurrent.CountDownLatch;

public class Thread2 extends Thread{
    int sleepTh1, activity_minTh1, activity_maxTh1;
    CountDownLatch countDownLatch,p6;
    Thread th1;
    public Thread2(CountDownLatch p6,  CountDownLatch countDownLatch, int sleepTh1, int activity_minTh1, int activity_maxTh1, Thread1 th1) {
        this.p6=p6;
        this.countDownLatch=countDownLatch;
        this.sleepTh1 = sleepTh1;
        this.activity_minTh1 = activity_minTh1;
        this.activity_maxTh1 = activity_maxTh1;
        this.th1=th1;
    }
    public void run() {

            System.out.println(this.getName() + " - STATE 1");
        try {
            this.p6.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this.getName() + " - STATE 2");
            int k = (int) Math.round(Math.random() * (activity_maxTh1
                    - activity_minTh1) + activity_minTh1);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            System.out.println(this.getName() + " - STATE 3");
            if (this.th1!=null) {
                try {
                    th1.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        this.countDownLatch.countDown();
        System.out.println("CountDown: "+this.getName());
        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        }

}