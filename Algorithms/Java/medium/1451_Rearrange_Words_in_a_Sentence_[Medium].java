/**
 * 1451. Rearrange Words in a Sentence
 *
 *
 * Given a sentence text (A sentence is a string of space-separated words) in the following format:
 *
 * First letter is in upper case.
 * Each word in text are separated by a single space.
 * Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths. If two words have the same length, arrange them in their original order.
 *
 * Return the new text following the format shown above.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "Leetcode is cool"
 * Output: "Is cool leetcode"
 * Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
 * Output is ordered by length and the new first word starts with capital letter.
 *
 * Example 2:
 *
 * Input: text = "Keep calm and code on"
 * Output: "On and keep calm code"
 * Explanation: Output is ordered as follows:
 * "On" 2 letters.
 * "and" 3 letters.
 * "keep" 4 letters in case of tie order by position in original text.
 * "calm" 4 letters.
 * "code" 4 letters.
 *
 * Example 3:
 *
 * Input: text = "To be or not to be"
 * Output: "To be or to be not"
 *
 *
 * Constraints:
 *
 * 1. text begins with a capital letter and then contains lowercase letters and single space between words.
 * 2. 1 <= text.length <= 10^5
 */
public class RearrangeWordsInASentence {

    public String arrangeWords1(String text) {
        String[] words = text.split("\\s");
        words[0] = Character.toLowerCase(words[0].charAt(0)) + words[0].substring(1);
        String collect = Arrays.stream(words).sorted(Comparator.comparingInt(String::length)).collect(Collectors.joining(" "));
        return Character.toUpperCase(collect.charAt(0)) + collect.substring(1);
    }

    public static String arrangeWords2(String text) {
        String[] words = text.split("\\s");
        words[0] = Character.toLowerCase(words[0].charAt(0)) + words[0].substring(1);

        List<String>[] arr = new ArrayList[text.length()];
        for (String word : words) {
            int length = word.length();
            if (arr[length] == null) {
                arr[length] = new ArrayList<>();
            }
            arr[length].add(word);
        }
        StringBuilder sb = new StringBuilder();
        for (List<String> strings : arr) {
            if (strings != null) {
                for (String word : strings) {
                    sb.append(word).append(" ");
                }
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

}
