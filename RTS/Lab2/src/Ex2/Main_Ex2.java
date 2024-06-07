package Ex2;

public class Main_Ex2 {
    public static void main(String[] args){

        Buffer b = new Buffer();

        Producer pro = new Producer(b);

        Consumer c = new Consumer(b);

        Consumer c2 = new Consumer(b);

        pro.start();

        c.start(); c2.start();

    }
}
