package App1;

public class Main {
    // Constants for the number of threads and processor load.
    private static final int noOfThreads = 6;
    private static final int processorLoad = 1000000;

    public static void main(String[] args)
    {
        // Creating a Window object for display.
        Window win = new Window(noOfThreads);

        // Creating and starting multiple Fir threads
        for (int i = 0; i<noOfThreads; i++)
        {
            Fir fir = new Fir(i,i+2,processorLoad);
            // Adding the window as an observer to each Fir thread
            fir.addObserver(win);
            // Start thread
            fir.start();
        }

    }
}