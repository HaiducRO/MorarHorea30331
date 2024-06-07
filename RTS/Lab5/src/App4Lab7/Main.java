package App4Lab7;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(2);
        Fir f1, f2, f3;
        f1 = new Fir(1, s, 5, (int) Math.round(Math.random() * 3 + 6), 2);
        f2 = new Fir(2, s, 3, (int) Math.round(Math.random() * 4 + 7), 2);
        f3 = new Fir(3, s, 6, (int) Math.round(Math.random() * 5 + 7), 2);
        f1.start();
        f2.start();
        f3.start();


    }
}
