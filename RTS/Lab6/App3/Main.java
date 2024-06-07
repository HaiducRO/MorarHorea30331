package Lab6.App3;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        CountDownLatch p10=new CountDownLatch(1);
        CountDownLatch p6 =new CountDownLatch(1);

        CountDownLatch countDownLatch=new CountDownLatch(4);


        Thread1 th1=new Thread1(p10,p6,countDownLatch, 7, 2, 3);
        Thread3 th3=new Thread3(p10,countDownLatch, 7, 4, 6,th1);
        Thread2 th2=new Thread2(p6,countDownLatch, 7, 3, 5,th1);

        th1.start();
        th2.start();
        th3.start();

        countDownLatch.countDown();
        System.out.println("CountDown: Main");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }
}
