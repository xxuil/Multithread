package assignment6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A6Test
 * Created by liuxx on 2017/7/26.
 */
public class A6Test {
    private static String show = "A6 Movie";
    private static List<Theater.Ticket> concurrencyTestLog;

    public static void main(String[] args) throws InterruptedException{
        setupBeforeClass();
        testBestSeatDouble();
    }

    private static void joinAllThreads(List<Thread> threads) throws InterruptedException {
        for (Thread t: threads) {
            t.join();
        }
    }

    public static void setupBeforeClass() throws InterruptedException {
        Map<String, Integer> offices = new HashMap<String, Integer>() {{
            put("BX1", 100);
            put("BX2", 100);
        }};

        Theater t = new Theater(100, 2, show);
        BookingClient bc = new BookingClient(offices, t);
        joinAllThreads(bc.simulate());

        concurrencyTestLog = t.getTransactionLog();
    }

    public static void testBestSeatDouble() throws InterruptedException {
        Map<String, Integer> offices = new HashMap<String, Integer>() {{
            put("BX1", 15);
            put("BX2", 15);
        }};

        Theater t = new Theater(50, 1, show);
        BookingClient bc = new BookingClient(offices, t);
        joinAllThreads(bc.simulate());

        Theater.Seat best = t.bestAvailableSeat();
        assert(best != null);
        assert(best.toString().equals("AE1"));
    }


}
