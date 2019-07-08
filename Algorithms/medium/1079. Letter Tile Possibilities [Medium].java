/**
 * 1079. Letter Tile Possibilities
 *
 * You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.
 *
 *
 * Example 1:
 *
 * Input: "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 *
 * Example 2:
 *
 * Input: "AAABBC"
 * Output: 188
 *
 *
 * Note:
 *
 * 1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 */
public class LetterTilePossibilities {

    private int num = 0;

    public int numTilePossibilities(String tiles) {

        int[] table = new int[26];
        for (int i = 0; i < tiles.length(); i++) {
            table[tiles.charAt(i) - 'A']++;
        }
        dfs(table);
        return num;
    }

    private void dfs(int[] table) {

        for (int i = 0; i < table.length; i++) {
            if (table[i] > 0) {
                table[i]--;
                dfs(table);
                table[i]++;
                num++;
            }
        }
    }


    /**
     * 回溯
     */
    public int numTilePossibilities2(String tiles) {
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        return helper(chars, 0, chars.length, 1, 1, 0, new int[26]);
    }

    private int helper(char[] chars, int i, int len, int a, int b, int m, int[] counts) {
        int result = 0;
        a *= ++m;
        for (int j = i; j < len; ++j) {
            int index = chars[j] - 'A';
            if (j == i || chars[j] != chars[j - 1]) {
                b *= ++counts[index];
                result += a / b + helper(chars, j + 1, len, a, b, m, counts);
                b /= counts[index]--;
            }
        }
        return result;
    }

}
