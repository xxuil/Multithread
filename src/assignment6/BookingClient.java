/* MULTITHREADING <BookingClient.java>
 * EE422C Project 6 submission by
 * Xiangxing Liu
 * xl5587
 * 76175
 * Slip days used: <0>
 * Summer 2017
 */

package assignment6;


import java.util.*;
import java.lang.Thread;

public class BookingClient {
    ArrayList<Office> officeList;
    ArrayList<String> officeNameList;
    public static boolean DEBUG = false;

    /*
     * @param office maps box office id to number of customers in line
     * @param theater the theater where the show is playing
     */
    public BookingClient(Map<String, Integer> office, Theater theater) {
        // TODO: Implement this constructor
        officeList = new ArrayList<>();
        officeNameList = new ArrayList<>(office.keySet());

        for(String name : officeNameList){
            officeList.add(new Office(office.get(name), name));
        }

        Office.setDEBUG(DEBUG);
        Office.buildLine();
        Office.setTheater(theater);
    }

    /*
     * Starts the box office simulation by creating (and starting) threads
     * for each box office to sell tickets for the given theater
     *
     * @return list of threads used in the simulation,
     *         should have as many threads as there are box offices
     */
    public List<Thread> simulate() {
        //TODO: Implement this method
        ArrayList<Thread> threadList = new ArrayList<>();

        for(Runnable office : officeList){
            Thread thread = new Thread(office);
            threadList.add(thread);
        }

        for(Thread thread : threadList){
            thread.start();

            try {
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return threadList;
    }

    private static void joinAllThreads(List<Thread> threads) throws InterruptedException {
        for (Thread t: threads) {
            t.join();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Theater show = new Theater(3, 2, "Tamako Market");
        Map<String, Integer> officeMap = new HashMap<String, Integer>();

        officeMap.put("BX1", 2);
        officeMap.put("BX2", 2);
        officeMap.put("BX3", 2);

        BookingClient mainShow = new BookingClient(officeMap, show);

        joinAllThreads(mainShow.simulate());
    }
}
