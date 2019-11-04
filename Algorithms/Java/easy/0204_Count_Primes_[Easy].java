/**
 * 204. Count Primes
 *
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Example:
 *
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes {

    public int countPrimes(int n) {

        int k = 2;
        int[] arr = new int[n];

        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                if (i % k == 0 && i != k) {
                    arr[i] = 1;
                }
            }

            for (int i = k + 1; i < n; i++) {
                if (i > k && arr[i] == 0) {
                    k = i;
                    break;
                }
            }
        }

        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (arr[i] == 0) {
                ans++;
            }
        }
        return ans;
    }

    public int countPrimes2(int n) {

        if (n < 3) {
            return 0;
        }
        boolean[] nums = new boolean[n];
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (nums[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += 2 * i) {
                if (!nums[j]) {
                    count--;
                    nums[j] = true;
                }
            }
        }

        return count;
    }
}
