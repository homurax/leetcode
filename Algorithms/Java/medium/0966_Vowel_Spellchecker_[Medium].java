/**
 * 966. Vowel Spellchecker
 *
 *
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 *
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 *
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 *
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 *
 *
 *
 * Example 1:
 *
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 *
 *
 * Example 2:
 *
 * Input: wordlist = ["yellow"], queries = ["YellOw"]
 * Output: ["yellow"]
 *
 *
 * Constraints:
 *
 * 1 <= wordlist.length, queries.length <= 5000
 * 1 <= wordlist[i].length, queries[i].length <= 7
 * wordlist[i] and queries[i] consist only of only English letters.
 */
public class VowelSpellchecker {

    // 直接存储替换单词
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> words = new HashSet<>();
        Map<String, String> map = new HashMap<>();
        Map<String, String> replaceMap = new HashMap<>();
        for (String word : wordlist) {
            words.add(word);
            map.putIfAbsent(word.toLowerCase(), word);
            String newWord = word.toLowerCase()
                    .replace('a', '0')
                    .replace('e', '0')
                    .replace('i', '0')
                    .replace('o', '0')
                    .replace('u', '0');
            replaceMap.putIfAbsent(newWord, word);
        }
        String[] ans = new String[queries.length];
        int i = 0;
        for (String query : queries) {
            if (words.contains(query)) {
                ans[i] = query;
            } else if (map.containsKey(query.toLowerCase())) {
                ans[i] = map.get(query.toLowerCase());
            } else {
                String newWord = query.toLowerCase()
                        .replace('a', '0')
                        .replace('e', '0')
                        .replace('i', '0')
                        .replace('o', '0'
                        ).replace('u', '0');
                ans[i] = replaceMap.getOrDefault(newWord, "");
            }
            i++;
        }
        return ans;
    }

}
