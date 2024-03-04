/**
 * 423. Reconstruct Original Digits from English
 *
 *
 * Given a string s containing an out-of-order English representation of digits 0-9, return the digits in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "owoztneoer"
 * Output: "012"
 *
 *
 * Example 2:
 *
 * Input: s = "fviefuro"
 * Output: "45"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
 * s is guaranteed to be valid.
 */
public class ReconstructOriginalDigitsFromEnglish {

    public String originalDigits(String s) {
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }
        int[] cnt = new int[10];
        cnt[0] = chars['z' - 'a'];
        cnt[2] = chars['w' - 'a'];
        cnt[4] = chars['u' - 'a'];
        cnt[6] = chars['x' - 'a'];
        cnt[8] = chars['g' - 'a'];
        cnt[1] = chars['o' - 'a'] - cnt[0] - cnt[2] - cnt[4];
        cnt[3] = chars['h' - 'a'] - cnt[8];
        cnt[5] = chars['f' - 'a'] - cnt[4];
        cnt[7] = chars['s' - 'a'] - cnt[6];
        cnt[9] = chars['i' - 'a'] - cnt[5] - cnt[6] - cnt[8];
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            ans.append(String.valueOf((char) (i + '0')).repeat(Math.max(0, cnt[i])));
        }
        return ans.toString();
    }

    // 按照每个单词中具有唯一性的字母 决定筛选数字的顺序
    private static final String[] words = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final int[] priority = new int[]{0, 8, 6, 3, 2, 7, 5, 9, 4, 1};

    public String originalDigits1(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : priority) {
            int count = Integer.MAX_VALUE;
            for (char c : words[i].toCharArray()) {
                count = Math.min(count, cnt[c - 'a']);
            }
            for (char c : words[i].toCharArray()) {
                cnt[c - 'a'] -= count;
            }
            while (count-- > 0) {
                sb.append(i);
            }
        }
        char[] chars = sb.toString().toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

}
