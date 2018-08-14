import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Remove duplicates in an unsorted array where the duplicates are at a distance of k or less from each other.
 */
public class RemoveKDistanceDuplicates {

    public static int[] removeDuplicates(int[] arr, int k) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        // Map each value to the indices it appears in
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = map.get(arr[i]);
            if (list == null) {
                list = new LinkedList<>();
            }
            list.add(i);
            map.put(arr[i], list);
        }
        List<Integer> indicesToRemove = new LinkedList<>();
        for (int value : map.keySet()) {
            List<Integer> indices = map.get(value);
            Iterator<Integer> itr = indices.iterator();
            int anchor = itr.next();
            while (itr.hasNext()) {
                int compare = itr.next();
                if (compare - anchor <= k) {
                    // we have duplicates k or fewer places apart
                    indicesToRemove.add(compare);
                } else {
                    // the duplicates are more than k places apart, set a new anchor
                    anchor = compare;
                }
            }
        }
        int[] trimmedArr = new int[arr.length - indicesToRemove.size()];
        Iterator<Integer> itr = indicesToRemove.iterator();
        int skipIndex = (itr.hasNext()) ? itr.next() : -1;
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != skipIndex) {
                trimmedArr[j] = arr[i];
                j++;
            } else {
                skipIndex = (itr.hasNext()) ? itr.next() : -1;
            }
        }
        return trimmedArr;
    }

    public static void main(String[] args) {
        Print p = new Print();
        int[] test1 = {1,1,2,3,4,5,2,3};
        int[] result = removeDuplicates(test1, 1);
        p.printArr(test1);
        System.out.println();
        p.printArr(result);
        result = removeDuplicates(test1, 4);
        System.out.println();
        p.printArr(result);
    }
}
