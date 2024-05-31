/**
 * 2961. Double Modular Exponentiation
 *
 *
 * You are given a 0-indexed 2D array variables where variables[i] = [ai, bi, ci, mi], and an integer target.
 *
 * An index i is good if the following formula holds:
 *
 * 0 <= i < variables.length
 * ((aibi % 10)ci) % mi == target
 * Return an array consisting of good indices in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
 * Output: [0,2]
 * Explanation: For each index i in the variables array:
 * 1) For the index 0, variables[0] = [2,3,3,10], (23 % 10)3 % 10 = 2.
 * 2) For the index 1, variables[1] = [3,3,3,1], (33 % 10)3 % 1 = 0.
 * 3) For the index 2, variables[2] = [6,1,1,4], (61 % 10)1 % 4 = 2.
 * Therefore we return [0,2] as the answer.
 *
 *
 * Example 2:
 *
 * Input: variables = [[39,3,1000,1000]], target = 17
 * Output: []
 * Explanation: For each index i in the variables array:
 * 1) For the index 0, variables[0] = [39,3,1000,1000], (393 % 10)1000 % 1000 = 1.
 * Therefore we return [] as the answer.
 *
 *
 * Constraints:
 *
 * 1 <= variables.length <= 100
 * variables[i] == [ai, bi, ci, mi]
 * 1 <= ai, bi, ci, mi <= 10^3
 * 0 <= target <= 10^3
 */
public class DoubleModularExponentiation {

    // ((a ^ b) % 10) ^ c) % m == target
    // a = k1 * m + r1
    // b = k2 * m + r2
    // (a + b) mod m = ((a mod m) + (b mod m)) mod m = ((k1 + k2) * m + r1 + r2) mod m = (r1 + r2) mod m
    // (a * b) mod m = ((a mod m) * (b mod m)) mod m = ((k1 * k2 * m^2) + (k1 * r2 + k2 * r1) * m + r1*r2) mod m = (r1 * r2) mod m
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int[] v = variables[i];
            if (pow(pow(v[0], v[1], 10), v[2], v[3]) == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    // 快速幂
    private long pow(long x, int n, int mod) {
        long rst = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                rst = rst * x % mod;
            }
            x = x * x % mod;
        }
        return rst;
    }

}
