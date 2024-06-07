package App4;

public class ExecutionThread2 extends Thread {
    private final Object monitor;
    private final int minActivity;
    private final int maxActivity;
    private final ExecutionThread1 thread;

    public ExecutionThread2(Object monitor, int minActivity, int maxActivity, ExecutionThread1 thread) {
        this.monitor = monitor;
        this.minActivity = minActivity;
        this.maxActivity = maxActivity;
        this.thread = thread;
    }

    @Override
    public void run() {
        // State 1
        System.out.println(this.getName() + " - STATE 1");
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // State 2
        System.out.println(this.getName() + " - STATE 2");
        int k = (int) Math.round(Math.random() * (maxActivity - minActivity) + minActivity);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        // State 3
        System.out.println(this.getName() + " - STATE 3");
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
