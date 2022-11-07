/**
 * 907. Sum of Subarray Minimums
 *
 *
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 *
 * Example 2:
 *
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 *
 *
 * Constraints:
 *
 * 1. 1 <= arr.length <= 3 * 10^4
 * 2. 1 <= arr[i] <= 3 * 10^4
 */
public class SumOfSubarrayMinimums {


    // 栈顶下面的元素正好也是栈顶的左边界
    public int sumSubarrayMins(int[] arr) {
        long ans = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 哨兵
        for (int r = 0; r <= arr.length; r++) {
            int num = r < arr.length ? arr[r] : -1; // 假设 arr 末尾有个 -1
            while (stack.size() > 1 && arr[stack.peek()] >= num) {
                int i = stack.pop();
                ans += (long) arr[i] * (i - stack.peek()) * (r - i);
            }
            stack.push(r);
        }
        return (int) (ans % 1_000_000_007);
    }


    // 对于 arr[i] 找到左右两边最近的 arr[L]、arr[R], arr[L] < arr[i], arr[i] > arr[R]
    // arr(L, R) 即 arr[L + 1, R - 1] 范围内的子数组的最小值均为 arr[i]
    // arr[i] * (i - (L + 1) + 1) * ((R - 1) - i + 1)
    // arr[i] * (i - L) * (R - i)
    // 为了避免重复, arr[L] < arr[i], arr[i] >= arr[R]
    public int sumSubarrayMins1(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        /*ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            left[i] = stack.peek();
            stack.push(i);
        }
        stack.clear();
        stack.push(n);
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            right[i] = stack.peek();
            stack.push(i);
        }*/

        Arrays.fill(right, n);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && arr[stack.peek()] >= arr[i]) {
                right[stack.pop()] = i; // i 恰好是栈顶的右边界
            }
            left[i] = stack.peek();
            stack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) arr[i] * (i - left[i]) * (right[i] - i);
        }
        return (int) (ans % 1_000_000_007);
    }

}
