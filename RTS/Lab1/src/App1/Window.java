package App1;

import javax.swing.*;
import java.util.ArrayList;

public class Window extends JFrame implements Observer {
    ArrayList<JProgressBar> bars=new ArrayList<JProgressBar>();

    // Constructor to initialize the window
    public Window(int nrThreads) {
        setLayout(null);
        setSize(450,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init(nrThreads);
        this.setVisible(true);
    }

    // Method to initialize progress bars
    private void init(int n){
        for(int i=0 ;i<n; i++){
            JProgressBar pb=new JProgressBar();// Creating a progress bar
            pb.setMaximum(1000);// max value of progress bar
            pb.setBounds(50,(i+1)*30,350,20);// Setting position and sizes for the bar
            this.add(pb);// Add progress bar to the window
            this.bars.add(pb);// Adding progress bar to the list so they are a list one onder each other
        }
    }

    // Method to set progress value of a progress bar
    public void setProgressValue(int id,int val)
    {
        bars.get(id).setValue(val);
    }


    // Observer method called when a Fir thread notifies the window about progress
    public void update(int id, int value)
    {
        // Updating the progress bar using SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(() -> {
            setProgressValue(id, value);
        });
    }
}