/**
 * 2517. Maximum Tastiness of Candy Basket
 *
 *
 * You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
 *
 * The store sells baskets of k distinct candies. The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
 *
 * Return the maximum tastiness of a candy basket.
 *
 *
 *
 * Example 1:
 *
 * Input: price = [13,5,1,8,21,2], k = 3
 * Output: 8
 * Explanation: Choose the candies with the prices [13,5,21].
 * The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
 * It can be proven that 8 is the maximum tastiness that can be achieved.
 *
 *
 * Example 2:
 *
 * Input: price = [1,3,1], k = 2
 * Output: 2
 * Explanation: Choose the candies with the prices [1,3].
 * The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
 * It can be proven that 2 is the maximum tastiness that can be achieved.
 *
 *
 * Example 3:
 *
 * Input: price = [7,7,7,7], k = 2
 * Output: 0
 * Explanation: Choosing any two distinct candies from the candies we have will result in a tastiness of 0.
 *
 *
 * Constraints:
 *
 * 2 <= k <= price.length <= 10^5
 * 1 <= price[i] <= 10^9
 */
public class MaximumTastinessOfCandyBasket {

    // f(t) 表示甜蜜度 为 t 时最多可以选多少种糖果
    // f(ans) >= k && f(ans + 1) < k
    // 上界可以取值为 ⌊(max(price) - min(price)) / (k - 1)⌋ = (max(price) - min(price)) / (k - 1) + 1
    // 甜蜜度最小值不会超过平均值
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int l = 0;
        int r = (price[price.length - 1] - price[0]) / (k - 1) + 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (f(price, mid) >= k) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int f(int[] price, int t) {
        int cnt = 1;
        int prev = price[0];
        for (int p : price) {
            if (p >= prev + t) {
                cnt++;
                prev = p;
            }
        }
        return cnt;
    }

}
