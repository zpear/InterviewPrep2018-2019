public class EggDropProblem {
    private static int[][] table;

    public static int findMinDrops(int eggs, int floors) {
        table = new int[eggs+1][floors+1];
        return findMinDropsHelper(eggs, floors);
    }

    private static int findMinDropsHelper(int eggs, int floors) {
        // Lookup previous result
        if (table[eggs][floors] != 0) {
            return table[eggs][floors];
        }
        // General base cases
        if (floors == 1 || floors == 0 || eggs == 1) {
            table[eggs][floors] = floors;
            return floors;
        }

        int minPoss = Integer.MAX_VALUE;
        for (int jump = 1; jump <= floors; jump++) {
            int eggBreaks = findMinDropsHelper(eggs-1, jump-1);
            int eggOk = findMinDropsHelper(eggs, floors - jump);
            int worstCase = Math.max(eggBreaks, eggOk);
            minPoss = Math.min(worstCase, minPoss);
        }

        table[eggs][floors] = minPoss + 1;
        return minPoss + 1;
    }

    public static void main(String[] args) {
        System.out.println(findMinDrops(2, 100)); // 14
        System.out.println(findMinDrops(2, 50)); // 10
        System.out.println(findMinDrops(3, 200)); // 11
        System.out.println(findMinDrops(5, 500)); // 10
    }
}
