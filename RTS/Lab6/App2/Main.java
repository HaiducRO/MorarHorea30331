package Lab6.App2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {
    public static void main(String[] args) {
        Lock l1 = new ReentrantLock();
        Lock l2 = new ReentrantLock();
        CountDownLatch countDownLatch;
        ExecutionThread e1,e3;
        ExecutionThread2 e2;

        while (true) {
            countDownLatch= new CountDownLatch(4);
            System.out.println("Thread-Main-STATE 1");
            e1 = new ExecutionThread(l1,countDownLatch, 4, 4,  4, 6);
            e2 = new ExecutionThread2(l1, l2,countDownLatch,5, 5, 3, 5);
            e3 = new ExecutionThread(l2,countDownLatch,5, 5, 3, 5);

            e1.start();
            e2.start();
            e3.start();
            countDownLatch.countDown();
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
