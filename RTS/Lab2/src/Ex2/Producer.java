package Ex2;

class Producer implements Runnable{

    private Buffer bf;

    private Thread thread;

    Producer(Buffer bf){this.bf=bf;}

    public void start(){

        if (thread==null){

            thread = new Thread(this);

            thread.start();

        }

    }

    public void run(){

        while (true){

            bf.put(Math.random());

            System.out.println("Producer "+thread.getName()+

                    " gave a task.");

            try{

                Thread.sleep(1000);

            }catch(Exception e){e.printStackTrace();}

        }

    }

}
