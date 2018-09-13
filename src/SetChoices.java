import java.util.LinkedList;

/**
 * Given n sets of choices: (1,2,3), (2,3,4), (4,5) You pick one element from each set of choices.
 * Generate all possible picking.
 */
public class SetChoices {

    public static void printPickings(int[][] sets, int currentSetIndex, LinkedList<Integer> currentPicks) {
        if (currentSetIndex >= sets.length) {
            for (Integer x : currentPicks) {
                System.out.print(x + " ");
            }
            System.out.println();
            return;
        }
        int[] currentSet = sets[currentSetIndex];

        for (Integer thisPick : currentSet) {
            currentPicks.add(thisPick);
            printPickings(sets, currentSetIndex+1, currentPicks);
            currentPicks.removeLast();
        }
    }

    public static void main(String[] args) {
        int[][] sets = new int[3][];
        int[] one = {1,2,3};
        int[] two = {2,3,4};
        int[] three = {4,5};
        sets[0] = one;
        sets[1] = two;
        sets[2] = three;

        // Should be 18 printed lines
        printPickings(sets, 0, new LinkedList<Integer>());

    }

}
