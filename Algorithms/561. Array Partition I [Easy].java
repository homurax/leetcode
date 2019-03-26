/**
 * 561. Array Partition I
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 *
 * Example 1:
 * Input: [1,4,3,2]
 *
 * Output: 4
 * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
 * Note:
 * n is a positive integer, which is in the range of [1, 10000].
 * All the integers in the array will be in the range of [-10000, 10000].
 *
 */
public class ArrayPartitionI {

    /**
     * 排序后遍历直接求和
     * 各种语言类似解法等于在变相比较各个语言的快排算法
     */
    public int arrayPairSum(int[] nums) {

        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }


    /**
     * step 1 变相排序
     *  数组buckets的下标减10000即为排序后的nums中的元素
     *  此下标对应的数组中的值即为该元素在nums中出现的次数
     *
     * step 2 计算求和
     *  count为出现次数
     *  (i - 10000)为nums元素 称之为为target
     *  flag初始值为1
     *
     *  1) count为奇数时
     *      flag为1
     *          (count + flag) >> 1 => count / 2 + 1
     *      flag为0
     *          (count + flag) >> 1 => count / 2
     *      count & 1 => 1
     *      flag = flag ^ (count & 1) => 0
     *      下次为偶数时flag不会被修改 下次为奇数时会被重置为1
     *
     *  2) count为偶数 count/2 * target 即可 此时flag的值对运算没有影响 flag本身也不会被修改
     *      (count + flag) >> 1 <=> count / 2
     *      count & 1 => 0
     *      flag = flag ^ (count & 1) => 1
     */
    public int arrayPairSum2(int[] nums) {

        int[] buckets  = new int[20001];

        for (int i = 0; i < nums.length; i++) { // step 1
            ++buckets[nums[i] + 10000];
        }

        int flag = 1, sum = 0, count = 0;
        for (int i = 0; i < buckets.length; i++) { // step 2
            if ((count = buckets[i]) > 0) {
                sum += ((count + flag) >> 1) * (i - 10000);
                flag = flag ^ (count & 1);
            }
        }

       return sum;
    }
}
