/**
 * 1764. Form Array by Concatenating Subarrays of Another Array
 *
 *
 * You are given a 2D integer array groups of length n. You are also given an integer array nums.
 *
 * You are asked if you can choose n disjoint subarrays from the array nums such that the ith subarray is equal to groups[i] (0-indexed), and if i > 0, the (i-1)th subarray appears before the ith subarray in nums (i.e. the subarrays must be in the same order as groups).
 *
 * Return true if you can do this task, and false otherwise.
 *
 * Note that the subarrays are disjoint if and only if there is no index k such that nums[k] belongs to more than one subarray. A subarray is a contiguous sequence of elements within an array.
 *
 *
 *
 * Example 1:
 *
 * Input: groups = [[1,-1,-1],[3,-2,0]], nums = [1,-1,0,1,-1,-1,3,-2,0]
 * Output: true
 * Explanation: You can choose the 0th subarray as [1,-1,0,1,-1,-1,3,-2,0] and the 1st one as [1,-1,0,1,-1,-1,3,-2,0].
 * These subarrays are disjoint as they share no common nums[k] element.
 *
 * Example 2:
 *
 * Input: groups = [[10,-2],[1,2,3,4]], nums = [1,2,3,4,10,-2]
 * Output: false
 * Explanation: Note that choosing the subarrays [1,2,3,4,10,-2] and [1,2,3,4,10,-2] is incorrect because they are not in the same order as in groups.
 * [10,-2] must come before [1,2,3,4].
 *
 * Example 3:
 *
 * Input: groups = [[1,2,3],[3,4]], nums = [7,7,1,2,3,4,7,7]
 * Output: false
 * Explanation: Note that choosing the subarrays [7,7,1,2,3,4,7,7] and [7,7,1,2,3,4,7,7] is invalid because they are not disjoint.
 * They share a common elements nums[4] (0-indexed).
 *
 *
 * Constraints:
 *
 * groups.length == n
 * 1. 1 <= n <= 10^3
 * 2. 1 <= groups[i].length, sum(groups[i].length) <= 10^3
 * 3. 1 <= nums.length <= 10^3
 * 4. -10^7 <= groups[i][j], nums[k] <= 10^7
 */
public class FormArrayByConcatenatingSubarraysOfAnotherArray {

    public boolean canChoose(int[][] groups, int[] nums) {
        int fromIdx = 0, n = nums.length;
        for (int[] group : groups) {
            while (true) {
                if (fromIdx >= n) {
                    return false;
                }
                int beginIdx = indexOf(nums, group[0], fromIdx);
                if (beginIdx == -1) {
                    return false;
                }
                if (isSubArray(nums, group, beginIdx)) {
                    fromIdx = beginIdx + group.length;
                    break;
                } else {
                    fromIdx++;
                }
            }
        }
        return true;
    }

    private int indexOf(int[] nums, int target, int fromIdx) {
        for (int i = fromIdx; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private boolean isSubArray(int[] nums, int[] targets, int beginIdx) {
        if (beginIdx + targets.length > nums.length) {
            return false;
        }
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] != nums[beginIdx + i]) {
                return false;
            }
        }
        return true;
    }


    // 以 nums 为基准
    public boolean canChoose1(int[][] groups, int[] nums) {
        int m = 0, n = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == groups[m][n]) {
                if (++n == groups[m].length) {
                    if (++m == groups.length) {
                        return true;
                    }
                    n = 0;
                }
            } else { // 回退
                i -= n;
                n = 0;
            }
        }
        return false;
    }

}
