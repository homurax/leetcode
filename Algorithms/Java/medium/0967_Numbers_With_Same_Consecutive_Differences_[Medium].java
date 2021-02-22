/**
 * 967. Numbers With Same Consecutive Differences
 *
 *
 * Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
 *
 * Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 *
 * Example 2:
 *
 * Input: n = 2, k = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 * Example 3:
 *
 * Input: n = 2, k = 0
 * Output: [11,22,33,44,55,66,77,88,99]
 *
 * Example 4:
 *
 * Input: n = 2, k = 2
 * Output: [13,20,24,31,35,42,46,53,57,64,68,75,79,86,97]
 *
 *
 * Constraints:
 *
 * 1. 2 <= n <= 9
 * 2. 0 <= k <= 9
 */
public class NumbersWithSameConsecutiveDifferences {

    private List<Integer> list = new ArrayList<>();

    public int[] numsSameConsecDiff(int n, int k) {
        int[] temp = new int[n];
        for (int i = 1; i <= 9; i++) {
            if (9 - i < k && i < k) {
                continue;
            }
            temp[0] = i;
            dfs(temp, 1, k);
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void dfs(int[] temp, int index, int k) {
        if (index == temp.length) {
            int number = 0;
            for (int i : temp) {
                number = number * 10 + i;
            }
            list.add(number);
            return;
        }
        int prev = temp[index - 1];
        if (prev - k >= 0) {
            temp[index] = prev - k;
            dfs(temp, index + 1, k);
        }
        if (prev + k <= 9 && k != 0) {
            temp[index] = prev + k;
            dfs(temp, index + 1, k);

        }
    }

}
