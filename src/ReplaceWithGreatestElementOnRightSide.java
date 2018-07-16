/*
Given an array of integers, replace every element with the next greatest element (greatest element on the right side)
in the array. Since there is no element next to the last element, replace it with -1. For example, if the array is
{16, 17, 4, 3, 5, 2}, then it should be modified to {17, 5, 5, 5, 2, -1}.
 */
public class ReplaceWithGreatestElementOnRightSide {
    public static Integer[] replace(Integer[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = arr.length-1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = (i ==  arr.length-1) ? -1 : max;
            max = (max > temp) ? max : temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        Print<Integer> print = new Print<>();
        Integer[] test1 = {16, 17, 4, 3, 5, 2};
        replace(test1);
        print.printArr(test1);
    }
}
