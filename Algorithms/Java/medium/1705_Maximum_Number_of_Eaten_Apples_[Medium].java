/**
 * 1705. Maximum Number of Eaten Apples
 *
 *
 * There is a special kind of apple tree that grows apples every day for n days. On the ith day, the tree grows apples[i] apples that will rot after days[i] days, that is on day i + days[i] the apples will be rotten and cannot be eaten. On some days, the apple tree does not grow any apples, which are denoted by apples[i] == 0 and days[i] == 0.
 *
 * You decided to eat at most one apple a day (to keep the doctors away). Note that you can keep eating after the first n days.
 *
 * Given two integer arrays days and apples of length n, return the maximum number of apples you can eat.
 *
 *
 *
 * Example 1:
 *
 * Input: apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * Output: 7
 * Explanation: You can eat 7 apples:
 * - On the first day, you eat an apple that grew on the first day.
 * - On the second day, you eat an apple that grew on the second day.
 * - On the third day, you eat an apple that grew on the second day. After this day, the apples that grew on the third day rot.
 * - On the fourth to the seventh days, you eat apples that grew on the fourth day.
 *
 * Example 2:
 *
 * Input: apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * Output: 5
 * Explanation: You can eat 5 apples:
 * - On the first to the third day you eat apples that grew on the first day.
 * - Do nothing on the fouth and fifth days.
 * - On the sixth and seventh days you eat apples that grew on the sixth day.
 *
 *
 * Constraints:
 *
 * 1. apples.length == n
 * 2. days.length == n
 * 3. 1 <= n <= 2 * 10^4
 * 4. 0 <= apples[i], days[i] <= 2 * 10^4
 * 5. days[i] = 0 if and only if apples[i] = 0.
 */
public class MaximumNumberOfEatenApples {

    // 有得选的时候优先吃保质期短的
    public static int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int ans = 0;
        for (int i = 0; i < apples.length || pq.size() > 0; i++) {
            while (!pq.isEmpty()) {
                int[] apple = pq.peek();
                if (apple[1] <= i) {
                    pq.poll();
                } else {
                    break;
                }
            }
            if (i < apples.length && apples[i] > 0) {
                pq.add(new int[]{apples[i], days[i] + i});
            }
            int[] eat = pq.peek();
            if (eat != null && eat[0] > 0) {
                ans++;
                if (--eat[0] == 0) {
                    pq.poll();
                }
            }
        }
        return ans;
    }

    public int eatenApples2(int[] apples, int[] days) {
        int n = apples.length;
        int[] record = new int[n + 20001];
        int minExpired = 0, lastDay = n;
        int ans = 0;

        for (int i = 0; i < lastDay; i++) {
            if (i < n) {
                int expired = days[i] + i;
                record[expired] = apples[i];
                minExpired = Math.min(minExpired, expired);
                lastDay = Math.max(expired, lastDay);
            }
            while ((minExpired <= i || record[minExpired] == 0) && minExpired < lastDay) {
                minExpired++;
            }
            if (record[minExpired] != 0) {
                record[minExpired]--;
                ans++;
            }
        }
        return ans;
    }

}
