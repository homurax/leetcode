/**
 * 131. Palindrome Partitioning
 *
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 16
 * 2. s contains only lowercase English letters.
 */
public class PalindromePartitioning {

    List<List<String>> result = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int[][] flag;
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        flag = new int[n][n];
        dfs(s, 0);
        return result;
    }

    private void dfs(String s, int i) {
        if (i == n) {
            result.add(new ArrayList<>(ans));
            return;
        }
        for (int j = i; j < n; j++) {
            if (isPalindrome(s, i, j) == 1) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    private int isPalindrome(String s, int i, int j) {
        if (flag[i][j] != 0) {
            return flag[i][j];
        }
        if (i >= j) {
            flag[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            flag[i][j] = isPalindrome(s, i + 1, j - 1);
        } else {
            flag[i][j] = -1;
        }
        return flag[i][j];
    }

}
