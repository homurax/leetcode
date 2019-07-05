/**
 * 1051. Height Checker
 *
 * Students are asked to stand in non-decreasing order of heights for an annual photo.
 *
 * Return the minimum number of students not standing in the right positions.  (This is the number of students that must move in order for all students to be standing in non-decreasing order of height.)
 *
 *
 *
 * Example 1:
 *
 * Input: [1,1,4,2,1,3]
 * Output: 3
 * Explanation:
 * Students with heights 4, 3 and the last 1 are not standing in the right positions.
 *
 *
 * Note:
 *
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 */
public class HeightChecker {

    /**
     * 统计有多少个元素不在自己该在的位置上
     */
    public int heightChecker(int[] heights) {

        int[] clone = heights.clone();
        Arrays.sort(heights);
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != clone[i]) ans++;
        }
        return ans;
    }

    /**
     * 打表获得一个元素的下标范围
     */
    public int heightChecker2(int[] heights) {

        int[] count = new int[101];
        for (int num : heights)
            count[num]++;

        int temp = 0;
        int[] lower = new int[101];
        int[] higher = new int[101];
        for (int num = 1; num < 101; num++) {
            if (count[num] != 0) {
                lower[num] = temp;
                temp += count[num];
                higher[num] = temp - 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            int num = heights[i];
            if (i < lower[num] || i > higher[num])
                ans++;
        }

        return ans;
    }
}
