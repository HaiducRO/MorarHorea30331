package Lab6.App1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
public class ExecutionThread extends Thread {
    Lock l1;
    Lock l2;
    Semaphore s9,s10;
    int permit9,permit10;

    CyclicBarrier bariera;
    int sleep_min, sleep_max, activity_min1, activity_max1,activity_min2, activity_max2;
    public ExecutionThread(Semaphore s9,Semaphore s10,CyclicBarrier bariera, int sleep_min, int
            sleep_max, int activity_min, int activity_max, int activity_min2, int activity_max2,int permit9,int permit10) {
        this.s9=s9;
        this.s10=s10;
        this.permit9=permit9;
        this.permit10=permit10;
        this.bariera=bariera;
        this.l1 = l1;
        this.l2=l2;
        this.sleep_min = sleep_min;
        this.sleep_max = sleep_max;
        this.activity_min1 = activity_min;
        this.activity_max1 = activity_max;
        this.activity_min2 = activity_min2;
        this.activity_max2 = activity_max2;

    }
    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        int k = (int) Math.round(Math.random() * (activity_max1
                - activity_min1) + activity_min1);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        try {
            this.s9.acquire(this.permit9);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println(this.getName() + " - STATE 2");

        k = (int) Math.round(Math.random() * (activity_max2 - activity_min2) + activity_min2);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }

        if(this.s10.tryAcquire(this.permit10)) {

            System.out.println(this.getName() + " - STATE 3");
            try {
                Thread.sleep(sleep_min * 500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                s10.release(1);
            }

        }
            this.s9.release(1);


        System.out.println(this.getName() + " - STATE 4");
        try {
            this.bariera.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
