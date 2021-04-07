/**
 * 187. Repeated DNA Sequences
 *
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 *
 * Example 2:
 *
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequences {

    // hash
    public List<String> findRepeatedDnaSequences(String s) {
        int L = 10, n = s.length();
        if (n <= L) {
            return new ArrayList<>();
        }
        Set<String> seen = new HashSet<>();
        Set<String> output = new HashSet<>();
        for (int start = 0; start < n - L + 1; start++) {
            String temp = s.substring(start, start + L);
            if (seen.contains(temp)) {
                output.add(temp);
            }
            seen.add(temp);
        }
        return new ArrayList<>(output);
    }

    // Rabin-Karp 旋转hash 基于上一个 hash 计算当前 hash 类似滑窗
    public List<String> findRepeatedDnaSequences2(String s) {
        int L = 10, n = s.length();
        if (n <= L) {
            return new ArrayList<>();
        }

        int[] toInt = new int[26];
        toInt['C' - 'A'] = 1;
        toInt['G' - 'A'] = 2;
        toInt['T' - 'A'] = 3;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = toInt[s.charAt(i) - 'A'];
        }

        Set<Integer> seen = new HashSet<>();
        Set<String> output = new HashSet<>();
        int a = 4, aL = (int) Math.pow(a, L);
        int hash = 0;
        for (int i = 0; i < L; i++) {
            hash = hash * a + nums[i];
        }
        seen.add(hash);

        for (int start = 1; start < n - L + 1; start++) {
            hash = hash * a - nums[start - 1] * aL + nums[start + L - 1];
            if (seen.contains(hash)) {
                output.add(s.substring(start, start + L));
            }
            seen.add(hash);
        }
        return new ArrayList<>(output);
    }

    // 位
    // A - 0 -> 00(2)
    // C - 1 -> 01(2)
    // G - 2 -> 10(2)
    // T - 3 -> 11(2)
    // 1 << n 是设置第 n 位为 1。
    // ~(1 << n) 是设置第 n 位为 0，且全部低位为 1
    // bitmask &= ~(1 << n) 是将 bitmask 第 n 位设置为 0
    public List<String> findRepeatedDnaSequences3(String s) {
        int L = 10, n = s.length();
        if (n <= L) {
            return new ArrayList<>();
        }

        int[] toInt = new int[26];
        toInt['C' - 'A'] = 1;
        toInt['G' - 'A'] = 2;
        toInt['T' - 'A'] = 3;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = toInt[s.charAt(i) - 'A'];
        }

        Set<Integer> seen = new HashSet<>();
        Set<String> output = new HashSet<>();
        int bitmask = 0;
        for (int i = 0; i < L; i++) {
            bitmask <<= 2;
            bitmask |= nums[i];
        }
        seen.add(bitmask);

        for (int start = 1; start < n - L + 1; start++) {
            bitmask <<= 2;
            bitmask |= nums[start + L - 1];
            // 将高 2 位设置为 0
            // bitmask &= ~(1 << 2 * L) & ~(1 << (2 * L + 1);
            bitmask &= ~(3 << 2 * L);
            if (seen.contains(bitmask)) {
                output.add(s.substring(start, start + L));
            }
            seen.add(bitmask);
        }
        return new ArrayList<>(output);
    }
}
