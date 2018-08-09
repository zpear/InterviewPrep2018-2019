/**
 * There are n stairs, a person standing at the bottom wants to reach the top. The person can climb either 1 stair
 * or 2 stairs at a time. Count the number of ways, the person can reach the top.
 */
public class FindWaysToClimbSteps {

    public static long[] table;

    public static long findWaysToClimbSteps(int steps) {
        table = new long[steps+1];
        return findWaysToClimbStepsHelper(steps);
    }

    private static long findWaysToClimbStepsHelper(int steps) {
        if (steps == 0) {
            return 1;
        } else if (steps < 0) {
            return 0;
        }


        if (table[steps] == 0) {
            table[steps] = findWaysToClimbStepsHelper(steps-1) + findWaysToClimbStepsHelper(steps-2);
        }
        return table[steps];
    }

    public static void main(String[] args) {
        System.out.println(findWaysToClimbSteps(100));
    }
}
