/**
 * 475. Heaters
 *
 *
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
 *
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 *
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.
 *
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 *
 *
 *
 * Example 1:
 *
 * Input: houses = [1,2,3], heaters = [2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * Example 2:
 *
 * Input: houses = [1,2,3,4], heaters = [1,4]
 * Output: 1
 * Explanation: The two heaters were placed at positions 1 and 4. We need to use a radius 1 standard, then all the houses can be warmed.
 * Example 3:
 *
 * Input: houses = [1,5], heaters = [2]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= houses.length, heaters.length <= 3 * 10^4
 * 1 <= houses[i], heaters[i] <= 10^9
 */
public class Heaters {

    // 二分供暖范围
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int l = 0, r = (int) 1e9;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(houses, heaters, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean check(int[] houses, int[] heaters, int x) {
        int n = houses.length, m = heaters.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && houses[i] > heaters[j] + x) {
                j++;
            }
            if (j < m && heaters[j] - x <= houses[i] && houses[i] <= heaters[j] + x) {
                continue;
            }
            return false;
        }
        return true;
    }

}
