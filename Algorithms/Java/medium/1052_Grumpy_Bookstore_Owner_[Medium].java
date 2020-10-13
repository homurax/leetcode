/**
 * 1052. Grumpy Bookstore Owner
 *
 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the day.
 *
 *
 *
 * Example 1:
 *
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 *
 * Note:
 *
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 */
public class GrumpyBookstoreOwner {

    // 记录没有补偿之和 + 最大补偿值
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ans = 0, curCompensate = 0, maxCompensate = 0;
        for (int cur = 0, left = 0; cur < customers.length; cur++) {
            if (grumpy[cur] == 1) {
                curCompensate += customers[cur];
            } else {
                ans += customers[cur];
            }
            if (cur - left + 1 > X) {
                if (grumpy[left] == 1) {
                    curCompensate -= customers[left];
                }
                left++;
            }
            maxCompensate = Math.max(maxCompensate, curCompensate);
        }
        return ans + maxCompensate;
    }

}
