package multithread;

/**
 * multithread test
 * Created by liuxx on 2017/7/24.
 */
public class test implements Runnable{
    public String name;

    public test(String name){
        this.name = name;
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + " starts");

        for(int i = 0; i < 5; i++){
            System.out.println(name + " running " + i);

            try{
                Thread.sleep((int) Math.random() * 10);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

        }

        System.out.println(Thread.currentThread().getName() + " ends");

    }



    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName() + " starts");

        Thread t1 = new Thread(new test("C"));
        Thread t2 = new Thread(new test("D"));

        t1.start();
        t2.start();


        try{
            t1.join();

        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            t2.join();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " ends\n\n\n\n\n\n\n\n");
    }


}