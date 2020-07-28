/**
 * 1170. Compare Strings by Frequency of the Smallest Character
 *
 * Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
 *
 * Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.
 *
 *
 * Example 1:
 *
 * Input: queries = ["cbd"], words = ["zaaaz"]
 * Output: [1]
 * Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
 *
 * Example 2:
 *
 * Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 * Output: [1,2]
 * Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 *
 *
 * Constraints:
 *
 * 1 <= queries.length <= 2000
 * 1 <= words.length <= 2000
 * 1 <= queries[i].length, words[i].length <= 10
 * queries[i][j], words[i][j] are English lowercase letters.
 */
public class CompareStringsByFrequencyOfTheSmallestCharacter {

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] temp1 = new int[queries.length];
        int[] temp2 = new int[words.length];
        for (int i = 0; i < queries.length; i++) {
            temp1[i] = count(queries[i]);
        }
        for (int i = 0; i < words.length; i++) {
            temp2[i] = count(words[i]);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int f1 = temp1[i];
            for (int f2 : temp2) {
                if (f2 > f1) {
                    ans[i]++;
                }
            }
        }
        return ans;
    }

    private int count(String str) {
        char curr = '~';
        int count = 0;
        for (char aChar : str.toCharArray()) {
            if (aChar < curr) {
                curr = aChar;
                count = 1;
            } else if (aChar == curr) {
                count++;
            }
        }
        return count;
    }



    public int[] numSmallerByFrequency2(String[] queries, String[] words) {
        int[] temp = new int[12];
        for (String word : words) {
            temp[f(word)]++;
        }
        for (int i = 9; i >= 0; i--) {
            temp[i] += temp[i + 1];
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = temp[f(queries[i]) + 1];
        }
        return ans;
    }

    private int f(String s) {
        int[] temp = new int[26];
        int minChar = 25;
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            temp[index]++;
            minChar = Math.min(minChar, index);
        }
        return temp[minChar];
    }

}
