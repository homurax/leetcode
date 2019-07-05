/**
 * 849. Maximize Distance to Closest Person
 *
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 *
 * There is at least one empty seat, and at least one person sitting.
 *
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 * Return that maximum distance to closest person.
 *
 * Example 1:
 *
 * Input: [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 *
 * Input: [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat, the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 * Note:
 *
 * 1 <= seats.length <= 20000
 * seats contains only 0s or 1s, at least one 0, and at least one 1.
 */
public class MaximizeDistanceToClosestPerson {


    public int maxDistToClosest(int[] seats) {

        List<Integer> indexs = new ArrayList<>();
        int length = seats.length;
        for (int i = 0; i < length; i++) {
            if (seats[i] == 1)
                indexs.add(i);
        }

        int ans = Math.max(indexs.get(0), length - 1 - indexs.get(indexs.size() - 1));
        int low = indexs.get(0), high;
        for (int i = 1; i < indexs.size(); i++) {
            high = indexs.get(i);
            int mid = (low + high) >> 1;
            ans = Math.max(ans, Math.min(mid - low, high - mid));
            low = high;
        }

        return ans;
    }


    /**
     * 没有必要实例化一个集合储存1的下标
     */
    public int maxDistToClosest2(int[] seats) {

        int low = -1, high = -1;
        int ans = 0;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (low == -1) {
                    low = high = ans = i;
                    continue;
                }
                high = i;
                int mid = (low + high) >> 1;
                ans = Math.max(ans, mid - low);
                low = high;
            }
        }
        ans = Math.max(ans, seats.length - 1 - high);
        return ans;
    }


    /**
     * 中间有K个连续空座位 最大距离即为 (K + 1) / 2
     */
    public int maxDistToClosest3(int[] seats) {

        int ans = 0, K = 0;
        int low = -1, high = -1;
        int length = seats.length;

        for (int i = 0; i < length; i++) {
            if (seats[i] == 1) {
                K = 0;
                if (low == -1) {
                    low = i;
                }
                high = i;
            } else {
                K++;
                ans = Math.max(ans, (K + 1) / 2);
            }
        }

        ans = Math.max(ans, low);
        ans = Math.max(ans, length - 1 - high);

        return ans;
    }

}
