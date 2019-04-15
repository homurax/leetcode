/**
 * 349. Intersection of Two Arrays
 *
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 *
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class IntersectionOfTwoArrays {

    /**
     * 有点慢 而且需要实例化两个Set
     */
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            set2.add(i);
        }
        set1.retainAll(set2);

        int[] ans = new int[set1.size()];
        int index = 0;
        for (Integer i : set1) {
            ans[index++] = i;
        }
        return ans;
    }


    /**
     * 先使用较少元素的数组打表
     * 在遍历另一个数组获得答案
     */
    public int[] intersection2(int[] nums1, int[] nums2) {

        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 <= length2) {

            int max = 0;
            for (int i : nums1) {
                max = Math.max(max, i);
            }

            boolean[] booleans = new boolean[max + 1];
            for (int i : nums1) {
                booleans[i] = true;
            }

            int index = 0;
            int[] ans = new int[max + 1];
            for (int i : nums2) {
                if (i <= max && booleans[i]) {
                    booleans[i] = false;
                    ans[index++] = i;
                }
            }
            return Arrays.copyOf(ans, index);
        } else {
            return intersection2(nums2, nums1);
        }
    }

}
