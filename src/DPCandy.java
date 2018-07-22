/**
 * Alice is a kindergarten teacher. She wants to give some candies to the children in her class.  All the children sit
 * in a line and each of them has a rating score according to his or her performance in the class.  Alice wants to give
 * at least 1 candy to each child. If two children sit next to each other, then the one with the higher rating must get
 * more candies. Alice wants to minimize the total number of candies she must buy.
 */

public class DPCandy {

    public static int findMinCandies(int[] kids) {
        int[] minCandies = new int[kids.length];

        for (int i = 0; i < kids.length; i++) {
            int candy = 1;
            if (i != 0 && kids[i - 1] < kids[i]) {
                candy = minCandies[i - 1] + 1;
            }
            minCandies[i] = candy;
        }

        for (int i = kids.length - 2; i >= 0; i--) {
            if (kids[i] > kids[i + 1] && minCandies[i] <= minCandies[i+1]) {
                minCandies[i] = minCandies[i+1] + 1;
            }
        }
        int sum = 0;
        for (int x : minCandies) {
            sum += x;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] test1 = {2,4,2,6,1,7,8,9,2,1};
        int[] test2 = {1,2,2};

        System.out.println(findMinCandies(test1));
        System.out.println(findMinCandies(test2));
    }
}
