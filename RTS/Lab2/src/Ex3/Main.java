package Ex3;

class JoinTestThread extends Thread{

    Thread t;
    String n;
    int number;

    JoinTestThread(String n, Thread t, int number){

        this.n = n;
        this.t = t;
        this.number = number;

    }

    public void run() {
        System.out.println("Thread " + n + " has entered the run() method");
        try {
            if (t != null) {
                t.join();
            }
            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    Main.sum += i;
                }
            }
            System.out.println("Sum = " + Main.sum + " " + this.n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



public class Main {
    static int sum=0;
    public static void main(String[] args){


        JoinTestThread w1 = new JoinTestThread("Thread 1",null,50_000);

        JoinTestThread w2 = new JoinTestThread("Thread 2",w1, 20_000);

       w1.start();

        w2.start();

    }

}