/**
 * 1702. Maximum Binary String After Change
 *
 *
 * You are given a binary string binary consisting of only 0's or 1's. You can apply each of the following operations any number of times:
 *
 * Operation 1: If the number contains the substring "00", you can replace it with "10".
 * For example, "00010" -> "10010"
 * Operation 2: If the number contains the substring "10", you can replace it with "01".
 * For example, "00010" -> "00001"
 * Return the maximum binary string you can obtain after any number of operations. Binary string x is greater than binary string y if x's decimal representation is greater than y's decimal representation.
 *
 *
 *
 * Example 1:
 *
 * Input: binary = "000110"
 * Output: "111011"
 * Explanation: A valid transformation sequence can be:
 * "000110" -> "000101"
 * "000101" -> "100101"
 * "100101" -> "110101"
 * "110101" -> "110011"
 * "110011" -> "111011"
 *
 * Example 2:
 *
 * Input: binary = "01"
 * Output: "01"
 * Explanation: "01" cannot be transformed any further.
 *
 *
 * Constraints:
 *
 * 1. 1 <= binary.length <= 10^5
 * 2. binary consist of '0' and '1'.
 */
public class MaximumBinaryStringAfterChange {

    // 核心就是算 0 的位置
    public String maximumBinaryString(String binary) {
        char[] chars = binary.toCharArray();
        int n = chars.length;
        int zeroCnt = 0, zeroIdx = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                zeroCnt++;
                zeroIdx = i;
            }
        }
        if (zeroCnt <= 1) {
            return binary;
        }
        Arrays.fill(chars, '1');
        chars[zeroIdx + zeroCnt - 1] = '0';
        return new String(chars);
    }

    // 左侧连续 1 不处理，其余的 1 移至右侧，中间的 00000 转换为 11110
    public String maximumBinaryString1(String binary) {
        char[] chars = binary.toCharArray();
        int n = chars.length;
        int l = 0, r = 0, flag = 1;
        for (char c : chars) {
            flag = flag & Character.getNumericValue(c);
            l += flag & Character.getNumericValue(c);
            r += ~flag & Character.getNumericValue(c);
        }
        if (l + r < n - 1) { // 有至少两个 0
            int k = n - r - 1;
            for (int i = 0; i < n; i++) {
                chars[i] = (i == k) ? '0' : '1';
            }
        }
        return new String(chars);
    }

}
