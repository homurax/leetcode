/**
 * 1733. Minimum Number of People to Teach
 *
 *
 * On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.
 *
 * You are given an integer n, an array languages, and an array friendships where:
 *
 * There are n languages numbered 1 through n,
 * languages[i] is the set of languages the i user knows, and
 * friendships[i] = [u, vi] denotes a friendship between the users u and vi.
 * You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.
 *
 * Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
 *
 *
 * Example 1:
 *
 * Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
 * Output: 1
 * Explanation: You can either teach user 1 the second language or user 2 the first language.
 * Example 2:
 *
 * Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
 * Output: 2
 * Explanation: Teach the third language to users 1 and 3, yielding two users to teach.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 500
 * languages.length == m
 * 1 <= m <= 500
 * 1 <= languages[i].length <= n
 * 1 <= languages[i][j] <= n
 * 1 <= ui < vi <= languages.length
 * 1 <= friendships.length <= 500
 * All tuples (ui, vi) are unique
 * languages[i] contains only unique values
 */
public class MinimumNumberOfPeopleToTeach {

    // 找到需要学习语言的人，考虑学习所有不会的语言
    // 贪心选择需要学习人数最少的
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        boolean[][] lan = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j : languages[i]) {
                lan[i][j - 1] = true;
            }
        }
        Set<Integer> users = new HashSet<>();
        for (int[] friendship : friendships) {
            int u = friendship[0] - 1;
            int v = friendship[1] - 1;
            boolean flag = false;
            for (int i = 0; i < n && !flag; i++) {
                if (lan[u][i] && lan[v][i]) {
                    flag = true;
                }
            }
            if (!flag) {
                users.add(u);
                users.add(v);
            }
        }
        int[] cnt = new int[n];
        for (int i : users) {
            for (int j = 0; j < n; j++) {
                if (!lan[i][j]) {
                    cnt[j]++;
                }/**/
            }
        }
        return Arrays.stream(cnt).min().getAsInt();
    }

}
