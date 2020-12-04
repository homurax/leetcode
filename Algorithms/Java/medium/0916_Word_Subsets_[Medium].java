/**
 * 916. Word Subsets
 *
 *
 * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 *
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 *
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 *
 * Return a list of all universal words in A.  You can return the words in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 *
 * Example 2:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
 * Output: ["apple","google","leetcode"]
 *
 * Example 3:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 * Output: ["facebook","google"]
 *
 * Example 4:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 * Output: ["google","leetcode"]
 *
 * Example 5:
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 *
 *
 * Note:
 *
 * 1. 1 <= A.length, B.length <= 10000
 * 2. 1 <= A[i].length, B[i].length <= 10
 * 3. A[i] and B[i] consist only of lowercase letters.
 * 3. All words in A[i] are unique: there isn't i != j with A[i] == A[j].
 */
public class WordSubsets {

    public List<String> wordSubsets1(String[] A, String[] B) {
        int[] target = new int[26];
        int[] temp = new int[26];
        for (String letters : B) {
            for (int i = 0; i < letters.length(); i++) {
                temp[letters.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                target[i] = Math.max(target[i], temp[i]);
            }
            Arrays.fill(temp, 0);
        }
        List<String> ans = new ArrayList<>();
        for (String letters : A) {
            for (int i = 0; i < letters.length(); i++) {
                temp[letters.charAt(i) - 'a']++;
            }
            boolean add = true;
            for (int i = 0; i < 26; i++) {
                if (target[i] > temp[i]) {
                    add = false;
                    break;
                }
            }
            if (add) {
                ans.add(letters);
            }
            Arrays.fill(temp, 0);
        }
        return ans;
    }




    public List<String> wordSubsets(String[] A, String[] B) {
        int[] target = new int[26];
        for (String letters : B) {
            int[] temp = count(letters);
            for (int i = 0; i < 26; i++) {
                target[i] = Math.max(target[i], temp[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (String letters : A) {
            if (check(target, count(letters))) {
                ans.add(letters);
            }
        }
        return ans;
    }

    private int[] count(String letters) {
        int[] rst = new int[26];
        for (char c: letters.toCharArray()) {
            rst[c - 'a']++;
        }
        return rst;
    }

    private boolean check(int[] target, int[] temp) {
        for (int i = 0; i < 26; i++) {
            if (target[i] > temp[i]) {
                return false;
            }
        }
        return true;
    }


}
