/**
 * 990. Satisfiability of Equality Equations
 *
 *
 * Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.
 *
 * Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
 *
 * Example 2:
 *
 * Input: ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 *
 * Example 3:
 *
 * Input: ["a==b","b==c","a==c"]
 * Output: true
 *
 * Example 4:
 *
 * Input: ["a==b","b!=c","c==a"]
 * Output: false
 *
 * Example 5:
 *
 * Input: ["c==c","b==d","x!=z"]
 * Output: true
 *
 *
 * Note:
 *
 * 1. 1 <= equations.length <= 500
 * 2. equations[i].length == 4
 * 3. equations[i][0] and equations[i][3] are lowercase letters
 * 4. equations[i][1] is either '=' or '!'
 * 5. equations[i][2] is '='
 */
public class SatisfiabilityOfEqualityEquations {

    public boolean equationsPossible(String[] equations) {
        int[] mark = new int[26];
        for (int i = 0; i < 26; i++) {
            mark[i] = i;
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                union(mark, index1, index2);
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                if (find(mark, index1) == find(mark, index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void union(int[] mark, int index1, int index2) {
        mark[find(mark, index1)] = find(mark, index2);
    }

    private int find(int[] mark, int index) {
        while (mark[index] != index) {
            mark[index] = mark[mark[index]];
            index = mark[index];
        }
        return index;
    }


}
