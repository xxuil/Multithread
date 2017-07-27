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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Theater {
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;

		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
		}

		public int getSeatNum() {
			return seatNum;
		}

		public int getRowNum() {
			return rowNum;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return the full Seat location ex: A1
			ArrayList<Character> seat = new ArrayList<Character>();
			int temp = rowNum;

			while (temp > 0) {
				if (temp < 26) // just one Character
				{
					char tempChar = (char) (temp + 64);
					seat.add(tempChar); //add the letter
					temp = 0;
				} else { // larger than one Character
					int quotient = temp / 26; // the part needs to be determined again
					int rem = temp % 26; //last letter

					if (rem == 0) {
						temp = quotient - 1;
						seat.add('Z'); //last letter is 'Z'
					} else {
						temp = quotient;
						char tempChar = (char) (rem + 64);
						seat.add(tempChar);
					}

				}
			}

			Collections.reverse(seat);
			String result = seat.stream().map(Object::toString).collect(Collectors.joining("")); //convert to string
			return result + seatNum;
		}
	}

  /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
	  private int client;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() {
			return seat;
		}

		public String getShow() {
			return show;
		}

		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		public int getClient() {
			return client;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return a string that resembles a ticket
			String result = "-------------------------------\n"; //upper boundary
			
			String tempString = "| Show: " + show; //the line for show
			int tempLength = tempString.length();
			result = result + tempString; 
			for(int i = 0; i< (30-tempLength); i++)
			{
				result = result + " "; //add correct number of space
			}
			result = result + "|\n"; //right boundary
			
			
			tempString = "| Box Office ID: " + boxOfficeId; //the line for Box Office
			tempLength = tempString.length();
			result = result + tempString; 
			for(int i = 0; i< (30-tempLength); i++)
			{
				result = result + " "; //add correct number of space
			}
			result = result + "|\n"; //right boundary
			
			
			tempString = "| Seat: " + seat; //the line for seat
			tempLength = tempString.length();
			result = result + tempString; 
			for(int i = 0; i< (30-tempLength); i++)
			{
				result = result + " "; //add correct number of space
			}
			result = result + "|\n"; //right boundary
			
			
			tempString = "| Client: " + client; //the line for Box Office
			tempLength = tempString.length();
			result = result + tempString; 
			for(int i = 0; i< (30-tempLength); i++)
			{
				result = result + " "; //add correct number of space
			}
			result = result + "|\n"; //right boundary
			
			result = result +  "-------------------------------\n"; //bottom boundary
			
			
			return result;
		}
	}

	int numRows;
	int seatsPerRow;
	String show;
	Seat selectSeat;
	List<Ticket> transactionLog = new ArrayList<Ticket>(); // save all the information for ticket
	
	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor	
		
		this.numRows = numRows;
		this.show = show;
		this.seatsPerRow = seatsPerRow;
		//selectSeat = new Seat(1, 1);
		transactionLog.clear(); //clear the log for initialization 
	}

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public synchronized Seat bestAvailableSeat() {
		//TODO: Implement this method
		Seat result;
		if(transactionLog.size() == numRows * seatsPerRow) // if no more seat is available
			return null;
		else if (transactionLog.size() == 0)
		{
			result = new Seat(1,1);
			//selectSeat.rowNum = 1;
			//selectSeat.seatNum = 1;
		}
		else{			
			Seat lastSeat = transactionLog.get(transactionLog.size()-1).seat; // get the seat for the last sell the ticked 
			int tempRowNumber = lastSeat.rowNum;
			int tempSeatsNumber = lastSeat.seatNum;
			
			if(tempSeatsNumber < seatsPerRow) // still has seat in this row
			{
				tempSeatsNumber = tempSeatsNumber + 1; // get the next seat in this row
				result = new Seat(tempRowNumber,tempSeatsNumber);
				//selectSeat.seatNum = tempSeatsNumber;
				//selectSeat.rowNum = tempRowNumber;
			}
			
			else{ // no seat in this row
				tempRowNumber = tempRowNumber + 1; // get the first seat in next row
				result = new Seat(tempRowNumber, 1);	
				
				//selectSeat.seatNum = tempSeatsNumber;
				//selectSeat.rowNum = tempRowNumber;
			}
			
		}	
		return result;
		
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public synchronized Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		//TODO: Implement this method
		/*try {
			Thread.sleep(50); //print each ticket sold to the console with a small delay for human readability (50 ms)
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		Ticket result = new Ticket(show, boxOfficeId, seat, client);
		transactionLog.add(result); // add ticket to the log
		System.out.println(result);
		return result;
		
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog() {
		//TODO: Implement this method
		return transactionLog;
	}
}
