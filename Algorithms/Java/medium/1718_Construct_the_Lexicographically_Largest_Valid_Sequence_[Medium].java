/**
 * 1718. Construct the Lexicographically Largest Valid Sequence
 *
 *
 * Given an integer n, find a sequence that satisfies all of the following:
 *
 * The integer 1 occurs once in the sequence.
 * Each integer between 2 and n occurs twice in the sequence.
 * For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
 * The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.
 *
 * Return the lexicographically largest sequence. It is guaranteed that under the given constraints, there is always a solution.
 *
 * A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position where a and b differ, sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0] is lexicographically larger than [0,1,5,6] because the first position they differ is at the third number, and 9 is greater than 5.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: [3,1,2,3,2]
 * Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.
 *
 * Example 2:
 *
 * Input: n = 5
 * Output: [5,3,1,4,3,5,2,4,2]
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 20
 */
public class ConstructTheLexicographicallyLargestValidSequence {

    // 贪心 + 回溯
    public int[] constructDistancedSequence(int n) {
        int[] ans = new int[2 * n - 1];
        backtrack(ans, new boolean[n + 1], 0, n);
        return ans;
    }

    private boolean backtrack(int[] ans, boolean[] visited, int idx, int n) {
        if (idx == ans.length) {
            return true;
        }
        if (ans[idx] > 0) {
            return backtrack(ans, visited, idx + 1, n);
        }
        for (int i = n; i >= 1; i--) {
            if (visited[i]) {
                continue;
            }
            if (i > 1 && (idx + i >= ans.length || ans[idx + i] > 0)) {
                continue;
            }
            if (i == 1) {
                ans[idx] = 1;
            } else {
                ans[idx] = ans[idx + i] = i;
            }
            visited[i] = true;
            if (backtrack(ans, visited, idx + 1, n)) {
                return true;
            }
            if (i == 1) {
                ans[idx] = 0;
            } else {
                ans[idx] = ans[idx + i] = 0;
            }
            visited[i] = false;
        }
        return false;
    }

}
