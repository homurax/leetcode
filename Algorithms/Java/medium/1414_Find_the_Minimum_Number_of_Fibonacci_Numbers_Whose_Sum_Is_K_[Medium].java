/**
 * 1414. Find the Minimum Number of Fibonacci Numbers Whose Sum Is K
 *
 * Given the number k, return the minimum number of Fibonacci numbers whose sum is equal to k, whether a Fibonacci number could be used multiple times.
 *
 * The Fibonacci numbers are defined as:
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 , for n > 2.
 * It is guaranteed that for the given constraints we can always find such fibonacci numbers that sum k.
 *
 *
 * Example 1:
 *
 * Input: k = 7
 * Output: 2
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 * For k = 7 we can use 2 + 5 = 7.
 *
 * Example 2:
 *
 * Input: k = 10
 * Output: 2
 * Explanation: For k = 10 we can use 2 + 8 = 10.
 *
 * Example 3:
 *
 * Input: k = 19
 * Output: 3
 * Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 *
 *
 * Constraints:
 *
 * 1 <= k <= 10^9
 */
public class FindTheMinimumNumberOfFibonacciNumbersWhoseSumIsK {

    public int findMinFibonacciNumbers(int k) {
        int a = 1, b = 1, idx = 1;
        int[] fibo = new int[44];
        fibo[0] = a;
        fibo[1] = b;
        while (a + b <= k) {
            int c = a + b;
            fibo[++idx] = c;
            a = b;
            b = c;
        }
        int ans = 0;
        for (int i = idx; i >= 0; i--) {
            if (k >= fibo[i]) {
                ans++;
                k -= fibo[i];
            }
        }
        return ans;
    }

    public int findMinFibonacciNumbers2(int k) {
        if (k <= 1) {
            return k;
        }
        int pre = 1, cur = 1;
        while (cur <= k) {
            cur += pre;
            pre = cur - pre;
        }
        return 1 + findMinFibonacciNumbers2(k - pre);
    }

}
