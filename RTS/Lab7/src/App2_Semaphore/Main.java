package App2_Semaphore;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);
        CyclicBarrier barrier = new CyclicBarrier(4);

        while (true) {
            // State 0
            System.out.println("Main: Initial state.");
            new Fir_App2_1_Semaphore(semaphore1, barrier, 2, 4, 4, "Thread 1").start();
            new Fir_App2_2_Semaphore(semaphore1, semaphore2, barrier, 3, 6, 3, "Thread 2").start();
            new Fir_App2_1_Semaphore(semaphore2, barrier, 2, 5, 5, "Thread 3").start();
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            barrier.reset();
        }
    }
}

