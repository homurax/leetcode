/**
 * 1002. Find Common Characters
 *
 * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: ["bella","label","roller"]
 * Output: ["e","l","l"]
 * Example 2:
 *
 * Input: ["cool","lock","cook"]
 * Output: ["c","o"]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] is a lowercase letter
 */
public class FindCommonCharacters {

    /**
     * 计算每个字母出现次数 相同字母取最小次数
     * 最后遍历数组 过滤掉出现次数为0 以及添加至集合
     */
    public List<String> commonChars(String[] A) {

        List<String> list = new ArrayList<>();
        int[] dict = new int[26];
        for (int j = 0; j < A[0].length(); j++) {
            dict[A[0].charAt(j) - 'a']++;
        }

        for (String word : A) {

            int[] temp = new int[26];
            for (int i = 0; i < word.length(); i++) {
                temp[word.charAt(i) - 'a']++;
            }

            for (int i = 0; i < 26; i++) {
                if (temp[i] < dict[i])
                    dict[i] = temp[i];
            }
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < dict[i]; j++) {
                list.add(Character.toString((char) ('a' + i)));
            }
        }

        return list;
    }
}
