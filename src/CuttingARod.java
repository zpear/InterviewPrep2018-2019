/**
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces. For example, if length of the
 * rod is 8 and the values of different pieces are given as following, then the maximum obtainable value is 22
 * (by cutting in two pieces of lengths 2 and 6)
 */
public class CuttingARod {
    public static int[][] dp;

    // Variant of the knapsack problem, with repeatable units
    public static int findMaxValue(int[] prices) {
        int n = prices.length;
        dp = new int[n+1][n+1];

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                int currentPrice = prices[row-1];

                int priceToAdd = (col-row >= 0) ? dp[row][col - row] : Integer.MIN_VALUE;
                int lastPrice = dp[row - 1][col];

                dp[row][col] = Math.max(lastPrice, priceToAdd+currentPrice);
            }
        }
        return dp[n-1][n-1];
    }

    public static void main(String[] args) {
        int[] prices = {1,5,8,9,10,17,17,20};
        System.out.println(findMaxValue(prices));

        Print p = new Print();
        p.print2DArr(dp);
    }
}
