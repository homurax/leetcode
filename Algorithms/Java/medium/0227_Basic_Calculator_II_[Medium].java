package com.homurax.algorithm.medium;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 227. Basic Calculator II
 * 
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * 
 * Example 1:
 * 
 * Input: "3+2*2"
 * Output: 7
 * 
 * Example 2:
 * 
 * Input: " 3/2 "
 * Output: 1
 * 
 * Example 3:
 * 
 * Input: " 3+5 / 2 "
 * Output: 5
 * 
 * Note:
 * 
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculatorII {


    public int calculate(String s) {
        final Queue<String> queue = new ArrayDeque<>();
        final Stack<Character> operators = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (Character.isSpaceChar(c)) {
                continue;
            }
            if (Character.isDigit(c)) {
                int k = i + 1;
                while (k < s.length() && Character.isDigit(s.charAt(k))) {
                    k++;
                }
                queue.add(s.substring(i, k));
                i = k - 1;
            } else {
                while (!operators.empty() && precede(operators.peek()) >= precede(c)) {
                    queue.add(operators.pop().toString());
                }
                operators.push(c);
            }
        }
        while (!operators.empty()) {
            queue.add(operators.pop().toString());
        }
        // System.out.println(queue);
        final Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            final String poll = queue.poll();
            if (isDigit(poll)) {
                stack.push(Integer.parseInt(poll));
            } else {
                final Integer pop1 = stack.pop();
                final Integer pop2 = stack.pop();
                switch (poll) {
                    case "*": stack.push(pop2 * pop1); break;
                    case "/": stack.push(pop2 / pop1); break;
                    case "+": stack.push(pop2 + pop1); break;
                    case "-": stack.push(pop2 - pop1); break;
                }
            }
        }
        return stack.pop();
    }

    private int precede(char op) {
        switch (op) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return 0;
    }

    private boolean isDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }




    public int calculate2(String s) {

        int num = 0;
        char operator = '+';
        int index = 0;
        final int length = s.length();
        int[] partialArr = new int[length];

        for (int i = 0; i < length; i++) {
            final char c = s.charAt(i);
            if (Character.isSpaceChar(c) && i < length - 1) {
                continue;
            }
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
                if (i < length - 1) {
                    continue;
                }
            }
            switch (operator) {
                case '+': partialArr[index++] = num; break;
                case '-': partialArr[index++] = -num; break;
                case '*': partialArr[index - 1] *= num; break;
                case '/': partialArr[index - 1] /= num; break;
            }
            operator = c;
            num = 0;
        }

        int ans = 0;
        for (int partial : partialArr) {
            ans += partial;
        }
        return ans;
    }

}
