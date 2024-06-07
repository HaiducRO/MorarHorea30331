package App4Lab7;

import java.util.concurrent.Semaphore;

public class Fir extends Thread {
    int name, delay, k, permit;
    Semaphore s;

    Fir(int n, Semaphore s, int delay, int k, int permit) {
        this.name = n;
        this.s = s;
        this.delay = delay;
        this.k = k;
        this.permit = permit;
    }

    public void run() {
        while (true) {

            System.out.println("Fir " + name + " State 1");
            try {
                this.s.acquire(this.permit); // critical region
                System.out.println("Fir " + name + " took a token from the semaphore");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fir " + name + " State 2");

            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            System.out.println("Fir " + name + " released a token from the semaphore");
            this.s.release(this.permit); // end of critical region
            System.out.println("Fir " + name + " State 3");
            try {
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fir " + name + " State 4");
        }
    }
}
