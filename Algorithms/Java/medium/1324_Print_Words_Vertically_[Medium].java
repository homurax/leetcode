/**
 * 1324. Print Words Vertically
 *
 * Given a string s. Return all the words vertically in the same order in which they appear in s.
 * Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
 * Each word would be put on only one column and that in one column there will be only one word.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "HOW ARE YOU"
 * Output: ["HAY","ORO","WEU"]
 * Explanation: Each word is printed vertically.
 *  "HAY"
 *  "ORO"
 *  "WEU"
 *
 * Example 2:
 *
 * Input: s = "TO BE OR NOT TO BE"
 * Output: ["TBONTB","OEROOE","   T"]
 * Explanation: Trailing spaces is not allowed.
 * "TBONTB"
 * "OEROOE"
 * "   T"
 *
 * Example 3:
 *
 * Input: s = "CONTEST IS COMING"
 * Output: ["CIC","OSO","N M","T I","E N","S G","T"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 200
 * s contains only upper case English letters.
 * It's guaranteed that there is only one space between 2 words.
 */
public class PrintWordsVertically {

    public List<String> printVertically(String s) {
        String[] words = s.split("\\s+");
        int m = 0, n = words.length;
        for (String word : words) {
            m = Math.max(m, word.length());
        }

        char[][] letters = new char[m][n];
        for (int j = 0; j < words.length; j++) {
            int i = 0;
            char[] word = words[j].toCharArray();
            while (i < word.length) {
                letters[i][j] = word[i];
                i++;
            }
            while (i < m) {
                letters[i++][j] = ' ';
            }
        }

        List<String> ans = new ArrayList<>(m);
        for (char[] letter : letters) {
            int last = n - 1;
            while (letter[last] == ' ') {
                last--;
            }
            ans.add(new String(letter, 0, ++last));
        }
        return ans;
    }

}
