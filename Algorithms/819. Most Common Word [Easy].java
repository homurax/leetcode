/**
 * 819. Most Common Word
 *
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 *
 * Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
 *
 *
 *
 * Example:
 *
 * Input:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 *
 *
 * Note:
 *
 * 1 <= paragraph.length <= 1000.
 * 0 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class MostCommonWord {

    /**
     * P: 标点字符
     * L: 字母
     * M: 标记符号 (一般不会单独出现)
     * Z: 分隔符 (比如空格、换行等)
     * S: 符号 (比如数学符号、货币符号等)
     * N: 数字 (比如阿拉伯数字、罗马数字等)
     * C: 其他字符
     */
    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> set = new HashSet<>(Arrays.asList(banned));
        String[] arr = paragraph.replaceAll("[\\pP\\pS\\pZ]", " ").split("\\s+");
        Map<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            str = str.toLowerCase();
            if (!set.contains(str))
                map.put(str, map.getOrDefault(str, 0) + 1);
        }

        return map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
    }

    public String mostCommonWord2(String paragraph, String[] banned) {
        paragraph += ".";
        Set<String> set = new HashSet<>();
        for (String ban : banned) { set.add(ban); }

        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        String ans = null;
        for (char c : paragraph.toCharArray()) {
            String word;
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (sb.length() > 0) {

                if (!set.contains(word = sb.toString())) {
                    map.put(word, map.getOrDefault(word, 0) + 1);
                    Integer currCount = map.get(word);
                    if (currCount > count) {
                        count = currCount;
                        ans = word;
                    }
                }
                sb.delete(0, sb.length());
            }
        }

        return ans;
    }
}
