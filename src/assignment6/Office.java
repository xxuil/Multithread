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
    private static int totalClients = 0;
    private static Deque<Integer> line = new ArrayDeque<>();

    public Office(int clients, String name){
        this.clients = clients;
        this.name = name;
        show = null;
        totalNum += 1;
        lock = new ReentrantLock();
        officeCondition = lock.newCondition();
        totalClients += clients;

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
        for(int i = 1; i <= totalClients; i++){
            line.push(i);
        }
    }

    private void processTicket(){
        try{
            lock.lock();
            Seat next = show.bestAvailableSeat();

            if(next == null)
                return;

            //Client Number is temporary
            show.printTicket(name, next, line.pop());

            try {
                Thread.currentThread().sleep(100);
            }
            catch(Exception e){
                e.printStackTrace();
            }

            if(DEBUG){
                totalTicket ++;
                System.out.println(totalTicket);
                System.out.println(name + " " + clients);
            }
        }
        finally{
            lock.unlock();
        }
    }

    private static boolean soldOutMessagePrinted = false;

    private void printSoldOut(){
        try {
            lock.lock();
            if (!soldOutMessagePrinted) {
                soldOutMessagePrinted = true;
                System.out.println("Sorry, we are sold out!");
            }
        }
        finally {
            lock.unlock();
        }

    }

    public static void reset(){
        soldOutMessagePrinted = true;
        show = null;
        totalNum = 0;
        totalTicket = 0;
        totalClients = 0;
        line = new ArrayDeque<>();
    }

    public void run(){
        while(true){
            if(!show.getStatus()){
                if(clients > 0){
                    processTicket();
                    clients -= 1;
                }
                else break;
            }
            else{
                if(DEBUG){
                    System.out.println(name + "Debug: sold");
                }
                printSoldOut();
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }
}
