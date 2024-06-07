package Lab6.App1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {
    public static void main(String[] args) {

        Semaphore s9 = new Semaphore(1);
        Semaphore s10 = new Semaphore(1);
        CyclicBarrier bariera= new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("Barier Routine");
            }
        });
        ExecutionThread e1,e2;

        while (true) {
            System.out.println("Thread-Main-STATE 1");
            e1 = new ExecutionThread(s9, s10,bariera, 4, 4, 2, 4, 4, 6,1,1);
            e2 = new ExecutionThread(s10, s9,bariera,5, 5, 3, 5, 5, 7,1,1);
            e1.start();
            e2.start();
            try {
                bariera.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
