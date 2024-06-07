package App4;

public class ExecutionThread1 extends Thread {
    private final Object monitor1;
    private final Object monitor2;
    private final int minActivity;
    private final int maxActivity;
    private final int transitionTime;

    public ExecutionThread1(Object monitor1, Object monitor2, int minActivity, int maxActivity, int transitionTime) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.transitionTime = transitionTime;
    }

    @Override
    public void run() {
        // State 1
        System.out.println(this.getName() + " - STATE 1");
        try {
            Thread.sleep(transitionTime * 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // State 2
        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (maxActivity - minActivity) + minActivity);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        synchronized (monitor1) {
            synchronized (monitor2) {
                monitor1.notify();
                monitor2.notify();
            }
        }
        // State 3
        System.out.println(this.getName() + " - STATE 3");
    }
}
