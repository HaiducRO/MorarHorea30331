package App4;

public class Main {
    public static void main(String[] args) {
        Object monitor1 = new Object();
        Object monitor2 = new Object();

        ExecutionThread1 thread1 = new ExecutionThread1(monitor1, monitor2, 2, 3, 7);
        ExecutionThread2 thread2 = new ExecutionThread2(monitor1, 3, 5, thread1);
        ExecutionThread2 thread3 = new ExecutionThread2(monitor2, 4, 6, thread1);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
