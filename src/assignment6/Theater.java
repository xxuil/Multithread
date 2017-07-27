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
    private static boolean isFull = false;


	public Theater(int numRows, int seatsPerRow, String show) {
        this.seatList = new ArrayList<>();
        this.show = show;
        soldList = new ArrayList<>();
        buildSeats(numRows, seatsPerRow);
	}

	private void buildSeats(int row, int col){
        for(int i = 1; i <= row; i++){
            for(int j= 1; j <= col; j++){
                this.seatList.add(new Seat(i, j));
            }
        }
    }

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public synchronized Seat bestAvailableSeat() {
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
	public synchronized Ticket printTicket(String boxOfficeId, Seat seat, int client) {
	    //First, check if the seat has been reserved or not
        if(seat.getStatus()){
            return null;
        }

	    //Create a new ticket, print it, and return this ticket
        Ticket ticket = new Ticket(this.show, boxOfficeId, seat, client);
        System.out.println(ticket);
        System.out.println();
        soldList.add(ticket);
        seat.reserve();

        if(soldList.size() == seatList.size())
            isFull = true;

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

	public synchronized boolean getStatus(){
	    return isFull;
    }

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
            String ret = "";
            for(int i = 0; i < 31; i++){
                ret = ret + "-";
            }

            ret = ret + "\n";

            for(int i = 0; i < 31; i++){
                if(i == 0)
                    ret = ret + "|";

                else if(i == 2) {
                    ret = ret + "Show: ";
                    i += 5;
                    continue;
                }

                else if(i == 8) {
                    ret = ret + show;
                    i += show.length();
                    continue;
                }
                else if(i == 30){
                    ret = ret + " |";
                }
                else
                    ret = ret + " ";
            }

            ret = ret + "\n";


            int j = 0;
            for(int i = 0; i < 31; i++){

                if(i == 0)
                    ret = ret + "|";

                else if(i == 2) {
                    ret = ret + "Box Office ID: ";
                    i += "Box Office ID: ".length();
                    j = i + 1;
                    continue;
                }

                else if(i == j) {
                    ret = ret + boxOfficeId;
                    i += boxOfficeId.length();
                    continue;
                }
                else if(i == 30){
                    ret = ret + "  |";
                }
                else
                    ret = ret + " ";
            }

            ret = ret + "\n";

            j = 0;
            for(int i = 0; i < 31; i++){
                if(i == 0)
                    ret = ret + "|";

                else if(i == 2) {
                    ret = ret + "Seat: ";
                    i += "Seat: ".length();
                    j = i + 1;
                    continue;
                }

                else if(i == j) {
                    ret = ret + seat;
                    i += seat.toString().length();
                    continue;
                }
                else if(i == 30){
                    ret = ret + "  |";
                }
                else
                    ret = ret + " ";
            }

            ret = ret + "\n";

            j = 0;
            for(int i = 0; i < 31; i++){
                if(i == 0)
                    ret = ret + "|";

                else if(i == 2) {
                    ret = ret + "Client: ";
                    i += "Client: ".length();
                    j = i + 1;
                    continue;
                }

                else if(i == j) {
                    ret = ret + client;
                    i += Integer.toString(client).length();
                    continue;
                }
                else if(i == 30){
                    ret = ret + "  |";
                }
                else
                    ret = ret + " ";
            }

            ret = ret + "\n";

            for(int i = 0; i < 31; i++){
                ret = ret + "-";
            }

            return ret;
        }
    }
}
