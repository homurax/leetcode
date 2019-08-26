/**
 * 1023. Camelcase Matching
 *
 * A query word matches a given pattern if we can insert lowercase letters to the pattern word so that it equals the query. (We may insert each character at any position, and may insert 0 characters.)
 *
 * Given a list of queries, and a pattern, return an answer list of booleans, where answer[i] is true if and only if queries[i] matches the pattern.
 *
 *
 *
 * Example 1:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * Output: [true,false,true,true,false]
 * Explanation:
 * "FooBar" can be generated like this "F" + "oo" + "B" + "ar".
 * "FootBall" can be generated like this "F" + "oot" + "B" + "all".
 * "FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".
 *
 * Example 2:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * Output: [true,false,true,false,false]
 * Explanation:
 * "FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
 * "FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".
 *
 * Example 3:
 *
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
 * Output: [false,true,false,false,false]
 * Explanation:
 * "FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".
 *
 *
 * Note:
 *
 * 1 <= queries.length <= 100
 * 1 <= queries[i].length <= 100
 * 1 <= pattern.length <= 100
 * All strings consists only of lower and upper case English letters.
 */
public class CamelcaseMatching {

    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> ans = new ArrayList<>(queries.length);
        char[] pArr = pattern.toCharArray();
        for (String query : queries) {
            //ans.add(matches(query, pattern));
            ans.add(matches(query.toCharArray(), pArr));
        }
        return ans;
    }

    private boolean matches(String query, String pattern) {
        int idxQ = 0, idxP = 0;
        while (idxQ < query.length()) {
            if (idxP < pattern.length() && query.charAt(idxQ) == pattern.charAt(idxP)) {
                idxP++;
            } else if (Character.isUpperCase(query.charAt(idxQ))) {
                return false;
            }
            idxQ++;
        }
        return idxP == pattern.length();
    }

    private boolean matches(char[] qArr, char[] pArr) {
        int m = qArr.length, n = pArr.length;
        int idxQ = 0, idxP = 0;
        while (idxQ < m && idxP < n) {
            if (qArr[idxQ] == pArr[idxP]) {
                idxP++;
            } else if (Character.isUpperCase(qArr[idxQ])) {
                return false;
            }
            idxQ++;
        }
        while (idxQ < m && Character.isLowerCase(qArr[idxQ])) {
            idxQ++;
        }
        return idxQ == m && idxP == n;
    }
}
