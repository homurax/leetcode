/**
 * 2405. Optimal Partition of String
 *
 *
 * Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.
 *
 * Return the minimum number of substrings in such a partition.
 *
 * Note that each character should belong to exactly one substring in a partition.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abacaba"
 * Output: 4
 * Explanation:
 * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
 * It can be shown that 4 is the minimum number of substrings needed.
 *
 *
 * Example 2:
 *
 * Input: s = "ssssss"
 * Output: 6
 * Explanation:
 * The only valid partition is ("s","s","s","s","s","s").
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. s consists of only English lowercase letters.
 */
public class OptimalPartitionOfString {


    public int partitionString(String s) {
        int ans = 1, mask = 0;
        for (char c : s.toCharArray()) {
            if ((mask >> (c & 31) & 1) > 0) {
                mask = 0;
                ans++;
            }
            mask |= 1 << (c & 31);
        }
        return ans;
    }

    // 贪心 状态压缩
    public int partitionString2(String s) {
        int ans = 0, mask = 0;
        for (char c : s.toCharArray()) {
            int n = c - 'a';
            if ((mask & (1 << n)) == 0) {
                mask |= 1 << n;
            } else {
                mask = 1 << n;
                ans++;
            }
        }
        if (mask > 0) {
            ans++;
        }
        return ans;
    }

    // 贪心
    public int partitionString1(String s) {
        int ans = 0;
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (cnt[idx] == 0) {
                cnt[idx]++;
            } else {
                Arrays.fill(cnt, 0);
                cnt[idx]++;
                ans++;
            }
        }
        if (Arrays.stream(cnt).anyMatch(t -> t > 0)) {
            ans++;
        }
        return ans;
    }

}
