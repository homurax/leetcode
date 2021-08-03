/**
 * 1807. Evaluate the Bracket Pairs of a String
 *
 *
 * You are given a string s that contains some bracket pairs, with each pair containing a non-empty key.
 *
 * For example, in the string "(name)is(age)yearsold", there are two bracket pairs that contain the keys "name" and "age".
 * You know the values of a wide range of keys. This is represented by a 2D string array knowledge where each knowledge[i] = [keyi, valuei] indicates that key keyi has a value of valuei.
 *
 * You are tasked to evaluate all of the bracket pairs. When you evaluate a bracket pair that contains some key keyi, you will:
 *
 * Replace keyi and the bracket pair with the key's corresponding valuei.
 * If you do not know the value of the key, you will replace keyi and the bracket pair with a question mark "?" (without the quotation marks).
 * Each key will appear at most once in your knowledge. There will not be any nested brackets in s.
 *
 * Return the resulting string after evaluating all of the bracket pairs.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
 * Output: "bobistwoyearsold"
 * Explanation:
 * The key "name" has a value of "bob", so replace "(name)" with "bob".
 * The key "age" has a value of "two", so replace "(age)" with "two".
 *
 * Example 2:
 *
 * Input: s = "hi(name)", knowledge = [["a","b"]]
 * Output: "hi?"
 * Explanation: As you do not know the value of the key "name", replace "(name)" with "?".
 *
 * Example 3:
 *
 * Input: s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
 * Output: "yesyesyesaaa"
 * Explanation: The same key can appear multiple times.
 * The key "a" has a value of "yes", so replace all occurrences of "(a)" with "yes".
 * Notice that the "a"s not in a bracket pair are not evaluated.
 *
 * Example 4:
 *
 * Input: s = "(a)(b)", knowledge = [["a","b"],["b","a"]]
 * Output: "ba"
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. 0 <= knowledge.length <= 10^5
 * 3. knowledge[i].length == 2
 * 4. 1 <= keyi.length, valuei.length <= 10
 * 5. s consists of lowercase English letters and round brackets '(' and ')'.
 * 6. Every open bracket '(' in s will have a corresponding close bracket ')'.
 * 7. The key in each bracket pair of s will be non-empty.
 * 8. There will not be any nested bracket pairs in s.
 * 9. keyi and valuei consist of lowercase English letters.
 * 10. Each keyi in knowledge is unique.
 */
public class EvaluateTheBracketPairsOfAString {

    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> list : knowledge) {
            map.put(list.get(0), list.get(1));
        }
        StringBuilder rst = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int idx = 0; idx < chars.length; idx++) {
            char letter = chars[idx];
            if (letter == '(') {
                int offset = ++idx;
                while (chars[idx] != ')') {
                    idx++;
                }
                String key = new String(chars, offset, idx - offset);
                String value = map.getOrDefault(key, "?");
                rst.append(value);
            } else {
                rst.append(letter);
            }
        }
        return rst.toString();
    }

    public String evaluate2(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> list : knowledge) {
            map.put(list.get(0), list.get(1));
        }
        StringBuilder rst = new StringBuilder();
        char[] chars = s.toCharArray();
        int offset = -1;
        for (int idx = 0; idx < chars.length; idx++) {
            char letter = chars[idx];
            if (letter == '(') {
                offset = idx + 1;
            } else if (letter == ')') {
                String key = new String(chars, offset, idx - offset);
                String value = map.getOrDefault(key, "?");
                rst.append(value);
                offset = -1;
            } else if (offset == -1) {
                rst.append(letter);
            }
        }
        return rst.toString();
    }

}
