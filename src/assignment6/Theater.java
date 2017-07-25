/* MULTITHREADING <Theater.java>
 * EE422C Project 6 submission by
 * Xiangxing Liu
 * xl5587
 * 76175
 * Slip days used: <0>
 * Summer 2017
 */

package assignment6;

import java.util.*;

public class Theater {

    private ArrayList<Seat> seatList;
    private ArrayList<Ticket> soldList;
    private String show;


	public Theater(int numRows, int seatsPerRow, String show) {
        this.seatList = new ArrayList<>();
        this.show = show;
        soldList = new ArrayList<>();
        buildSeats(numRows, seatsPerRow);
	}

	private void buildSeats(int row, int col){
        for(int i = 0; i < row; i++){
            for(int j= 0; j < col; j++){
                this.seatList.add(new Seat(i, j));
            }
        }
    }

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public Seat bestAvailableSeat() {
	    //Find the first available seat on the list and return
	    for(Seat seat : seatList){
	        if(!seat.getStatus())
	            return seat;
        }
        //return null if there is no more available seat
        return null;
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
     * Also prints the ticket to the console
	 *
     * @param seat a particular seat in the theater
     * @return a ticket or null if a box office failed to reserve the seat
     */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
	    //First, check if the seat has been reserved or not
        if(seat.getStatus()){
            return null;
        }

	    //Create a new ticket, print it, and return this ticket
        Ticket ticket = new Ticket(this.show, boxOfficeId, seat, client);
        System.out.println(ticket);
        soldList.add(ticket);
        return ticket;
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog(){
	    return soldList;
	}
}
