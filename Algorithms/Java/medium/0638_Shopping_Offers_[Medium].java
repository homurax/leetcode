/**
 * 638. Shopping Offers
 *
 *
 * In LeetCode Store, there are n items to sell. Each item has a price. However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.
 *
 * You are given an integer array price where price[i] is the price of the ith item, and an integer array needs where needs[i] is the number of pieces of the ith item you want to buy.
 *
 * You are also given an array special where special[i] is of size n + 1 where special[i][j] is the number of pieces of the jth item in the ith offer and special[i][n] (i.e., the last integer in the array) is the price of the ith offer.
 *
 * Return the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers. You are not allowed to buy more items than you want, even if that would lower the overall price. You could use any of the special offers as many times as you want.
 *
 *
 *
 * Example 1:
 *
 * Input: price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
 * Output: 14
 * Explanation: There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
 * In special offer 1, you can pay $5 for 3A and 0B
 * In special offer 2, you can pay $10 for 1A and 2B.
 * You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
 *
 * Example 2:
 *
 * Input: price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
 * Output: 11
 * Explanation: The price of A is $2, and $3 for B, $4 for C.
 * You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.
 * You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.
 * You cannot add more items, though only $9 for 2A ,2B and 1C.
 *
 *
 * Constraints:
 *
 * 1. n == price.length
 * 2. n == needs.length
 * 3. 1 <= n <= 6
 * 4. 0 <= price[i] <= 10
 * 5. 0 <= needs[i] <= 10
 * 6. 1 <= special.length <= 100
 * 7. special[i].length == n + 1
 * 8. 0 <= special[i][j] <= 50
 */
public class ShoppingOffers {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs, 0);
    }

    public int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int start) {
        int min = 0, n = needs.size();
        for (int i = 0; i < n; i++) {
            min += price.get(i) * needs.get(i);
        }
        for (int i = start; i < special.size(); i++) {
            List<Integer> item = special.get(i);
            if (isValid(item, needs)) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    temp.add(needs.get(j) - item.get(j));
                }
                min = Math.min(min, dfs(price, special, temp, i) + item.get(n));
            }
        }
        return min;
    }

    private boolean isValid(List<Integer> item, List<Integer> needs) {
        for (int i = 0; i < needs.size(); i++) {
            if (item.get(i) > needs.get(i)) {
                return false;
            }
        }
        return true;
    }





    /**
     * 最多 11^6 = 1771561 中购买情况
     *
     * dp[needs] = min(special(i).price + dp[needs - special(i).needs]), i in special
     *
     */
    private int n;
    // key 可以优化为状态映射
    private Map<List<Integer>, Integer> memo = new HashMap<>();

    public int shoppingOffer2(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        n = price.size();
        special.removeIf(item -> {
            int count = 0, total = 0;
            for (int i = 0; i < n; i++) {
                count += item.get(i);
                total += item.get(i) * price.get(i);
            }
            return count == 0 || total <= item.get(n);
        });
        return dfs(price, special, needs);
    }

    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (!memo.containsKey(needs)) {
            int min = 0;
            for (int i = 0; i < n; i++) {
                min += needs.get(i) * price.get(i);
            }
            for (List<Integer> item : special) {
                int specialPrice = item.get(n);
                List<Integer> next = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if (item.get(i) > needs.get(i)) {
                        break;
                    }
                    next.add(needs.get(i) - item.get(i));
                }
                if (next.size() == n) {
                    min = Math.min(min, dfs(price, special, next) + specialPrice);
                }
            }
            memo.put(needs, min);
        }
        return memo.get(needs);
    }

}
