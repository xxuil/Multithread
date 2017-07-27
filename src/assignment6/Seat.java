package assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Seat Class
 * Created by Liuxx on 2017/7/24.
 */

public class Seat implements Comparable<Seat> {
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

    public int compareTo(Seat that){
        if(this.rowNum < that.getRowNum())
            return -1;

        if(this.rowNum > that.getRowNum())
            return 1;

        else {
            if (this.seatNum < that.getSeatNum())
                return -1;

            if (this.seatNum > that.getSeatNum())
                return 1;

            else return 0;
        }
    }

    @Override
    public String toString() {
        ArrayList<Character> row = new ArrayList<>();
        int temp = rowNum;

        while (temp > 0) {
            if (temp < 26) // just one Character
            {
                char tempChar = (char) (temp + 64);
                row.add(tempChar); //add the letter
                temp = 0;
            } else { // larger than one Character
                int quotient = temp / 26; // the part needs to be determined again
                int rem = temp % 26; //last letter

                if (rem == 0) {
                    temp = quotient - 1;
                    row.add('Z'); //last letter is 'Z'
                } else {
                    temp = quotient;
                    char tempChar = (char) (rem + 64);
                    row.add(tempChar);
                }

            }
        }

        Collections.reverse(row);
        String result = row.stream().map(Object::toString).collect(Collectors.joining("")); //convert to string
        return result + seatNum;
    }
}
