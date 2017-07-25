package assignment6;

/**
 * Office Class
 * Created by liuxx on 2017/7/24.
 */
public class Office implements Runnable {
    private int clients;
    private String name;
    private static Theater show;

    public Office(int clients, String name){
        this.clients = clients;
        this.name = name;
        show = null;
    }

    public int getClients(){
        return this.clients;
    }

    public String getName(){
        return this.name;
    }

    public static void setTheater(Theater theater){
        show = theater;
    }

    public static Theater getTheater(){
        return show;
    }

    public void run(){

    }
}
