/**
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 *
 *
 * A string s is called good if there are no two different characters in s that have the same frequency.
 *
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 *
 * The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 *
 * Example 2:
 *
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
 *
 * Example 3:
 *
 * Input: s = "ceabaacb"
 * Output: 2
 * Explanation: You can delete both 'c's resulting in the good string "eabaab".
 * Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. s contains only lowercase English letters.
 */
public class MinimumDeletionsToMakeCharacterFrequenciesUnique {

    // 其实从任意一个 len 开始处理都是一样的
    public int minDeletions(String s) {
        int ans = 0;
        int[] lens = new int[26];
        for (char c : s.toCharArray()) {
            lens[c - 'a']++;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            int len = lens[i];
            while (len-- > 0) {
                if (!set.contains(len)) {
                    set.add(len);
                    break;
                }
                ans++;
            }
        }
        return ans;
    }


    // TreeMap 换成 int[100001]的话，在 keySet() 步骤时只能在 [1, 100000] 中遍历，会慢
    public int minDeletions1(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int len : counts) {
            if (len > 0) {
                map.merge(len, 1, Integer::sum);
            }
        }
        int ans = 0;
        for (Integer len : new ArrayList<>(map.keySet())) { // keySet() 返回的是 view
            int frequency = map.get(len);
            for (int i = len - 1; i > 0 && frequency > 1; i--) {
                if (!map.containsKey(i)) {
                    map.put(i, 1);
                    ans += len - i;
                    frequency--;
                }
            }
            if (frequency > 1) {
                ans += (frequency - 1) * len;
            }
        }
        return ans;
    }

}
