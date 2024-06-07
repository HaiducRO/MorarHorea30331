package Ex2;

class Consumer extends Thread{

    private Buffer bf;

    Consumer(Buffer bf){this.bf=bf;}

    public void run() {

        while (true){

            System.out.println("Consumer "+this.getName()+

                    " received >> "+bf.get());

        }

    }

}
