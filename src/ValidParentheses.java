import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 * valid.
 *
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {

    public static boolean checkValidity(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.size() == 0) {
                    return false;
                }
                char opener = stack.pop();
                if (opener == '(' && c != ')' || opener == '{' && c != '}' || opener == '[' && c != ']') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String test1 = "{{([])()}}";
        String test2 = "(()}";

        System.out.println(checkValidity(test1)); // true
        System.out.println(checkValidity(test2)); // false
    }
}
