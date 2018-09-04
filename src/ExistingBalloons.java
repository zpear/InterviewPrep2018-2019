import java.util.HashMap;
import java.util.Map;

/**
 * Balloons can be inflated at certain times, and last a certain number of seconds before they pop. Find out
 * how many balloons exist at a given time.
 */
public class ExistingBalloons {

    private static class Balloon {
        int start;
        int end;
        int seconds;

        // For implementation 2
        public Balloon(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // For implementation 1
        public Balloon(int seconds) {
            this.seconds = seconds;
        }
    }

    /**
     * Implementation 1: Balloons are received in a sequential stream. The user inputs the seconds each balloon will
     * last after they are created at the given time by entering a nonzero number. After entering 0, the timeline
     * advances one second. After entering -1 all the balloons have been created.
     *
     * Now the user inputs the time they want to know how many balloons exist at. -1 to exit the program.
     */


    /**
     * Implementation 2: Balloons are passed in as an array of Balloon objects with start and end times. An array of
     * integers in which each element represents the number of balloons inflated at each second i.
     *
     * O(t+b) where t is the number of seconds and b is the number of balloons
     */
    public static int[] getTimeline(Balloon[] balloons) {
        Map<Integer, Integer> increments = new HashMap<>();
        int end = 0;

        // Populate increments map
        for (Balloon balloon : balloons) {
            // increment start
            Integer point = increments.get(balloon.start);
            if (point == null) {
                point = 1;
            } else {
                point++;
            }
            increments.put(balloon.start, point);
            // decrement end
            point = increments.get(balloon.end);
            if (point == null) {
                point = -1;
            } else {
                point--;
            }
            increments.put(balloon.end, point);
            end = Math.max(end, balloon.end);
        }

        // Calculate timeline based on map
        int[] timeline = new int[end];
        for (int i = 0; i < end; i++) {
            Integer increment = increments.get(i);
            increment = (increment == null) ? 0 : increment;

            int last = (i > 0) ? timeline[i-1] : 0;
            timeline[i] = last + increment;
        }

        return timeline;
    }

    public static void main(String[] args) {
        Balloon[] balloons = new Balloon[4];

        balloons[0] = new Balloon(0,6);
        balloons[1] = new Balloon(1,7);
        balloons[2] = new Balloon(3,9);
        balloons[3] = new Balloon(0,2);

        int[] timeline = getTimeline(balloons);

        Print p = new Print();
        // 2,3,2,3,3,3,2,1,1
        p.printArr(timeline);
    }
}
