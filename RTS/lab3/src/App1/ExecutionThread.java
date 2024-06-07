package App1;

public class  ExecutionThread extends Thread {
    Integer monitor1;
    Integer monitor2;
    int activity_min, activity_max, sleep;

    public ExecutionThread(Integer monitor1, Integer monitor2, int activity_min, int activity_max, int sleep) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.sleep = sleep;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        synchronized (monitor1) {
            if (monitor2 != null) {
                synchronized (monitor2) {
                    System.out.println(this.getName() + " - STATE 2");
                    int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                    for (int i = 0; i < k * 100000; i++) {
                        i++;
                        i--;
                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println(this.getName() + " - STATE 2");
                int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
                for (int i = 0; i < k * 100000; i++) {
                    i++;
                    i--;
                }
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(this.getName() + " - STATE 3");
    }
}
