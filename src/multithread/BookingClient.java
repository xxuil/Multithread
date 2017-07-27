/* MULTITHREADING <MyClass.java>
 * EE422C Project 6 submission by
 * Replace <...> with your actual data.
 * Yue Shen
 * YS7764
 * 13269
 * Slip days used: <1>
 * Spring 2017
 */

package multithread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.lang.Thread;

public class BookingClient {
	
	
	public static void main(String[] arg) { //main
		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("Boxoffice1", 110);
		m.put("BX2", 110);
	//	m.put("BX2", 20);
	//	m.put("BX5", 20);
	//	m.put("BX4", 20);
		Theater theater = new Theater(5, 50, "Hamlet");
		BookingClient client = new BookingClient(m, theater);
		client.simulate();
	}
	
	
	Map<String, Integer> office;
	Theater theater;
	Object key;
  /*
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */
  public BookingClient(Map<String, Integer> office, Theater theater) {
    // TODO: Implement this constructor
	  this.office = office;
	  this.theater = theater;
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
		List <Thread> result = new ArrayList<Thread>();
		
		//get all box offices from the map
		List<String> keyList = new ArrayList<String>(office.keySet());
		
		for (int i = 0; i<keyList.size(); i++) // go through every box office
		{
			String tempBoxOffice = keyList.get(i);
			Thread officeThread = new Thread(){
				int tempCustomer = office.get(tempBoxOffice); // get the customer number in this line
				
				@Override
				public void run(){
					
					while(tempCustomer > 0){
						/*
						for (int j = 0; j< result.size(); j++)
						{
							if (Thread.currentThread() == result.get(j))
								try {
									//Random rand = new Random();

									//int  n = rand.nextInt(350) + 200;
									Thread.sleep(131+j*21);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						}
						*/
						
						synchronized (theater){
						
						
						Theater.Seat selectSeat = theater.bestAvailableSeat();
						
						if (selectSeat != null)
						theater.printTicket(tempBoxOffice, selectSeat, theater.transactionLog.size()+1);
						tempCustomer = tempCustomer - 1;
						
						try {
							Thread.sleep(50); //print each ticket sold to the console with a small delay for human readability (50 ms)
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					}
				}
			};
			result.add(officeThread);
		}
		
		
		for(int i = 0 ; i < result.size(); i++)
		{
			result.get(i).start();
		}
		
		
		
			try {
				for(int i = 0; i< result.size(); i++) 
				result.get(i).join(); //wait thread to end
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(theater.transactionLog.size() == theater.numRows * theater.seatsPerRow)
		{
			System.out.println("Sorry, we are sold out!");
		}
		
		return result;
		
	}
}
