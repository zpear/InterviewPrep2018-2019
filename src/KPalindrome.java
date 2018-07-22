/**
 * A string is k palindrome if it can be transformed into a palindrome on removing at most k characters from it. Your
 * task is to complete the function isKPalindrome which takes two arguments a string str and a number k . Your function
 * should return true if the string is k palindrome else it should return false.
 */
public class KPalindrome {

    private static int findNumForPalindrome(String s, int i, int j) {
        if (i == j || i > j) {
            return 0;
        }
        else if (s.charAt(i) == s.charAt(j)) {
            return findNumForPalindrome(s, i+1, j-1);
        } else {
            return Math.min(findNumForPalindrome(s, i+1, j), findNumForPalindrome(s, i, j-1)) + 1;
        }
    }

    public static boolean isKPalindrome(String s, int k) {
        return (k >= findNumForPalindrome(s, 0, s.length()-1));
    }

    public static void main(String[] args) {
        String test1 = "abcdecba";
        String test2 = "acdcb";

        System.out.println(isKPalindrome(test1, 1));
        System.out.println(isKPalindrome(test2, 1));
    }
}
