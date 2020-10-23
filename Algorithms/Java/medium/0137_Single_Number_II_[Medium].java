/**
 * 137. Single Number II
 *
 *
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 */
public class SingleNumberII {

    /**
     * 需要设计一种状态转换方式，当同一个数出现 3 次时自动抵消为 0
     *
     * 0 ^ x = x
     * x ^ x = 0
     * x & ~x = 0
     * x & ~0 = x
     */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int x : nums) {
            // a = 0, b = 0
            // x 第一次出现: a = x, b = 0
            // x 第二次出现: a = 0, b = x
            // x 第三次出现: a = 0, b = 0
            a = ~b & (a ^ x);
            b = ~a & (b ^ x);
        }
        return a;
    }

    /**
     * 统计全部数字在二进制下每一位 1 的个数
     * 出现 3 次的数字，各二进制位出现的次数都是 3 的倍数
     * 对 3 求余，结果则为只出现一次的数字。
     */
    public int singleNumber2(int[] nums) {
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>>= 1;
            }
        }
        for (int i = 0; i < 32; i++) {
            count[i] %= 3;
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans <<= 1;
            ans |= count[31 - i];
        }
        return ans;
    }




    // 用排序比用 Set 或者 Map 还快一点...
    public int singleNumber3(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                return nums[i];
            }
            if (i + 2 < nums.length && nums[i] != nums[i + 2]) {
                return nums[i];
            } else {
                i += 2;
            }
        }
        return -1;
    }

    public int singleNumber4(int[] nums) {
        Set<Long> set = new HashSet<>();
        long sumSet = 0, sumArray = 0;
        for (int n : nums) {
            sumArray += n;
            set.add((long) n);
        }
        for (Long s : set) {
            sumSet += s;
        }
        return (int) ((3 * sumSet - sumArray) / 2);
    }

    public int singleNumber5(int[] nums) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        for (int num : nums) {
            hashmap.merge(num, 1, Integer::sum);
        }
        for (int key : hashmap.keySet()) {
            if (hashmap.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

}
