package App2;

public class Main {
    public static void main(String[] args) {
        Integer monitor1 = 1;
        Integer monitor2 = 1;
        new ExecutionThread(monitor1, monitor2, 4, 4, 6, 2, 4).start();
        new ExecutionThread(monitor2, monitor1, 5, 5, 7, 3, 5).start();
    }
}
