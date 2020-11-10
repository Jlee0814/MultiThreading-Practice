public class CarParking{
// A typical consumer and producer problem when consumer is full we wait, when producer is empty we wait
    private static int parkingLot = 3;
    private static boolean isParking=false;
    private static Object obj = new Object();
    public static void main(String[] args)  {
        new Thread(new park(),"Car1").start();
        new Thread(new park(),"Car2").start();
        new Thread(new park(),"Car3").start();
        new Thread(new park(),"Car4").start();
        new Thread(new park(),"Car5").start();

        new Thread(new unpark()).start();
        new Thread(new unpark()).start();
        new Thread(new unpark()).start();
        new Thread(new unpark()).start();
        new Thread(new unpark()).start();

    }
    public static class park implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                while(parkingLot==0){ //can not consume
                    try {
                        System.out.println("The parking lot is full");
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                parkingLot--; // consume
                System.out.println(Thread.currentThread().getName()+" has parked succuessfully ,we have "+parkingLot+" parking space remain");
                obj.notifyAll();
            }
        }
    }
    public static class unpark implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                while(parkingLot==3){//stop produce, enough spaces,Or if the car is not in the parking lot ,then wait
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                parkingLot++;// produce
                System.out.println("There's a car driving out ,we have "+parkingLot+" parking space remain");
                obj.notifyAll();
            }
        }
    }
}