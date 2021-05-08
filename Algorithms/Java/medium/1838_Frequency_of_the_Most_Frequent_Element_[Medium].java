/**
 * 1838. Frequency of the Most Frequent Element
 *
 *
 * The frequency of an element is the number of times it occurs in an array.
 *
 * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
 *
 * Return the maximum possible frequency of an element after performing at most k operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,4], k = 5
 * Output: 3
 * Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
 * 4 has a frequency of 3.
 *
 * Example 2:
 *
 * Input: nums = [1,4,8,13], k = 5
 * Output: 2
 * Explanation: There are multiple optimal solutions:
 * - Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
 * - Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
 * - Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
 *
 * Example 3:
 *
 * Input: nums = [3,9,6], k = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^5
 */
public class FrequencyOfTheMostFrequentElement {

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int frequency = 1, left = 0, increment = 0;
        for (int right = 1; right < nums.length; right++) {
            increment += (nums[right] - nums[right - 1]) * (right - left);
            while (increment > k) {
                increment -= nums[right] - nums[left++];
            }
            frequency = Math.max(frequency, right - left + 1);
        }
        return frequency;
    }

    public int maxFrequency1(int[] nums, int k) {
        int[] count = new int[100001];
        for (int num : nums) {
            count[num]++;
        }
        int frequency = 1, preNum = -1, preCount = 0, increment = 0, left = 0;
        for (int num = 0; num < count.length; num++) {
            if (count[num] == 0) {
                continue;
            }
            if (preNum == -1) {
                preNum = num;
                preCount = count[num];
                frequency = count[num];
                left = num;
            } else {
                increment += (num - preNum) * preCount;
                preCount += count[num];
                preNum = num;
                while (increment > k) {
                    if (count[left] > 0) {
                        increment -= num - left;
                        count[left]--;
                        preCount--;
                    } else {
                        left++;
                    }
                }
                frequency = Math.max(frequency, preCount);
            }
        }
        return frequency;
    }

}
