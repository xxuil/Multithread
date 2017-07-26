package assignment6;

/**
 * Ticket Class
 * Created by liuxx on 2017/7/24.
 */
public class Ticket {
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
                ret = ret + "|";
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
                ret = ret + " |";
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
                ret = ret + " |";
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
                ret = ret + " |";
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
