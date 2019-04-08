/**
 * 884. Uncommon Words from Two Sentences
 *
 * We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Return a list of all uncommon words.
 *
 * You may return the list in any order.
 *
 * Example 1:
 *
 * Input: A = "this apple is sweet", B = "this apple is sour"
 * Output: ["sweet","sour"]
 *
 * Example 2:
 *
 * Input: A = "apple apple", B = "banana"
 * Output: ["banana"]
 *
 * Note:
 *
 * 0 <= A.length <= 200
 * 0 <= B.length <= 200
 * A and B both contain only spaces and lowercase letters.
 */
public class UncommonWordsFromTwoSentences {


    /**
     * 不常见单词定义为
     * 在其中一个句子中只出现一次 在另一个句子中却没有出现
     * 也就是总共只出现过一次
     */
    public String[] uncommonFromSentences(String A, String B) {

        Map<String, Integer> countMap = new HashMap<>();

        for (String word: A.split(" "))
            countMap.merge(word, 1, (x, y) -> x + y); // countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        for (String word: B.split(" "))
            countMap.merge(word, 1, (x, y) -> x + y);

        List<String> list = new LinkedList<>();
        for (String word: countMap.keySet())
            if (countMap.get(word) == 1)
                list.add(word);
        return list.toArray(new String[0]);
    }
}
