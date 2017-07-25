package assignment6;

/**
 * Seat Class
 * Created by Liuxx on 2017/7/24.
 */

public class Seat {
    /*
     * Represents a seat in the theater
     * A1, A2, A3, ... B1, B2, B3 ...
     */
    private int rowNum;
    private int seatNum;

    private boolean reserved;

    public Seat(int rowNum, int seatNum) {
        this.rowNum = rowNum;
        this.seatNum = seatNum;
        this.reserved = false;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void reserve(){
        this.reserved = true;
    }

    public boolean getStatus(){
        return this.reserved;
    }

    @Override
    public String toString() {
        String ret = "";

        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String row = "";
        int index = rowNum / 26;

        row = String.valueOf(alphabet[rowNum % 26]);

        while(index >= 0) {
            row = String.valueOf(alphabet[rowNum]) + row;
            index -= 26;
        }

        return row + Integer.toString(seatNum);
    }
}
