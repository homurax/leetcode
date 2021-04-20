/**
 * 1524. Number of Sub-arrays With Odd Sum
 *
 *
 * Given an array of integers arr. Return the number of sub-arrays with odd sum.
 *
 * As the answer may grow large, the answer must be computed modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,3,5]
 * Output: 4
 * Explanation: All sub-arrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
 * All sub-arrays sum are [1,4,9,3,8,5].
 * Odd sums are [1,9,3,5] so the answer is 4.
 *
 * Example 2:
 *
 * Input: arr = [2,4,6]
 * Output: 0
 * Explanation: All sub-arrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
 * All sub-arrays sum are [2,6,12,4,10,6].
 * All sub-arrays have even sum and the answer is 0.
 *
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5,6,7]
 * Output: 16
 *
 * Example 4:
 *
 * Input: arr = [100,100,99,99]
 * Output: 4
 *
 * Example 5:
 *
 * Input: arr = [7]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1. 1 <= arr.length <= 10^5
 * 2. 1 <= arr[i] <= 100
 */
public class NumberOfSubArraysWithOddSum {


    /**
     * 当 preSum(arr[0:i]) 为偶数时，preSum(arr[0:j]) 为奇数， j < i；则 preSum(arr[j+1:i]) 是奇数，反过来同理
     *
     * 维护奇偶前缀和个数计算
     */
    public int numOfSubarrays(int[] arr) {
        int ans = 0;
        int odd = 0, even = 1, flag = 0;
        for (int num : arr) {
            if ((flag ^= num & 1) == 1) {
                ans += even;
                odd++;
            } else {
                ans += odd;
                even++;
            }
            if (ans >= 1000000007) {
                ans -= 1000000007;
            }
        }
        return ans;
    }

    public int numOfSubarrays1(int[] arr) {
        long odd = 0;
        int sum = 0;
        for (int num : arr) {
            sum += num;
            odd += sum % 2;
        }
        return (int) (odd * (arr.length + 1 - odd) % 1000000007);
    }

}
