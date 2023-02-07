/**
 * 457. Circular Array Loop
 *
 *
 * You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes the number of indices forward/backward you must move if you are located at index i:
 *
 * If nums[i] is positive, move nums[i] steps forward, and
 * If nums[i] is negative, move nums[i] steps backward.
 * Since the array is circular, you may assume that moving forward from the last element puts you on the first element, and moving backwards from the first element puts you on the last element.
 *
 * A cycle in the array consists of a sequence of indices seq of length k where:
 *
 * Following the movement rules above results in the repeating index sequence seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * Every nums[seq[j]] is either all positive or all negative.
 * k > 1
 * Return true if there is a cycle in nums, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [2,-1,1,2,2]
 * Output: true
 * Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
 * We can see the cycle 0 --> 2 --> 3 --> 0 --> ..., and all of its nodes are white (jumping in the same direction).
 *
 *
 * Example 2:
 *
 *
 * Input: nums = [-1,-2,-3,-4,-5,6]
 * Output: false
 * Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
 * The only cycle is of size 1, so we return false.
 *
 *
 * Example 3:
 *
 *
 * Input: nums = [1,-1,5,1,4]
 * Output: true
 * Explanation: The graph shows how the indices are connected. White nodes are jumping forward, while red is jumping backward.
 * We can see the cycle 0 --> 1 --> 0 --> ..., and while it is of size > 1, it has a node jumping forward and a node jumping backward, so it is not a cycle.
 * We can see the cycle 3 --> 4 --> 3 --> ..., and all of its nodes are white (jumping in the same direction).
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 5000
 * 2. -1000 <= nums[i] <= 1000
 * 3. nums[i] != 0
 *
 *
 * Follow up: Could you solve it in O(n) time complexity and O(1) extra space complexity?
 */
public class CircularArrayLoop {

    // 使用原数组标记
    public boolean circularArrayLoop(int[] nums) {
        int OFFSET = 100010;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= OFFSET) {
                continue;
            }
            int cur = i, tag = OFFSET + i, last;
            boolean flag = nums[cur] > 0;
            while (true) {
                last = nums[cur];
                int next = ((cur + nums[cur]) % n + n ) % n;
                nums[cur] = tag;
                cur = next;
                if (cur == i) break;
                if (nums[cur] >= OFFSET) break;
                if (flag && nums[cur] < 0) break;
                if (!flag && nums[cur] > 0) break;
            }
            if (last % n != 0 && nums[cur] == tag) return true;
        }
        return false;
    }

    // 记录是在哪一轮标记的
    public boolean circularArrayLoop2(int[] nums) {
        int n = nums.length;
        int[] visited = new int[n];
        for (int i = 0, cnt = 1; i < n; i++, cnt++) {
            if (visited[i] > 0) {
                continue;
            }
            int cur = i;
            boolean flag = nums[i] > 0;
            while (true) {
                int next = ((cur + nums[cur]) % n + n) % n;
                // k 需大于 1
                if (next == cur) {
                    break;
                }
                if (visited[next] > 0) {
                    // next 非本轮标记 则后续均标记
                    if (visited[next] != cnt) {
                        break;
                    } else {
                        // next 本轮标记 找到环
                        return true;
                    }
                }
                if ((flag && nums[next] < 0) || (!flag && nums[next] > 0)) {
                    break;
                }
                visited[next] = cnt;
                cur = next;
            }
        }
        return false;
    }

    // 模拟 存在重复检查
    public boolean circularArrayLoop1(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int cur = i;
            int k = 1;
            boolean flag = nums[i] > 0;
            while (true) {
                if (k > n) {
                    break;
                }
                int next = ((cur + nums[cur]) % n + n) % n;
                if ((flag && nums[next] < 0) || (!flag && nums[next] > 0)) {
                    break;
                }
                if (next == i) {
                    if (k > 1) {
                        return true;
                    } else {
                        break;
                    }
                }
                cur = next;
                k++;
            }
        }
        return false;
    }



}
