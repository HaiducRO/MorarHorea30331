package Lab6.App3;

import java.util.concurrent.CountDownLatch;

public class Thread1 extends Thread{
    Thread th2,th3;
    CountDownLatch countDownLatch,p10,p6;
    int sleepTh1, activity_minTh1, activity_maxTh1;

    public Thread1(CountDownLatch p10,CountDownLatch p6,CountDownLatch countDownLatch, int sleepTh1, int activity_minTh1, int activity_maxTh1) {
        this.p10=p10;
        this.p6=p6;
        this.countDownLatch=countDownLatch;
        this.sleepTh1 = sleepTh1;
        this.activity_minTh1 = activity_minTh1;
        this.activity_maxTh1 = activity_maxTh1;

    }

    public void run() {

            System.out.println(this.getName() + " - STATE 1");
            try {
                Thread.sleep(sleepTh1 * 200);
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
            this.p6.countDown();
            this.p10.countDown();

            System.out.println(this.getName() + " - STATE 3");
        this.countDownLatch.countDown();
        System.out.println("CountDown: "+this.getName());
        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


