import java.util.HashSet;

/*
 Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not
 share common letters. You may assume that each word will contain only lower case letters. If no such two words exist,
 return 0.

 Example 1:
 Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 Return 16
 The two words can be "abcw", "xtfn".

 Example 2:
 Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 Return 4
 The two words can be "ab", "cd".
 Example 3:
 Given ["a", "aa", "aaa", "aaaa"]
 Return 0
 No such pair of words.
*/
public class MaxStringProduct {

    // There are multiple small optimizations that could be done for this implementation
    // but as the ones I see do not affect the Big O runtime I did not bother
    public static int getMaxUniqueLengthProduct(String[] strings) {
        HashSet<Character>[] hSets = new HashSet[strings.length];
        int[] products = new int[strings.length];

        for (int i = 0; i < strings.length; i++) {
            hSets[i] = new HashSet<>();
            for (char c : strings[i].toCharArray()) {
                hSets[i].add(c);
            }
        }

        for (int i = 0; i < strings.length; i++) {
            String anchor = strings[i];
            int maxOperandLength = 0;
            for (int j = i+1; j < strings.length; j++) {
                boolean noneShared = true;
                for (char c : anchor.toCharArray()) {
                    if (hSets[j].contains(c)) {
                        noneShared = false;
                        break;
                    }
                }
                if (noneShared) {
                    maxOperandLength = Math.max(hSets[j].size(), maxOperandLength);
                }
            }
            products[i] = anchor.length() * maxOperandLength;
        }
        int max = 0;
        for (int i : products) {
            max = (i > max) ? i : max;
        }
        return max;
    }

    public static void main(String[] args) {
        String[] test1 = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        String[] test2 = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        String[] test3 = {"a", "aa", "aaa", "aaaa"};

        System.out.println(getMaxUniqueLengthProduct(test1));
        System.out.println(getMaxUniqueLengthProduct(test2));
        System.out.println(getMaxUniqueLengthProduct(test3));
    }
}
