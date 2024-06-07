package App3;

import java.util.concurrent.CountDownLatch;

public class Fir_App3_1 extends Thread {
    private final Object monitor1;
    private final Object monitor2;
    private final CountDownLatch latch;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;
    private final String name;

    public Fir_App3_1(Object monitor1, Object monitor2, CountDownLatch latch, int minActivity, int maxActivity, int transitionTime, String name) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.latch = latch;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
        this.name = name;
    }

    @Override
    public void run() {
        // State 1
        System.out.println(name + " started execution.");
        try {
            Thread.sleep(transitionTime * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // State 2
        System.out.println(name + ": Executing activity.");
        int k = (int) Math.round(Math.random() * (maxActivity - minActivity) + minActivity);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        System.out.println(name + ": Activity completed.");
        synchronized (monitor1) {
            synchronized (monitor2) {
                monitor1.notify();
                monitor2.notify();
            }
        }
        // State 3
        latch.countDown();
        System.out.println(name + ": Waiting for all threads to finish execution.");
        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(name + ": End of execution.");
    }
}
