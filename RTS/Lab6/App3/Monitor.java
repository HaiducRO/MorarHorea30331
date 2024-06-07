package Lab6.App3;

public class Monitor {
    private Integer monitor=new Integer(0);

    synchronized void start(){
        this.monitor=new Integer(1);
        notify();
    }
    synchronized void read(){
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
