/**
 * 1760. Minimum Limit of Balls in a Bag
 *
 *
 * You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer maxOperations.
 *
 * You can perform the following operation at most maxOperations times:
 *
 * Take any bag of balls and divide it into two new bags with a positive number of balls.
 * For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
 * Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.
 *
 * Return the minimum possible penalty after performing the operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [9], maxOperations = 2
 * Output: 3
 * Explanation:
 * - Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
 * - Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
 * The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.
 *
 *
 * Example 2:
 *
 * Input: nums = [2,4,8,2], maxOperations = 4
 * Output: 2
 * Explanation:
 * - Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
 * The bag with the most number of balls has 2 balls, so your penalty is 2 an you should return 2.
 *
 *
 * Example 3:
 *
 * Input: nums = [7,17], maxOperations = 2
 * Output: 7
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= maxOperations, nums[i] <= 10^9
 */
public class MinimumLimitOfBallsInABag {

    public int minimumSize(int[] nums, int maxOperations) {
        long sum = 0;
        int l = 1, r = 1;
        for (int num : nums) {
            sum += num;
            if (num > r) {
                r = num;
            }
        }
        while (l < r) {
            int cost = (l + r) / 2;
            long minOps = sum / cost - nums.length;
            long maxOps = sum / cost;
            if (minOps > maxOperations) {
                l = cost + 1;
            } else if (maxOps <= maxOperations) {
                r = cost;
            } else {
                int exactOps = 0;
                for (int num : nums) {
                    exactOps += (num - 1) / cost;
                }
                if (exactOps > maxOperations) {
                    l = cost + 1;
                } else {
                    r = cost;
                }
            }
        }
        return l;
    }

    public int minimumSize1(int[] nums, int maxOperations) {
        long ans = 0;
        long l = 1, r = 1;
        for (int num : nums) {
            if (num > r) {
                r = num;
            }
        }
        while (l <= r) {
            long mid = (l + r) / 2;
            if (check(nums, mid, maxOperations)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) ans;
    }

    // 尝试能否控制 cost 情况下，操作数不超过 maxOperations
    private boolean check(int[] nums, long cost, int maxOperations) {
        long ans = 0;
        for (int num : nums) {
            /*if (num % cost == 0) {
                ans += num / cost - 1;
            } else {
                ans += num / cost;
            }*/
            ans += (num - 1) / cost;
        }
        return ans <= maxOperations;
    }

}
