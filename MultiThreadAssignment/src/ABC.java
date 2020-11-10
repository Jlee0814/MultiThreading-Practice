public class ABC {
    private static int flag = 1;
    private static Object obj=new Object();
    public static void main(String[] args) {
        for(int i=0;i<=10;i++){
            new Thread(new printA()).start();
            new Thread(new printB()).start();
            new Thread(new printC()).start();
        }

    }
    public static class printA implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                while(flag!=1){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A");
                flag=2;
                obj.notifyAll();
            }
        }
    }
    public static class printB implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                while(flag!=2){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B");
                flag=3;
                obj.notifyAll();
            }
        }
    }
    public static class printC implements Runnable{

        @Override
        public void run() {
            synchronized (obj){
                while(flag!=3){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("C");
                flag=1;
                obj.notifyAll();
            }
        }
    }
}
