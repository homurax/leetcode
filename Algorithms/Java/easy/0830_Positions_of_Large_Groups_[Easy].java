/**
 * 830. Positions of Large Groups
 *
 * In a string S of lowercase letters, these letters form consecutive groups of the same character.
 *
 * For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".
 *
 * Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.
 *
 * The final answer should be in lexicographic order.
 *
 *
 *
 * Example 1:
 *
 * Input: "abbxxxxzzy"
 * Output: [[3,6]]
 * Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
 *
 * Example 2:
 *
 * Input: "abc"
 * Output: []
 * Explanation: We have "a","b" and "c" but no large group.
 *
 * Example 3:
 *
 * Input: "abcdddeeeeaabbbcd"
 * Output: [[3,5],[6,9],[12,14]]
 *
 *
 * Note:  1 <= S.length <= 1000
 */
public class PositionsOfLargeGroups {


    /**
     * 考虑清楚边界条件即可
     */
    public List<List<Integer>> largeGroupPositions(String S) {

        int length = S.length();
        if (length < 3) {
            return Collections.emptyList();
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < length - 2; i++) {
            if (S.charAt(i) == S.charAt(i + 1) && S.charAt(i + 1) == S.charAt(i + 2)) {
                int start = i;
                int count = 3;
                while (i + count < length && S.charAt(i) == S.charAt(i + count)) {
                    count++;
                }
                int end = i + count - 1;
                List<Integer> temp = new ArrayList<>(2);
                temp.add(start);
                temp.add(end);
                ans.add(temp);
                i += count - 1;
            }
        }

        return ans;
    }


    /**
     * 遍历发现不连续时
     *      1. 判断当前检测[start, end]部分是否满足长度要求 满足则记录
     *      2. 更新start值
     */
    public List<List<Integer>> largeGroupPositions2(String S) {

        List<List<Integer>> ans = new ArrayList<>();

        int length = S.length();
        int start = 0;
        for (int end = 0; end < length; end++) {
            if (end == length - 1 || S.charAt(end) != S.charAt(end + 1)) {
                if (end - start + 1 >= 3) {
                    ans.add(Arrays.asList(start, end));
                }
                start = end + 1;
            }
        }

        return ans;
    }
}
