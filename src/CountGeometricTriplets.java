import java.util.HashMap;
import java.util.HashSet;

/**
 * You are given an array and you need to find number of tripets of indices (i, j, k) such that the elements at those
 * indices are in geometric progression for a given common ratio r and i < j < k.
 */
public class CountGeometricTriplets {

    public static int countGeoTrips(int r, int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            Integer numTimes = map.get(i);
            if (numTimes == null) {
                numTimes = 1;
            } else {
                numTimes++;
            }
            map.put(i, numTimes);
        }

        int total = 0;
        HashSet<Integer> checked = new HashSet<>();
        for (int i : arr) {
            if (!checked.contains(i)) {
                checked.add(i);
                Integer second = i * r;
                Integer third = second * r;
                if (map.get(second) != null && map.get(third) != null) {
                    total += map.get(i) * map.get(second) * map.get(third);
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 2, 4};
        int[] test2 = {1, 3, 9, 9, 27, 81};
        int[] test3 = {1, 5, 5, 25, 125};

        System.out.println(countGeoTrips(2, test1));
        System.out.println(countGeoTrips(3, test2));
        System.out.println(countGeoTrips(5, test3));
    }
}
