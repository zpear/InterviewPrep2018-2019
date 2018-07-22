/**
 * Letters are encoded A-Z as 1-26. Given an array of numbers, determine the total number of ways a message could be
 * encoded
 */
public class TotalDecodingMessages {

    public static int decodePossibilities(int[] message) {
        // Assume that all numbers are positive
        int possibilities = 1;

        for (int i = 0; i < message.length - 1; i+=2) {
            int possibility = (message[i] <= 2 && message[i+1] <= 6) ? 1 : 0;
            possibilities += possibility;
        }
        for (int i = 1; i < message.length - 1; i+=2) {
            int possibility = (message[i] <= 2 && message[i+1] <= 6) ? 1 : 0;
            possibilities += possibility;
        }
        return possibilities;
    }

    public static void main(String[] args) {
        int[] test1 = {1,2,3};
        int[] test2 = {2,5,2,3};
        int[] test3 = {2,2,2,3};
        int[] test4 = {2,5,6,3};

        System.out.println(decodePossibilities(test1));
        System.out.println(decodePossibilities(test2));
        System.out.println(decodePossibilities(test3));
        System.out.println(decodePossibilities(test4));
    }
}
