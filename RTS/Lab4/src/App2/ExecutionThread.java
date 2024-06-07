package App2;

public class ExecutionThread extends Thread {
    Integer monitor1;
    Integer monitor2;
    int sleep, activity_min, activity_max, activity_min1, activity_max1;

    public ExecutionThread(Integer monitor1, Integer monitor2, int sleep, int activity_min, int activity_max, int activity_min1, int activity_max1) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.sleep = sleep;
        this.activity_min = activity_min;
        this.activity_min1 = activity_min;
        this.activity_max = activity_max;
        this.activity_max1 = activity_max;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        synchronized (monitor1) {
            synchronized (monitor2) {
                System.out.println(this.getName() + " - STATE 2");

                int j = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < j * 100000; i++) {
                    i++;
                    i--;
                }

                System.out.println(this.getName() + " - STATE 3");
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(this.getName() + " - STATE 4");
    }
}
