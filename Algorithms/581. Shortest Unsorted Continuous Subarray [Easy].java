/**
 * 581. Shortest Unsorted Continuous Subarray
 *
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 */
public class ShortestUnsortedContinuousSubarray {


    /**
     * 可能超时
     */
    public int findUnsortedSubarray(int[] nums) {

        int startIndex = nums.length, endIndex = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    endIndex = Math.max(endIndex, j);
                    startIndex = Math.min(startIndex, i);
                }
            }
        }
        if (startIndex > endIndex) {
            return 0;
        }
        return endIndex - startIndex + 1;
    }

    /**
     * 排序后一次遍历
     */
    public int findUnsortedSubarray2(int[] nums) {

        int[] clone = nums.clone();
        Arrays.sort(clone);
        int start = clone.length, end = 0;

        for (int i = 0; i < clone.length; i++) {
            if (clone[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        if (start > end) {
            return 0;
        }
        return end - start + 1;
    }

    public int findUnsortedSubarray3(int[] nums) {

        LinkedList<Integer> deque = new LinkedList<>();
        int start = nums.length, end = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peek()] > nums[i]) {
                start = Math.min(start, deque.pop());
            }
            deque.push(i);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!deque.isEmpty() && nums[deque.peek()] < nums[i]) {
                end = Math.max(end, deque.pop());
            }
            deque.push(i);
        }
        if (start > end) {
            return 0;
        }
        return end - start + 1;
    }


    public int findUnsortedSubarray4(int[] nums) {

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                flag = true;
            }
            if (flag) {
                min = Math.min(min, nums[i]);
            }
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                flag = true;
            }
            if (flag) {
                max = Math.max(max, nums[i]);
            }
        }
        int start, end;
        for (start = 0; start < nums.length; start++) {
            if (min < nums[start]) {
                break;
            }
        }
        for (end = nums.length - 1; end >= 0; end--) {
            if (max > nums[end]) {
                break;
            }
        }
        return end - start < 0 ? 0 : end - start + 1;
    }


}
