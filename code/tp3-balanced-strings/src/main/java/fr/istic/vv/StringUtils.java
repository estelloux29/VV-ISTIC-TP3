package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    public static boolean isBalanced(String str) {
        Stack stack = new Stack<>();
        if (str == null || str.length() == 0) {
            return true;
        }

        char currentChar = str.charAt(0);

        if (currentChar == '[' || currentChar == '(' || currentChar == '{') {
            stack.push(currentChar);
        } else {
            char topChar = (char) stack.peek();

            if ((currentChar == ']' && topChar == '[') ||
                    (currentChar == ')' && topChar == '(') ||
                    (currentChar == '}' && topChar == '{')) {
                stack.pop();
            } else {
                return false;
            }
        }

        return isBalanced(str.substring(1));
    }


}
