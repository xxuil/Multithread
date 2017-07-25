/* MULTITHREADING <BookingClient.java>
 * EE422C Project 6 submission by
 * Xiangxing Liu
 * xl5587
 * 76175
 * Slip days used: <0>
 * Summer 2017
 */

package assignment6;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.Thread;

public class BookingClient {
    ArrayList<Office> officeList;

  /*
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */
  public BookingClient(Map<String, Integer> office, Theater theater) {
    // TODO: Implement this constructor
      officeList = new ArrayList<>();
      ArrayList<String> officeNameList = new ArrayList<>(office.keySet());

      for(String name : officeNameList){
          officeList.add(new Office(office.get(name), name));
      }
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


        return threadList;
	}

	public static void main(String[] args){
	    Theater show = new Theater(100, 100, "Tamako");
	    System.out.println("Sorry, we are sold out!");
    }
}
