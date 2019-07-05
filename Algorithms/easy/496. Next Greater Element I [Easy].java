/**
 * 496. Next Greater Element I
 *
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 *
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 *
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 *     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 *     For number 1 in the first array, the next greater number for it in the second array is 3.
 *     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * Example 2:
 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * Output: [3,-1]
 * Explanation:
 *     For number 2 in the first array, the next greater number for it in the second array is 3.
 *     For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * Note:
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 */
public class NextGreaterElementI {


    /**
     * 遍历查找
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int length1 = nums1.length;
        int length2 = nums2.length;
        int count = 0;

        int[] ans = new int[length1];
        Map<Integer, Integer> valueIndexMap = new HashMap<>(length1);

        for (int i = 0; i < length1; i++) {
            valueIndexMap.put(nums1[i], i);
        }

        for (int i = 0; i < length2; i++) {
            int value = nums2[i];
            if (valueIndexMap.containsKey(value)) {
                int j;
                boolean find = false;
                for (j = i + 1; j < length2; j++) {
                    if (nums2[j] > value) {
                        find = true;
                        break;
                    }
                }
                ans[valueIndexMap.get(value)] = find ? nums2[j] : -1;
                if (++count == length1) {
                    break;
                }
            }
        }

        return ans;
    }


    /**
     * key   => 数组中某个元素
     * value => 下一个比key大的元素
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {

        int length = nums1.length;

        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> deque = new LinkedList<>();

        for (int num : nums2) {
            while (!deque.isEmpty() && deque.peek() < num) {
                map.put(deque.pop(), num);
            }
            deque.offerFirst(num);
        }

        for (int i = 0; i < length; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }

        return nums1;
    }
}
