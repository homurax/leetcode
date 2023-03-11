/**
 * 306. Additive Number
 *
 *
 * An additive number is a string whose digits can form an additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits, return true if it is an additive number or false otherwise.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: "112358"
 * Output: true
 * Explanation:
 * The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 *
 *
 * Example 2:
 *
 * Input: "199100199"
 * Output: true
 * Explanation:
 * The additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 *
 *
 * Constraints:
 *
 * 1. 1 <= num.length <= 35
 * 2. num consists only of digits.
 */
public class AdditiveNumber {

    private List<List<Integer>> list = new ArrayList<>();
    private String num;
    private int n;

    public boolean isAdditiveNumber(String num) {
        this.num = num;
        this.n = num.length();
        return dfs(0);
    }

    // 分割点
    private boolean dfs(int idx) {
        int size = list.size();
        if (idx == n) {
            return size >= 3;
        }
        int limit = num.charAt(idx) == '0' ? idx + 1 : n;
        List<Integer> cur = new ArrayList<>();
        for (int i = idx; i < limit; i++) {
            cur.add(0, num.charAt(i) - '0');
            if (size < 2 || check(list.get(size - 2), list.get(size - 1), cur)) {
                list.add(cur);
                if (dfs(i + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
        }
        return false;
    }


    // a + b = c
    // 使用逆序存储数值
    private boolean check(List<Integer> a, List<Integer> b, List<Integer> c) {
        List<Integer> ans = new ArrayList<>();
        int t = 0;
        for (int i = 0; i < a.size() || i < b.size(); i++) {
            if (i < a.size()) {
                t += a.get(i);
            }
            if (i < b.size()) {
                t += b.get(i);
            }
            ans.add(t % 10);
            t /= 10;
        }
        if (t > 0) {
            ans.add(t);
        }
        boolean flag = c.size() == ans.size();
        for (int i = 0; i < c.size() && flag; i++) {
            if (c.get(i) != ans.get(i)) {
                flag = false;
            }
        }
        return flag;
    }

}
