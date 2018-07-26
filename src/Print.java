public class Print <T> {
    public void printArr(T[] arr) {
        System.out.print('{');
        for (T x : arr) {
            System.out.print(x + ",");
        }
        System.out.print('}');
    }

    public void printArr(int[] arr) {
        System.out.print('{');
        for (int x : arr) {
            System.out.print(x + ",");
        }
        System.out.print('}');
    }

    public void print2DArr(int[][] arr) {
        for (int[] row : arr) {
            printArr(row);
            System.out.println();
        }
    }
}
