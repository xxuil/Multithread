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
public class Office extends Thread {
    private int clients;
    private String name;
    private static Theater show;
    private static int totalNum = 0;
    private Lock lock;
    private Condition officeCondition;

    private static boolean DEBUG;
    private static Integer totalTicket = 0;
    private static Integer totalClients = 0;

    public Office(int clients, String name){
        this.clients = clients;
        this.name = name;
        show = null;
        totalNum += 1;
        totalClients += clients;

        if(DEBUG){
            System.out.println(name + " " + totalNum);
        }
    }

    public static void setTheater(Theater theater){
        show = theater;
    }

    public static void setDEBUG(boolean debug){ DEBUG = debug;}

    private static boolean soldOutMessagePrinted = false;

    public static void reset(){
        soldOutMessagePrinted = false;
        show = null;
        totalNum = 0;
        totalTicket = 0;
        totalClients = 0;
    }

    public void run(){
        while(clients > 0) {
            synchronized(show){
                Theater.Seat next = show.bestAvailableSeat();

                if(next != null){
                    show.printTicket(name, next, totalClients);
                    clients --;
                    totalClients --;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(next == null){
                    if (!soldOutMessagePrinted) {
                        soldOutMessagePrinted = true;
                        System.out.println("Sorry, we are sold out!");
                    }
                }
            }
        }
        if(DEBUG)
            System.out.println(Thread.currentThread().getName() + " end");
    }
}
