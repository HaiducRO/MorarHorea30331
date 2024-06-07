package App3;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        Object monitor1 = new Object();
        Object monitor2 = new Object();
        CountDownLatch latch = new CountDownLatch(3);
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;

        System.out.println("Type the value for x:");
        x = scanner.nextInt();
        System.out.println("Type the value for y:");
        y = scanner.nextInt();

        Fir_App3_1 thread1 = new Fir_App3_1(monitor1, monitor2, latch, 2, 3, 7, "Thread 1");
        Fir_App3_2 thread2 = new Fir_App3_2(monitor1, latch, 3, 5, x, "Thread 2");
        Fir_App3_2 thread3 = new Fir_App3_2(monitor2, latch, 4, 6, y, "Thread 3");
        // State 0
        System.out.println("Beginning of the program.");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
