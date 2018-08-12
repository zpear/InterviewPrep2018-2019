import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a list of integers representing temperatures for a given day, return a list of the number of days you would
 * have to wait to reach a day with a warmer temperature than that of the given day.
 */
public class DaysToWarmerTemp {


    /**
     * Assumptions: there are no repeated temperatures in the input array,
     * PriorityQueue is by default a min-heap,
     * If there is no day warmer than a given day, the value at that given index of the returned array will be 0
     */
    public static int[] findDaysToWarmerTemp(int[] temps) {
        // Map each temperature to its relative position
        HashMap<Integer, Integer> tempToDay = new HashMap<>();
        for (int i = 0; i < temps.length; i++) {
            tempToDay.put(temps[i], i);
        }
        // Default sorting is minimum-first
        PriorityQueue<Integer> lookedAtTemps = new PriorityQueue<>();
        lookedAtTemps.add(temps[0]);
        int[] allDaysToWait = new int[temps.length];

        for (int i = 0; i < temps.length; i++) {
            while (lookedAtTemps.peek() != null && temps[i] > lookedAtTemps.peek()) {
                int tempToUpdate = lookedAtTemps.poll();
                int dayToUpdate = tempToDay.get(tempToUpdate);
                int daysToWait = i - dayToUpdate;
                allDaysToWait[dayToUpdate] = daysToWait;
            }
            lookedAtTemps.add(temps[i]);
        }
        return allDaysToWait;
    }

    public static void main(String[] args) {
        int[] test1 = {1,2,0,5};
        int[] result = findDaysToWarmerTemp(test1);
        Print p = new Print();
        p.printArr(test1);
        System.out.println();
        p.printArr(result);
    }
}
