/**
 * 1218. Longest Arithmetic Subsequence of Given Difference
 *
 * Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 *
 * Example 2:
 *
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 *
 * Example 3:
 *
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 *
 *
 * Constraints:
 *
 * 1. 1 <= arr.length <= 10^5
 * 2. -10^4 <= arr[i], difference <= 10^4
 */
public class LongestArithmeticSubsequenceOfGivenDifference {

    // TLE
    private int ans = 1;

    public int longestSubsequence1(int[] arr, int difference) {
        dfs(arr, 0, 1, difference, 1);
        return ans;
    }

    private void dfs(int[] arr, int pre, int cur, int difference, int count) {
        if (cur == arr.length) {
            ans = Math.max(ans, count);
            return;
        }
        if (arr[cur] - arr[pre] == difference) {
            dfs(arr, cur, cur + 1, difference, count + 1);
        } else {
            dfs(arr, pre, cur + 1, difference, count);
        }
        dfs(arr, cur, cur + 1, difference, 1);
    }



    public int longestSubsequence2(int[] arr, int difference) {
        int ans = 0;
        int[] dp = new int[arr.length];
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            int pre = arr[i] - difference;
            if (numCount.containsKey(pre)) {
                dp[i] = Math.max(dp[i], numCount.get(pre) + 1);
            }
            numCount.put(arr[i], dp[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        int[] dp = new int[20001];
        for (int num : arr) {
            int n = num + 10000;
            int t = n - difference;
            if (t < 0 || t > 20000) {
                dp[n] = Math.max(dp[n], 1);
            } else {
                dp[n] = Math.max(dp[n], dp[t] + 1);
            }
        }
        for (int i = 0; i < 20001; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
