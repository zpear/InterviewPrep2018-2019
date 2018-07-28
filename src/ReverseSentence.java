import java.util.Stack;

/**
 * Let the input string be “i like this program very much”.
 * The function should change the string to “much very program this like i”
 */
public class ReverseSentence {

    /**
     * Assume only one space between words in the sentence
     */
    public static String reverseSentence(String sentence) {
        sentence = sentence.trim();
        sentence += " "; // add this so the last word is easy to grab with substring()
        Stack<String> stack = new Stack<String>();

        int wordStart = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ') {
                String word = sentence.substring(wordStart, i);
                stack.push(word);
                wordStart = i+1;
            }
        }
        String reverse = "";
        while (stack.size() > 0) {
            reverse += stack.pop() + " ";
        }

        return reverse.trim();
    }

    public static void main(String[] args) {
        String test1 = "I really like to do the cha cha";
        String test2 = "first second third fourth";

        System.out.println(reverseSentence(test1));
        System.out.println(reverseSentence(test2));
    }
}
