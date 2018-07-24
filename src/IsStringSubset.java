import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class IsStringSubset {

    public static boolean isStringSubset(String t, String s) {
        HashMap<Character, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < s.length() - 1; i++) {
            List<Integer> indices = map.get(s.charAt(i));
            if (indices == null) {
                indices = new LinkedList<Integer>();
            }
            indices.add(i);
            map.put(s.charAt(i), indices);
        }

        int last = -1;
        for (int i = 0; i < t.length() - 1; i++) {
            List<Integer> possIndices = map.get(t.charAt(i));

            if (possIndices == null) {
                return false;
            }
            boolean found = false;
            for (Integer x : possIndices) {
                possIndices.remove(x);
                if (x > last) {
                    last = x;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String test1 = "blaAfdjkaBjdlaCfdkla";
        String test2 = "ABC";

        System.out.println(isStringSubset(test2, test1));
    }
}
