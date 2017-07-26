package assignment6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Office Class
 * Created by liuxx on 2017/7/24.
 */
public class Office implements Runnable {
    private int clients;
    private String name;
    private static Theater show;
    private static int totalNum = 0;
    private Lock lock;
    private Condition officeCondition;

    private static boolean DEBUG;
    private static int totalTicket = 0;
    private static int totalClints = 0;
    private static Deque<Integer> line = new ArrayDeque<>();

    public Office(int clients, String name){
        this.clients = clients;
        this.name = name;
        show = null;
        totalNum += 1;
        lock = new ReentrantLock();
        officeCondition = lock.newCondition();
        totalClints += clients;

        if(DEBUG){
            System.out.println(name + " " + totalNum);
        }
    }

    public int getClients(){
        return this.clients;
    }

    public String getName(){
        return this.name;
    }

    public int getTotalNum(){return totalNum;}

    public static void setTheater(Theater theater){
        show = theater;
    }

    public static Theater getTheater(){
        return show;
    }

    public static void setDEBUG(boolean debug){ DEBUG = debug;}

    public static void buildLine(){
        for(int i = 1; i <= totalClints; i++){
            line.push(i);
        }
    }

    private void processTicket(Seat next){
        try{
            lock.lock();

            //Client Number is temporary
            show.printTicket(name, next, line.pop());

            if(DEBUG){
                totalTicket ++;
                System.out.println(totalTicket);
            }
        }
        finally{
            lock.unlock();
        }
    }

    public void run(){
        while(true){
            if(!show.getStatus()){
                Seat next = show.bestAvailableSeat();

                if(next == null)
                    break;

                processTicket(next);
            }
        }
    }
}
