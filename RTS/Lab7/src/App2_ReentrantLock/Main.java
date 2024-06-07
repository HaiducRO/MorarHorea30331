package App2_ReentrantLock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        CyclicBarrier barrier = new CyclicBarrier(4);

        while (true) {
            // State 0
            System.out.println("Main: Initial state.");
            new Fir_App2_1_ReentrantLock(lock1, barrier, 2, 4, 4, "Thread 1").start();
            new Fir_App2_2_ReentrantLock(lock1, lock2, barrier, 3, 6, 3, "Thread 2").start();
            new Fir_App2_1_ReentrantLock(lock2, barrier, 2, 5, 5, "Thread 3").start();
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            barrier.reset();
        }
    }
}
