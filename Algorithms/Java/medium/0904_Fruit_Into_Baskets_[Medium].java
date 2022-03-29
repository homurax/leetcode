/**
 * 904. Fruit Into Baskets
 *
 *
 * You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 *
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 *
 * You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 *
 *
 *
 * Example 1:
 *
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 *
 * Example 2:
 *
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 *
 * Example 3:
 *
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 *
 *
 * Constraints:
 *
 * 1. 1 <= fruits.length <= 10^5
 * 2. 0 <= fruits[i] < fruits.length
 */
public class FruitIntoBaskets {

    public int totalFruit(int[] fruits) {
        int max = 0, l = 0, r = -1;
        int n = fruits.length;
        for (int i = 1; i < n; i++) {
            if (fruits[i] != fruits[i - 1]) { // 出现新type
                if (r >= 0 && fruits[i] != fruits[r]) { // 已经是第三种type了
                    max = Math.max(max, i - l);
                    l = r + 1;
                }
                r = i - 1;
            }
        }
        return Math.max(max, n - l);
    }

    // 滑窗 基于 freq[] 维护 type
    public int totalFruit1(int[] tree) {
        int l = 0, type = 0, max = 0;
        int n = tree.length;
        int[] freq = new int[n];
        for (int r = 0; r < tree.length; r++) {
            if (++freq[tree[r]] == 1) {
                type++;
            }
            while (type > 2) {
                if (--freq[tree[l++]] == 0) {
                    type--;
                }
            }
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

}
