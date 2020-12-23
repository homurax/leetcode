/**
 * 945. Minimum Increment to Make Array Unique
 *
 * Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
 *
 * Return the least number of moves to make every value in A unique.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,2]
 * Output: 1
 * Explanation:  After 1 move, the array could be [1, 2, 3].
 *
 * Example 2:
 *
 * Input: [3,2,1,2,1,7]
 * Output: 6
 * Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
 * It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 *
 *
 * Note:
 *
 * 1. 0 <= A.length <= 40000
 * 2. 0 <= A[i] < 40000
 */
public class MinimumIncrementToMakeArrayUnique {

    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : A) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int[] count = new int[max - min + 1];
        for (int num : A) {
            count[num - min]++;
        }
        int ans = 0;
        for (int i = 0; i < count.length - 1; i++) {
            if (count[i] > 1) {
                int move = count[i] - 1;
                ans += move;
                count[i + 1] += move;
            }
        }
        if (count[count.length - 1] > 1) {
            int diff = count[count.length - 1] - 1;
            ans += ((1 + diff) * diff) >> 1;
        }
        return ans;
    }

}
