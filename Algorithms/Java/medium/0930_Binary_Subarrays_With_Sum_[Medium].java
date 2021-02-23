/**
 * 930. Binary Subarrays With Sum
 *
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,0,1,0,1], S = 2
 * Output: 4
 * Explanation:
 * The 4 subarrays are bolded below:
 * [1,0,1,x,x]
 * [1,0,1,0,x]
 * [x,0,1,0,1]
 * [x,x,1,0,1]
 *
 *
 * Note:
 *
 * 1. A.length <= 30000
 * 2. 0 <= S <= A.length
 * 3. A[i] is either 0 or 1.
 */
public class BinarySubarraysWithSum {

    // P[i] = A[0] + A[1] + ... + A[i - 1]
    // P[j + 1] - P[i] = A[i] + A[i + 1] + ... + A[j]
    public int numSubarraysWithSum(int[] A, int S) {
        int N = A.length;
        int[] P = new int[N + 1];
        for (int i = 0; i < N; i++) {
            P[i + 1] = P[i] + A[i];
        }
        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        for (int sum : P) {
            ans += count.getOrDefault(sum, 0);
            count.put(sum + S, count.getOrDefault(sum + S, 0) + 1);
        }
        return ans;
    }



    public int numSubarraysWithSum2(int[] A, int sum) {
        return sliding(A, sum) - sliding(A, sum - 1);
    }

    private int sliding(int[] A, int sum) {
        if (sum < 0) {
            return 0;
        }
        int ans = 0;
        int currSum = 0;
        for (int left = 0, right = 0; right < A.length; right++) {
            currSum += A[right];
            while (currSum > sum) {
                currSum -= A[left++];
            }
            ans += (right - left) + 1;
        }
        return ans;
    }
}
