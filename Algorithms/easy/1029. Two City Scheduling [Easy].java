/**
 * 1029. Two City Scheduling
 *
 * There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].
 *
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 *
 *
 *
 * Example 1:
 *
 * Input: [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 *
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 *
 *
 * Note:
 *
 * 1 <= costs.length <= 100
 * It is guaranteed that costs.length is even.
 * 1 <= costs[i][0], costs[i][1] <= 1000
 */
public class TwoCityScheduling {


    /**
     * 使用大顶堆
     * costs[i][0] - pq.poll() => costs[i][0] - costs[i][0] + costs[i][1]
     *                         => costs[i][1]
     */
    public int twoCitySchedCost(int[][] costs) {

        int sum = 0;
        int length = costs.length;
        for (int i = 0; i < length; i++) {
            sum += costs[i][0];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(50, Collections.reverseOrder());
        for (int i = 0; i < length; i++) {
            pq.offer(costs[i][0] - costs[i][1]);
        }

        int n = length / 2;
        while (n > 0) {
            sum += -pq.poll();
            n--;
        }

        return sum;
    }


    /**
     * 先都取较小的 再纠正
     * 当left偏多时 依次选取 right - left 最小的组合
     * 修正sum、left、right的值
     * 当right偏多时 反过来即可
     */
    public int twoCitySchedCost2(int[][] costs) {

        int n = costs.length;
        int sum = 0, left = 0, right = 0;
        int[] match = new int[n];
        for (int i = 0; i < n; i++) {
            if (costs[i][0] > costs[i][1]) {
                match[i] = 1;
                sum += costs[i][1];
                right++;
            } else {
                match[i] = 0;
                sum += costs[i][0];
                left++;
            }
        }
        while (left > right) {
            int chosen = -1, min = 1001;
            for (int i = 0; i < n; i++) {
                if (match[i] == 0 && costs[i][1] >= costs[i][0] && costs[i][1] - costs[i][0] < min) {
                    chosen = i;
                    min = costs[i][1] - costs[i][0];
                }
            }
            match[chosen] = 1;
            sum += min;
            left--;
            right++;
        }
        while (right > left) {
            int chosen = -1, min = 1001;
            for (int i = 0; i < n; i++) {
                if (match[i] == 1 && costs[i][0] >= costs[i][1] && costs[i][0] - costs[i][1] < min) {
                    chosen = i;
                    min = costs[i][0] - costs[i][1];
                }
            }
            match[chosen] = 0;
            sum += min;
            left++;
            right--;
        }
        return sum;
    }
}
