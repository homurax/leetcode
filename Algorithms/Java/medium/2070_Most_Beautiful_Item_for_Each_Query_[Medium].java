/**
 * 2070. Most Beautiful Item for Each Query
 *
 *
 * You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes the price and beauty of an item respectively.
 *
 * You are also given a 0-indexed integer array queries. For each queries[j], you want to determine the maximum beauty of an item whose price is less than or equal to queries[j]. If no such item exists, then the answer to this query is 0.
 *
 * Return an array answer of the same length as queries where answer[j] is the answer to the jth query.
 *
 *
 *
 * Example 1:
 *
 * Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
 * Output: [2,4,5,5,6,6]
 * Explanation:
 * - For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
 * - For queries[1]=2, the items which can be considered are [1,2] and [2,4].
 *   The maximum beauty among them is 4.
 * - For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
 *   The maximum beauty among them is 5.
 * - For queries[4]=5 and queries[5]=6, all items can be considered.
 *   Hence, the answer for them is the maximum beauty of all items, i.e., 6.
 *
 * Example 2:
 *
 * Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
 * Output: [4]
 * Explanation:
 * The price of every item is equal to 1, so we choose the item with the maximum beauty 4.
 * Note that multiple items can have the same price and/or beauty.
 *
 * Example 3:
 *
 * Input: items = [[10,1000]], queries = [5]
 * Output: [0]
 * Explanation:
 * No item has a price less than or equal to 5, so no item can be chosen.
 * Hence, the answer to the query is 0.
 *
 *
 * Constraints:
 *
 * 1. 1 <= items.length, queries.length <= 10^5
 * 2. items[i].length == 2
 * 3. 1 <= pricei, beautyi, queries[j] <= 10^9
 */
public class MostBeautifulItemForEachQuery {

    // 还是使用 TreeMap 动态更新
    // higherPrice 的 beauty 如果小于当前的 beauty，就说明这组 KV 是可以被移除的
    public int[] maximumBeauty(int[][] items, int[] queries) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] item : items) {
            int price = item[0], beauty = item[1];
            Integer lowerPrice = map.floorKey(price);
            if (lowerPrice == null || map.get(lowerPrice) < beauty) {
                Integer higherPrice = map.ceilingKey(price);
                while (higherPrice != null && map.get(higherPrice) < beauty) {
                    map.remove(higherPrice);
                    higherPrice = map.ceilingKey(price);
                }
                map.put(price, beauty);
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer key = map.floorKey(queries[i]);
            if (key != null) {
                ans[i] = map.get(key);
            }
        }
        return ans;
    }

    // 排序后更新 items 来当作辅助数组 + 二分
    public int[] maximumBeauty3(int[][] items, int[] queries) {
        Arrays.sort(items, Comparator.comparingInt(a -> a[0]));
        int n = items.length;
        for (int i = 1; i < n; i++) {
            items[i][1] = Math.max(items[i][1], items[i - 1][1]);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = 0, r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (items[mid][0] > queries[i]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] = (l == 0) ? 0 : items[l - 1][1];
        }
        return ans;
    }

    // 先对 items 排序
    public int[] maximumBeauty2(int[][] items, int[] queries) {
        Arrays.sort(items, Comparator.comparingInt(a -> a[0]));
        int max = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] item : items) {
            max = Math.max(max, item[1]);
            map.put(item[0], max);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer key = map.floorKey(queries[i]);
            if (key != null) {
                ans[i] = map.get(key);
            }
        }
        return ans;
    }

    // 需要对 TreeMap 再次处理
    public int[] maximumBeauty1(int[][] items, int[] queries) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] item : items) {
            if (!map.containsKey(item[0]) || map.get(item[0]) < item[1]) {
                map.put(item[0], item[1]);
            }
        }
        int max = 0;
        for (Integer key : map.keySet()) {
            if (max > map.get(key)) {
                map.put(key, max);
            } else {
                max = map.get(key);
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            Integer key = map.floorKey(queries[i]);
            if (key != null) {
                ans[i] = map.get(key);
            }
        }
        return ans;
    }

}
