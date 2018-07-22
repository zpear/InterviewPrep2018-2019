import java.util.HashMap;

public class LongestPalindrome {

    public static class Tuple {
        public int left;
        public int right;

        public Tuple(int l, int r) {
            left = l;
            right = r;
        }

        public int getSize() {
            return right - left + 1;
        }
    }

    public static Tuple findLongestPalindrome(String s) {
        // Want to map right index of palindrome to its Tuple
        HashMap<Integer, Tuple> palindromes = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (palindromes.get(i-1) != null) {
                // See if we can add on to this existing palindrome
                Tuple t = palindromes.get(i-1);
                if (t.left-1 >= 0 && s.charAt(t.left-1) == s.charAt(i)) {
                    // Normal expansion
                    t.left = t.left - 1;
                    t.right = i;
                } else if (t.getSize() == 1 && s.charAt(t.left) == s.charAt(i)) {
                    // 2-middle expansion
                    t.right = i;
                } else {
                    // can't, so create a new one
                    t = new Tuple(i, i);
                }
                palindromes.put(i, t);
            } else if (i - 1 >= 0 && i + 1 < s.length() && s.charAt(i - 1) == s.charAt(i + 1)) {
                palindromes.put(i, new Tuple(i - 1,i + 1));
            } else {
                palindromes.put(i, new Tuple(i, i));
            }
        }
        Tuple max = new Tuple(0,0);
        for (Tuple curr : palindromes.values()) {
            max = (curr.getSize() > max.getSize()) ? curr : max;
        }
        return max;
    }

    public static void printString(String s, Tuple x) {
        for (int i = x.left; i <= x.right; i++) {
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }
    public static void testMethod(String s) {
        Tuple x = findLongestPalindrome(s);
        for (int i = x.left; i <= x.right; i++) {
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String test1 = "abcbfdka";
        testMethod(test1);

        System.out.println("########");
        String test2 = "aaaabcghjklkjhg";
        testMethod(test2);

        System.out.println("########");
        String test3 = "aaaabcghjklkjhgasdfghjjhgfdsa";
        testMethod(test3);

    }
}
