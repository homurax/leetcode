/**
 * 2975. Maximum Square Area by Removing Fences From a Field
 *
 *
 * There is a large (m - 1) x (n - 1) rectangular field with corners at (1, 1) and (m, n) containing some horizontal and vertical fences given in arrays hFences and vFences respectively.
 *
 * Horizontal fences are from the coordinates (hFences[i], 1) to (hFences[i], n) and vertical fences are from the coordinates (1, vFences[i]) to (m, vFences[i]).
 *
 * Return the maximum area of a square field that can be formed by removing some fences (possibly none) or -1 if it is impossible to make a square field.
 *
 * Since the answer may be large, return it modulo 109 + 7.
 *
 * Note: The field is surrounded by two horizontal fences from the coordinates (1, 1) to (1, n) and (m, 1) to (m, n) and two vertical fences from the coordinates (1, 1) to (m, 1) and (1, n) to (m, n). These fences cannot be removed.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: m = 4, n = 3, hFences = [2,3], vFences = [2]
 * Output: 4
 * Explanation: Removing the horizontal fence at 2 and the vertical fence at 2 will give a square field of area 4.
 * Example 2:
 *
 *
 *
 * Input: m = 6, n = 7, hFences = [2], vFences = [4]
 * Output: -1
 * Explanation: It can be proved that there is no way to create a square field by removing fences.
 *
 *
 * Constraints:
 *
 * 3 <= m, n <= 10^9
 * 1 <= hFences.length, vFences.length <= 600
 * 1 < hFences[i] < m
 * 1 < vFences[i] < n
 * hFences and vFences are unique.
 */
public class MaximumSquareAreaByRemovingFencesFromAField {

    // 维护任意水平栅栏、垂直栅栏之间的距离, 差值交集的最大值就是最大边长
    // 手动添加外框边界
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> h = f(hFences, m);
        Set<Integer> v = f(vFences, n);
        int ans = 0;
        for (int x : h) {
            if (v.contains(x)) {
                ans = Math.max(ans, x);
            }
        }
        return ans > 0 ? (int) ((long) ans * ans % 1_000_000_007) : -1;
    }

    private Set<Integer> f(int[] a, int mx) {
        int len = a.length;
        a = Arrays.copyOf(a, len + 2);
        a[len] = 1;
        a[len + 1] = mx;
        Arrays.sort(a);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                set.add(a[j] - a[i]);
            }
        }
        return set;
    }

}
