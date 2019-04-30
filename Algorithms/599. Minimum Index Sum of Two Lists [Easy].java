/**
 * 599. Minimum Index Sum of Two Lists
 *
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
 *
 * You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.
 *
 * Example 1:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * Output: ["Shogun"]
 * Explanation: The only restaurant they both like is "Shogun".
 * Example 2:
 * Input:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 * Note:
 * The length of both lists will be in the range of [1, 1000].
 * The length of strings in both lists will be in the range of [1, 30].
 * The index is starting from 0 to the list length minus 1.
 * No duplicates in both lists.
 */
public class MinimumIndexSumOfTwoLists {


    /**
     * 一种思路是找到满足条件的餐厅及其索引和 然后从中选取最小索引合的餐厅
     *
     * 也可以反过来 从最小的索引和开始找是否有满足的餐厅 第一次被找到就是最小的索引和
     */
    public String[] findRestaurant(String[] list1, String[] list2) {

        int length1 = list1.length;
        int length2 = list2.length;
        int maxSumIndex = length1 + length2 - 2;
        List<String> ans = new ArrayList<>();
        for (int sum = 0; sum <= maxSumIndex; sum++) {

            for (int i = 0; i <= sum; i++) {
                int index2 = sum - i;
                if (i < length1 && index2 < length2 && list1[i].equals(list2[index2])) {
                    ans.add(list1[i]);
                }
            }
            if (!ans.isEmpty()) break;
        }

        return ans.toArray(new String[]{});
    }

    /**
     * 更快
     */
    public String[] findRestaurant2(String[] list1, String[] list2) {

        if (list1.length > list2.length)
            return findRestaurant2(list2, list1);

        List<String> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        int min = list1.length + list2.length;

        for (int i = 0; i < list2.length && i <= min; i++) {
            String restaurant = list2[i];
            if (map.containsKey(restaurant)) {
                int sum = i + map.get(restaurant);
                if (sum < min) {
                    ans.clear();
                    ans.add(restaurant);
                    min = sum;
                } else if (sum == min) {
                    ans.add(restaurant);
                }
            }
        }

        return ans.toArray(new String[]{});
    }

}
