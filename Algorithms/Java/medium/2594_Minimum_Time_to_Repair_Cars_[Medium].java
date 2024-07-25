/**
 * 2594. Minimum Time to Repair Cars
 *
 *
 * You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.
 *
 * You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.
 *
 * Return the minimum time taken to repair all the cars.
 *
 * Note: All the mechanics can repair the cars simultaneously.
 *
 *
 *
 * Example 1:
 *
 * Input: ranks = [4,2,3,1], cars = 10
 * Output: 16
 * Explanation:
 * - The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
 * - The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
 * - The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
 * - The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
 * It can be proved that the cars cannot be repaired in less than 16 minutes.
 *
 *
 * Example 2:
 *
 * Input: ranks = [5,1,8], cars = 6
 * Output: 16
 * Explanation:
 * - The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
 * - The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
 * - The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
 * It can be proved that the cars cannot be repaired in less than 16 minutes.
 *
 *
 * Constraints:
 *
 * 1 <= ranks.length <= 10^5
 * 1 <= ranks[i] <= 100
 * 1 <= cars <= 10^6
 */
public class MinimumTimeToRepairCars {

    // 假设修成总用时为 t
    // 对修车时间 t 进行二分, r * n^2 <= t, 对于每一个 ranks[i] 解的修车数量 n 求和与 cars 作比较
    // 上界为修成最快的人修完所有车所用时间 min(ranks) * cars^2
    public long repairCars(int[] ranks, int cars) {
        int[] cnt = new int[101];
        int min = Integer.MAX_VALUE;
        for (int rank : ranks) {
            cnt[rank]++;
            min = Math.min(min, rank);
        }
        long left = 0, right = (long) min * cars * cars;
        while (left + 1 < right) {
            long mid = (left + right) >> 1;
            long sum = 0;
            for (int rank = min; rank <= 100 && sum < cars; rank++) {
                sum += (long) Math.sqrt(mid / rank) * cnt[rank];
            }
            if (sum >= cars) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

}
