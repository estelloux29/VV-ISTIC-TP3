package fr.istic.vv;

import java.util.*;

public class StringUtils {

    public static boolean isBalanced(String str) {
        Stack stack = new Stack<>();
        if (str.isEmpty() || str == null) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            if (!(isOpen(currentChar) || isClosed(currentChar))){return false;}

            if (isOpen(currentChar)) {
                stack.push(currentChar);
            }
            if (isClosed(currentChar)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = (char) stack.pop();
                if (!isMatched(currentChar, topChar)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isMatched(char currentChar, char topChar) {
        return ((currentChar == ']' && topChar == '[') ||
                (currentChar == ')' && topChar == '(') ||
                (currentChar == '}' && topChar == '{'));
    }

    private static boolean isClosed(char topChar) {
        List<Character> openChar = new ArrayList<>();
        openChar.add(')');
        openChar.add(']');
        openChar.add('}');
        return openChar.contains(topChar);
    }

    private static boolean isOpen(char currentChar) {
        List<Character> openChar = new ArrayList<>();
        openChar.add('(');
        openChar.add('[');
        openChar.add('{');
        return openChar.contains(currentChar);
    }

}
