/**
 * 1078. Occurrences After Bigram
 *
 * Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.
 *
 * For each such occurrence, add "third" to the answer, and return the answer.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
 * Output: ["girl","student"]
 * Example 2:
 *
 * Input: text = "we will we will rock you", first = "we", second = "will"
 * Output: ["we","rock"]
 *
 *
 * Note:
 *
 * 1 <= text.length <= 1000
 * text consists of space separated words, where each word consists of lowercase English letters.
 * 1 <= first.length, second.length <= 10
 * first and second consist of lowercase English letters.
 */
public class OccurrencesAfterBigram {


    public String[] findOcurrences(String text, String first, String second) {

        List<String> res = new ArrayList<>();
        String[] split = text.split("\\s+");
        for (int i = 0; i < split.length - 2; i++) {
            if (first.equals(split[i]) && second.equals(split[i + 1])) {
                res.add(split[i + 2]);
            }
        }
        return res.toArray(new String[0]);
    }

    public String[] findOcurrences2(String text, String first, String second) {

        List<String> res = new ArrayList<>();
        String pattern = first + " " + second;
        int position = -1;
        while ((position = text.indexOf(pattern, position + 1)) != -1) {
            int start = text.indexOf(' ', position + pattern.length());
            int end = text.indexOf(' ', position + pattern.length() + 1);
            if (end == -1) {
                end = text.length();
            }
            if(start != -1 && start + 1 < end) {
                res.add(text.substring(start + 1, end));
            }
        }

        return res.toArray(new String[0]);
    }

}
