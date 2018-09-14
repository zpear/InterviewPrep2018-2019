/**
 * Given an array of integers, find the subset of non-adjacent elements with the maximum sum.
 */
public class MaxNonAdjSubSum {

    public static int findMaxSum(int[] nums) {
        int[] sums = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int back2Sum = (i > 1) ? sums[i-2] : 0;
            int back1Sum = (i > 0) ? sums[i-1] : 0;

            int maxSum = Math.max(current + back2Sum, back1Sum);
            sums[i] = maxSum;
        }

        return sums[sums.length-1];
    }

    public static void main(String[] args) {
        int[] test1 = {1,0,2,10,2};
        System.out.println(findMaxSum(test1)); // 11
    }
}
