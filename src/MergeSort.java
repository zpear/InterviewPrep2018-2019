public class MergeSort {

    public static void mergeSort(int[] arr, int start, int end) {
        if (end - start <= 1) {
            int temp = arr[start];
            if (arr[start] > arr[end]) {
                arr[start] = arr[end];
                arr[end] = temp;
            }
            return;
        }
        int middle = (start+end)/2;
        mergeSort(arr, start, middle);
        mergeSort(arr, middle + 1, end);

        int[] left = new int[middle-start+1];
        int[] right = new int[end-middle];

        int lIndex = 0;
        int rIndex = 0;

        copyIntoArr(arr, start, middle, left);
        copyIntoArr(arr, middle+1, end, right);

        for (int i = start; i <= end; i++) {
            if (rIndex >= right.length) {
                arr[i] = left[lIndex];
                lIndex++;
            } else if (lIndex >= left.length) {
                arr[i] = right[rIndex];
                rIndex++;
            } else if (left[lIndex] < right[rIndex]) {
                arr[i] = left[lIndex];
                lIndex++;
            } else {
                arr[i] = right[rIndex];
                rIndex++;
            }
        }
    }

    public static void copyIntoArr(int[] copyFrom, int start, int end, int[] copyTo) {
        if (end - start < 0)
            return;
        int x = 0;
        for (int i = start; i <= end; i++) {
            copyTo[x] = copyFrom[i];
            x++;
        }
    }

    public static void main(String[] args) {
        Print p = new Print();

        int[] test1 = {5,4,3,2,1,6,10,1431,21,13,4,1,10,13,13,45,21,14,3,4,3214123,414,314,1,3};
        mergeSort(test1, 0, test1.length-1);
        p.printArr(test1);

    }
}
