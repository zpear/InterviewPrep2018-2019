/*
Given an array of integers, find the subset of non-adjacent elements with the maximum sum.
Calculate the sum of that subset.
 */

public class MaxSubsetSum {

    public static int maxSubsetSum(int[] arr) {

        int[] runningSums = new int[arr.length];
        int totalMax = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int last = i - 2;
            int currentMax;
            if (last >= 0) {
               currentMax = Math.max(arr[i], arr[i] + runningSums[last]);
            } else {
                currentMax = arr[i];
            }
            totalMax = Math.max(totalMax, currentMax);
            runningSums[i] = totalMax;
        }
        return runningSums[arr.length-1];
    }

    public static void main(String[] args)
    {
        int[] test1 = {-2, 1, 3, -4, 5};
        int[] test2 = {3, 5, -7, 8, 10};

        System.out.println(maxSubsetSum(test1));
        System.out.println(maxSubsetSum(test2));
    }
}
