/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much
 * water it is able to trap after raining.
 */
public class RainWaterCollector {

    public static int findMaxWater(int[] bars) {
        int[] leftMaxBar = new int[bars.length];
        leftMaxBar[0] = bars[0];
        int[] rightMaxBar = new int[bars.length];
        rightMaxBar[bars.length-1] = bars[bars.length-1];

        for (int i = 1; i < bars.length; i++) {
            int currentBar = bars[i];
            leftMaxBar[i] = Math.max(currentBar, leftMaxBar[i-1]);
        }

        for (int j = bars.length-2; j >= 0; j--) {
            int currentBar = bars[j];
            rightMaxBar[j] = Math.max(currentBar, rightMaxBar[j+1]);
        }
        int sumWater = 0;
        for (int v = 0; v < bars.length; v++) {
            int val = Math.min(leftMaxBar[v], rightMaxBar[v]) - bars[v];
            sumWater += val;
        }
        return sumWater;
    }

    public static void main(String[] args) {
        int[] test1 = {2,0,2};
        int[] test2 = {3,0,0,2,0,4};
        int[] test3 = {0,1,0,2,1,0,1,3,2,1,2,1};

        System.out.println(findMaxWater(test1));
        System.out.println(findMaxWater(test2));
        System.out.println(findMaxWater(test3));
    }
}
