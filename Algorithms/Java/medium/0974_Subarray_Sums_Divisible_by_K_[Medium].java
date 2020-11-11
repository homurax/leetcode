/**
 * 974. Subarray Sums Divisible by K
 *
 *
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *
 * Note:
 *
 * 1. 1 <= A.length <= 30000
 * 2. -10000 <= A[i] <= 10000
 * 3. 2 <= K <= 10000
 */
public class SubarraySumsDivisibleByK {

    /**
     * 前缀和
     * pre[i] = pre[i−1] + nums[i]
     *
     *      (pre[i] - pre[j-1]) mod K == 0
     * ->  pre[i] mod K == pre[j-1] mod K
     * (同余定理)
     */
    public int subarraysDivByK(int[] A, int K) {
        int sum = 0, ans = 0;
        int[] count = new int[K];
        count[0] = 1;
        for (int num : A) {
            sum += num;
            // 考虑余数为负数 Math.abs(sum % K)
            int remainder = (sum % K + K) % K;
            ans += count[remainder]++;
        }
        return ans;
    }

    public int subarraysDivByK2(int[] A, int K) {
        int sum = 0, ans = 0;
        int[] count = new int[K];
        count[0] = 1;
        for (int num : A) {
            sum += num;
            count[(sum % K + K) % K]++;
        }
        for (int i : count) {
            ans += i * (i - 1) / 2;
        }
        return ans;
    }

}
