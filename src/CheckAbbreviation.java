public class CheckAbbreviation {

    // 0 == unknown, 1 == true, 2 == false
    public static short[][] dp;

    public static boolean checkAbbreviation(String a, String b) {
        dp = new short[a.length()+1][b.length()+1]; // +1 is just for easy/intuitive lookup
        return checkAbbreviation(a.toCharArray(), 0, b.toCharArray(), 0);
    }

    private static boolean checkAbbreviation(char[] a, int aIndex, char[] b, int bIndex) {
        if (aIndex >= a.length && bIndex >= b.length) {
            dp[a.length - aIndex][b.length - bIndex] = 1;
            return true;
        } else if (aIndex >= a.length) {
            dp[a.length - aIndex][b.length - bIndex] = 2;
            return false;
        }else if (dp[a.length - aIndex][b.length - bIndex] != 0) {
            // Solution has been saved
//            return (dp[a.length - aIndex][b.length - bIndex] == 1) ? true : false;
        } else if (aIndex < a.length && bIndex >= b.length) {
            // If we delete the rest of the characters in a, might be an abbreviation
            boolean deleted = checkAbbreviation(a, aIndex+1, b, bIndex);
            dp[a.length - aIndex][b.length - bIndex] = (deleted) ? (short)1 : 2;

            return deleted;
        }

        char aChar = a[aIndex];
        char bChar = b[bIndex];

        System.out.println(aChar + " " + bChar);

        if (aChar == bChar) {
            // increment both indexes and check substrings
            System.out.println("equal");
            boolean incremented = checkAbbreviation(a, aIndex+1, b, bIndex+1);
            dp[a.length - aIndex][b.length - bIndex] = (incremented) ? (short)1 : (short)2;
            return incremented;

        } else if (aChar == bChar + 32) {
            System.out.println("uppercase or delete");
            // Simulate making a lowercase character uppercase OR deleting it
            boolean deleted = checkAbbreviation(a, aIndex+1, b, bIndex);
            boolean upperCased = checkAbbreviation(a, aIndex+1, b, bIndex+1);
            dp[a.length - aIndex][b.length - bIndex] = (deleted || upperCased) ? (short)1 : 2;

            return deleted || upperCased;
        } else if (aChar > 'Z'){
            // Simulate deleting a lowercase character from a
            System.out.println("delete");
            boolean deleted = checkAbbreviation(a, aIndex+1, b, bIndex);
            dp[a.length - aIndex][b.length - bIndex] = (deleted) ? (short)1 : 2;

            return deleted;
        }

        System.out.println("this path is false");
        // if none of the above pass, return false
        dp[a.length - aIndex][b.length - bIndex] = 2;
        return false;
    }

    public static void main(String[] args) {
        String a = "AbdDf";
        String b = "ABDF";

        System.out.println((int)'a');
        System.out.println((int)'A');

        System.out.println(checkAbbreviation(a, b));
    }
}
