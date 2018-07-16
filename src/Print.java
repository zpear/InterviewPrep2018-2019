public class Print <T> {
    public void printArr(T[] arr) {
        System.out.print('{');
        for (T x : arr) {
            System.out.print(x + ",");
        }
        System.out.print('}');
    }
}
