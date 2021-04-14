/**
 * 923. 3Sum With Multiplicity
 *
 *
 * Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
 *
 * As the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation:
 * Enumerating by the values (arr[i], arr[j], arr[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 *
 * Example 2:
 *
 * Input: arr = [1,1,2,2,2,2], target = 5
 * Output: 12
 * Explanation:
 * arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
 * We choose one 1 from [1,1] in 2 ways,
 * and two 2s from [2,2,2,2] in 6 ways.
 *
 *
 * Constraints:
 *
 * 1. 3 <= arr.length <= 3000
 * 2. 0 <= arr[i] <= 100
 * 3. 0 <= target <= 300
 */
public class ThreeSumWithMultiplicity {

    private int ans = 0;
    private int mod = 1_000_000_007;

    public int threeSumMulti(int[] arr, int target) {
        dfs(arr, 0, target, 0);
        return ans;
    }

    // TLE  case = 0, [0,0,0,...,0]
    private void dfs(int[] arr, int index, int target, int choose) {
        if (index == arr.length) {
            return;
        }
        if (arr[index] == target && choose == 2) {
            // 不 return
            // 可能还存在 arr[i] == arr[index], i in [index + 1, length)
            ans++;
        }
        if (arr[index] <= target) {
            dfs(arr, index + 1, target - arr[index], choose + 1);
        }
        dfs(arr, index + 1, target, choose);
    }


    // 计数后 排列组合
    public int threeSumMulti2(int[] arr, int target) {
        int mod = 1_000_000_007;
        long[] count = new long[101];
        for (int x: arr) {
            count[x]++;
        }
        long ans = 0;
        // All different
        for (int x = 0; x <= 100; x++) {
            for (int y = x + 1; y <= 100; y++) {
                int z = target - x - y;
                if (y < z && z <= 100) {
                    ans += count[x] * count[y] * count[z];
                    ans %= mod;
                }
            }
        }
        // x == y != z
        for (int x = 0; x <= 100; x++) {
            int z = target - 2 * x;
            if (x < z && z <= 100) {
                ans += count[x] * (count[x] - 1) / 2 * count[z];
                ans %= mod;
            }
        }
        // x != y == z
        for (int x = 0; x <= 100; x++) {
            if (target % 2 == x % 2) {
                int y = (target - x) / 2;
                if (x < y && y <= 100) {
                    ans += count[x] * count[y] * (count[y] - 1) / 2;
                    ans %= mod;
                }
            }
        }
        // x == y == z
        if (target % 3 == 0) {
            int x = target / 3;
            if (0 <= x && x <= 100) {
                ans += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
                ans %= mod;
            }
        }
        return (int) ans;
    }

    // 把三数之和变为二数之和
    public int threeSumMulti3(int[] arr, int target) {
        int mod = 1_000_000_007;
        long ans = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            int T = target - arr[i];
            int j = i + 1, k = arr.length - 1;
            while (j < k) {
                if (arr[j] + arr[k] < T) {
                    j++;
                } else if (arr[j] + arr[k] > T) {
                    k--;
                } else if (arr[j] != arr[k]) {
                    int left = 1, right = 1;
                    while (j + 1 < k && arr[j] == arr[j + 1]) {
                        left++;
                        j++;
                    }
                    while (k - 1 > j && arr[k] == arr[k - 1]) {
                        right++;
                        k--;
                    }
                    ans += (long) left * right;
                    ans %= mod;
                    j++;
                    k--;
                } else { // arr[j] == arr[k]
                    ans += (long) (k - j + 1) * (k - j) / 2;
                    ans %= mod;
                    break;
                }
            }
        }
        return (int) ans;
    }



}
