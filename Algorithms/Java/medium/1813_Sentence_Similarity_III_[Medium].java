/**
 * 1813. Sentence Similarity III
 * <p>
 * <p>
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces. For example, "Hello World", "HELLO", "hello world hello world" are all sentences. Words consist of only uppercase and lowercase English letters.
 * <p>
 * Two sentences sentence1 and sentence2 are similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. For example, sentence1 = "Hello my name is Jane" and sentence2 = "Hello Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in sentence2.
 * <p>
 * Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
 * Output: true
 * Explanation: sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: sentence1 = "of", sentence2 = "A lot of words"
 * Output: false
 * Explanation: No single sentence can be inserted inside one of the sentences to make it equal to the other.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: sentence1 = "Eating right now", sentence2 = "Eating"
 * Output: true
 * Explanation: sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1. 1 <= sentence1.length, sentence2.length <= 100
 * 2. sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
 * 3. The words in sentence1 and sentence2 are separated by a single space.
 */
public class SentenceSimilarityIII {

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] s1 = sentence1.split(" ");
        String[] s2 = sentence2.split(" ");
        int len1 = s1.length;
        int len2 = s2.length;
        int l = 0;
        while (l < len1 && l < len2 && s1[l].equals(s2[l])) {
            l++;
        }
        if (l == len1 || l == len2) {
            return true;
        }
        int r1 = len1, r2 = len2;
        while (r1 > 0 && r2 > 0 && s1[r1 - 1].equals(s2[r2 - 1])) {
            r1--;
            r2--;
        }
        if (r1 == 0 || r2 == 0) {
            return true;
        }
        return l >= r1 || l >= r2;
    }

}
