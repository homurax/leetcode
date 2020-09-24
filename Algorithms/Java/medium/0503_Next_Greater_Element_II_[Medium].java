/**
 * 503. Next Greater Element II
 *
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * Example 1:
 * Input: [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Note: The length of given array won't exceed 10000.
 */
public class NextGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] doubledNums = new int[len * 2];
        for (int i = 0; i < len; i++) {
            doubledNums[i + len] = doubledNums[i] = nums[i];
        }
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            boolean find = false;
            for (int j = i + 1; j < i + len; j++) {
                if (doubledNums[j] > nums[i]) {
                    ans[i] = doubledNums[j];
                    find = true;
                    break;
                }
            }
            if (!find) {
                ans[i] = -1;
            }
        }
        return ans;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        int[] stack = new int[len];
        int idx = -1;

        // 正常扫描
        for (int i = 0; i < len; i++) {
            // 栈中nums的index对应的值从栈顶自上而下单调不降
            while (idx != -1 && nums[stack[idx]] < nums[i]) {
                ans[stack[idx--]] = nums[i];
            }
            stack[++idx] = i;
        }

        // 循环扫描
        for (int i = 0; i < len && idx != -1; i++) {
            // 栈顶自上而下 循环再次从0开始 相当于循环的那一次查找
            while (idx != -1 && nums[stack[idx]] < nums[i]) {
                ans[stack[idx--]] = nums[i];
            }
        }

        while (idx >= 0) {
            ans[stack[idx--]] = -1;
        }
        return ans;
    }

}
