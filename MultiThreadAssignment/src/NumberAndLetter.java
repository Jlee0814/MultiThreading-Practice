public class NumberAndLetter {
    public static void main(String[] args) {
        Object o = new Object();
        number number = new number(o);
        letter letter = new letter(o);
        new Thread(number).start();
        new Thread(letter).start();

    }
    public static class number implements Runnable{
        private Object obj;
        public number(Object obj){
            this.obj=obj;
        }
        @Override
        public void run() {
            synchronized(obj){
                for(int i=1;i<53;i++){
                    System.out.print(i);
                    if(i%2==0){// do not use while , i is not the global condition
                        obj.notifyAll();
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    public static class letter implements Runnable{
        private Object obj;
        public letter(Object obj){
            this.obj=obj;
        }
        @Override
        public void run() {
            synchronized(obj){
              for(char i='A';i<='Z';i++){
                  System.out.println(i);
                  obj.notifyAll();
                  if(i<'Z') {// use to terminate
                      try {
                          obj.wait();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }
            }
        }
    }
}
