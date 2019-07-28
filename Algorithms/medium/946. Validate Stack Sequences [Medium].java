/**
 * 946. Validate Stack Sequences
 *
 * Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
 *
 * Example 1:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 * Explanation: We might do the following sequence:
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * Example 2:
 *
 * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * Output: false
 * Explanation: 1 cannot be popped before 2.
 *
 *
 * Note:
 *
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed is a permutation of popped.
 * pushed and popped have distinct values.
 */
public class ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int[] stack = new int[pushed.length];
        int size = 0;
        for (int i = 0, j = 0; i < pushed.length; i++) {
            stack[size++] = pushed[i];
            while (size != 0 && stack[size - 1] == popped[j]) {
                size--;
                j++;
            }
        }
        return size == 0;
    }

    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int topIndex = 0, i = 1;
        for (int pop : popped)
            if (topIndex >= 0 && pushed[topIndex] == pop) {
                topIndex--;
            } else {
                while (i < popped.length && pop != pushed[i]) {
                    pushed[++topIndex] = pushed[i++];
                }
                if (i == popped.length) {
                    return false;
                } else {
                    i++;
                }
            }
        return true;
    }
}
