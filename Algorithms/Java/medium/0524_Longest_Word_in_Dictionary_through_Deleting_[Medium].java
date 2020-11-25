/**
 * 524. Longest Word in Dictionary through Deleting
 *
 *
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 *
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 *
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * Output:
 * "a"
 *
 * Note:
 * 1. All the strings in the input will only contain lower-case letters.
 * 2. The size of the dictionary won't exceed 1,000.
 * 3. The length of all the strings in the input won't exceed 1,000.
 */
public class LongestWordInDictionaryThroughDeleting {

    // 排序后从头开始找第一个
    public String findLongestWord1(String s, List<String> d) {
        d.sort(Comparator.comparing(String::length).reversed().thenComparing(Function.identity()));
        int strLen = s.length();
        for (String word : d) {
            int wordLen = word.length();
            if (wordLen <= strLen) {
                int i = 0;
                for (int j = 0; i < wordLen && j < strLen; j++) {
                    if (word.charAt(i) == s.charAt(j)) {
                        i++;
                    }
                }
                if (i == wordLen) {
                    return word;
                }
            }
        }
        return "";
    }

    // 不排序遍历遍历一次 其实和 排序后从头遍历找第一个是差不多的
    public String findLongestWord2(String s, List<String> d) {
        String ans = "";
        for (String word : d) {
            if (word.length() <= s.length()) {
                int i = 0;
                for (int j = 0; i < word.length() && j < s.length(); j++) {
                    if (word.charAt(i) == s.charAt(j)) {
                        i++;
                    }
                }
                if (i == word.length()) {
                    if (word.length() > ans.length() || (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                        ans = word;
                    }
                }
            }
        }
        return ans;
    }


    // 修改双指针的逻辑 跳过无效部分
    public String findLongestWord(String s, List<String> d) {
        String ans = "";
        for (String word : d) {
            if (word.length() < ans.length() || !isSubsequence(word, s))
                continue;
            if (word.length() > ans.length() || word.compareTo(ans) < 0)
                ans = word;
        }
        return ans;
    }

    private boolean isSubsequence(String target, String source) {
        for (int i = 0, j = -1; i < target.length(); i++) {
            j = source.indexOf(target.charAt(i), j + 1);
            if (j == -1) return false;
        }
        return true;
    }


}
