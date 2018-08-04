// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
// stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
// connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
// of money you can rob tonight without alerting the police.
public class HouseRobber {

    public static int findMaxProfit(int[] money) {
        int[] sums = new int[money.length];

        for (int i = 0; i < money.length; i++) {
            int back2Sum = (i > 1) ? sums[i-2] : 0;
            int back1Sum = (i > 0) ? sums[i-1] : 0;
            int curr = money[i];
            sums[i] = Math.max(back1Sum, back2Sum + curr);
        }

        return sums[sums.length-1];
    }

    public static void main(String[] args) {
        int[] test1 = {1,3,2,5,12};
        int[] test2= {1,6,2,5,12};

        System.out.println(findMaxProfit(test1)); // 15
        System.out.println(findMaxProfit(test2)); // 18
    }
}
