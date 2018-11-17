/**
 * determine if a given binary string is valid ASCII.
 * Every character must be in one of the following forms.
 *
 * 1-byte: 0???????
 * 2-byte: 110????? 10??????
 * 3-byte: 1110???? 10?????? 10??????
 *
 *  Example:
 * a: "01100001"
 * aa: "0110000101100001"
 * ü: "1100001110111100"
 * ☃: "111000101001100010000011"
 *
 * A sample input is;
 * "111000101001100010000011"
 */

public class AsciiValid {

    /**
     * split string up into individual bytes
     *
     * 1st char == 0, move on to next byte
     * 1st char == 1
     *  make sure next char is 1, else reject
     *  3rd char = 0
     *      next byte, make sure starts w/ 10
     *  3rd char = 1
     *      4th char = 0
     *          make sure next 2 bytes start w/ 10
     *
     * Otherwise reject the entire string
     */

    public static boolean checkValidAscii(String input) {
        String[] bytes = splitNChars(input, 8);
        boolean inDouble = false;
        boolean inTriple = false;
        boolean valid = true;
        int numLookedAtForTriple = 0;

        for (int i = 0; i < bytes.length; i++) {
            String myByte = bytes[i];
            if (!inDouble && !inTriple) {
                // If 1st char is 1 need to check double or triple
                if (myByte.charAt(0) == '1') {
                    if (myByte.charAt(1) != '1') {
                        valid = false;
                        break;
                    } else {
                        if (myByte.charAt(2) == '1' && myByte.charAt(3) == '0') {
                            inTriple = true;
                            numLookedAtForTriple = 1;
                        } else if (myByte.charAt(2) == '0'){
                            inDouble = true;
                        } else {
                            valid = false;
                            break;
                        }
                    }
                }
            } else if (inDouble) {
                // If we get here, we are looking at the second byte of the double segment
                if (!myByte.substring(0,2).equals("10")) {
                    valid = false;
                    break;
                } else {
                    inDouble = false;
                }
            } else if (inTriple) {
                if (!myByte.substring(0,2).equals("10")) {
                    valid = false;
                    break;
                }
                numLookedAtForTriple++;
                if (numLookedAtForTriple == 3) {
                    inTriple = false;
                }

            }

        }
        return valid && !inDouble && !inTriple;
    }

    /**
     * Assume n divides into input evenly. If not, will go up to the last full group of n chars and truncate remainder
     */
    private static String[] splitNChars(String input, int n) {
        int numSubstrings = input.length() / n;
        int currentIndex = 0;
        String[] split = new String[numSubstrings];

        for (int i = 0; i < numSubstrings; i++) {
            if (i == numSubstrings - 1) {
                split[i] = input.substring(currentIndex);
            } else {
                split[i] = input.substring(currentIndex, currentIndex + n);
            }

            currentIndex += n;
        }

        return split;
    }

    public static void main(String[] args) {

        String testAscii = "11100010" +
                "10011000" +
                "10000011";
        System.out.println(checkValidAscii(testAscii)); // true
        testAscii = "00000000" +
                "11100000" +
                "10000000" +
                "10000000" +
                "11000000" +
                "10000000";
        System.out.println(checkValidAscii(testAscii)); // true
        testAscii = "00000000" +
                "11100000" +
                "10000000" +
                "10000000" +
                "11000000" +
                "00000000";
        System.out.println(checkValidAscii(testAscii)); // false
    }
}
