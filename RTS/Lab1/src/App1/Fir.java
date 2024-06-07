package App1;

import java.util.ArrayList;
import java.util.List;


public class Fir extends Thread implements Observable {
    int id;// Thread ID
    int processorLoad;// Amount of processing load
    List<Observer> observers = new ArrayList<>();// List of observers

    // Constructor to initialize Fir object
    Fir(int id, int priority, int procLoad){
        this.id=id;
        this.processorLoad=procLoad;
        this.setPriority(priority);// Setting thread priority
    }
    // Run method, executed when the thread starts
    public void run(){
        int c=0;
        while(c<1000)
        {
            // Simulating processing load
            for(int j=0;j<this.processorLoad;j++){
                j++;j--;
            }
            c++;

            // Notifying observers about the progress of the thread
            notifyObservers(id,c);
            try{
                Thread.sleep(10); // Introducing a small delay to see better
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }


    // Methods from the Observable interface
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int id, int value) {
        for (Observer observer: observers)
        {
            observer.update(id,value);
        }
    }
}