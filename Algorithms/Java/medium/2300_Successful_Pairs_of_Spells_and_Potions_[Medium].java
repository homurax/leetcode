/**
 * 2300. Successful Pairs of Spells and Potions
 *
 *
 * You are given two positive integer arrays spells and potions, of length n and m respectively, where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
 *
 * You are also given an integer success. A spell and potion pair is considered successful if the product of their strengths is at least success.
 *
 * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
 *
 *
 *
 * Example 1:
 *
 * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * Output: [4,0,3]
 * Explanation:
 * - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
 * - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
 * - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
 * Thus, [4,0,3] is returned.
 *
 *
 * Example 2:
 *
 * Input: spells = [3,1,2], potions = [8,5,8], success = 16
 * Output: [2,0,2]
 * Explanation:
 * - 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
 * - 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
 * - 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
 * Thus, [2,0,2] is returned.
 *
 *
 * Constraints:
 *
 * 1. n == spells.length
 * 2. m == potions.length
 * 3. 1 <= n, m <= 10^5
 * 4. 1 <= spells[i], potions[i] <= 10^5
 * 5. 1 <= success <= 10^10
 */
public class SuccessfulPairsOfSpellsAndPotions {

    // a >= b + 1 等价于 a > b
    // x * y >= success -> y >= success / x -> y > (success - 1) / x
    // 不能使用 Arrays.binarySearch 无法处理数组中重复元素的情况
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        long[] temp = new long[m];
        for (int i = 0; i < m; i++) {
            temp[i] = potions[i];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = m - binarySearch(temp, (success - 1) / spells[i] + 1);
        }
        return ans;
    }

    private int binarySearch(long[] arr, long target) {
        int l = 0, r = arr.length - 1;
        if (arr[r] < target) {
            return r + 1;
        }
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

}
