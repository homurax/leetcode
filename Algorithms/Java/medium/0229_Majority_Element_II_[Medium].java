/**
 * 229. Majority Element II
 *
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: [3]
 *
 * Example 2:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 * Example 3:
 *
 * Input: nums = [1,2]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 5 * 10^4
 * 2. -10^9 <= nums[i] <= 10^9
 */
public class MajorityElementII {

    // 考虑取值范围 不适合用数组打表计数
    public List<Integer> majorityElement(int[] nums) {
        int limit = nums.length / 3;
        List<Integer> ans = new ArrayList<>();
        IntStream.of(nums).boxed().collect(Collectors.toConcurrentMap(x -> x, x -> 1, Integer::sum)).forEach((num, count) -> {
            if (count > limit) {
                ans.add(num);
            }
        });
        return ans;
    }

    // 摩尔投票算法 寻找一组元素中占多数的元素（超过一半）
    private int moore(int[] nums) {
        int choose = nums[0], count = 0;
        // 抵消
        for (int num : nums) {
            if (count == 0) {
                choose = num;
                count = 1;
            } else if (num == choose) {
                count++;
            } else {
                count--;
            }
        }
        if (count == 0) {
            return -1;
        }
        // 计数校验 [A, B, C] -> choose = C, 但是 C 没有超过一半
        count = 0;
        for (int num : nums) {
            if (num == choose) {
                count++;
            }
        }
        return count > nums.length / 2 ? choose : -1;
    }

    /**
     * 基于摩尔投票算法
     * 超过 1/3 即最多两位
     *
     * [A, B, C, A, A, B, C]
     *
     * 第 1 张票，第 2 张票和第 3 张票进行对坑，如果票都不同，则互相抵消掉
     * 第 4 张票，第 5 张票和第 6 张票进行对坑，如果有部分相同，则累计增加他们的可抵消票数，[A, 2] 和 [B, 1]
     * 接着将 [A, 2] 和 [B, 1] 与第 7 张票进行对坑，如果票都没匹配上，则互相抵消掉，变成 [A, 1] 和 [B, 0]
     *
     * 如果至多选 n 个代表，那他们的票数至少要超过 1 / (n + 1) 的票数。
     */
    public List<Integer> majorityElement1(int[] nums) {
        int choose1 = nums[0], count1 = 0;
        int choose2 = nums[0], count2 = 0;
        for (int num : nums) {
            if (choose1 == num) {
                count1++;
                continue;
            }
            if (choose2 == num) {
                count2++;
                continue;
            }
            if (count1 == 0) {
                choose1 = num;
                count1++;
                continue;
            }
            if (count2 == 0) {
                choose2 = num;
                count2++;
                continue;
            }
            count1--;
            count2--;
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (choose1 == num) {
                count1++;
            } else if (choose2 == num) {
                count2++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        if (count1 > nums.length / 3) {
            ans.add(choose1);
        }
        if (count2 > nums.length / 3) {
            ans.add(choose2);
        }
        return ans;
    }

}
