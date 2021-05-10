/**
 * 93. Restore IP Addresses
 *
 *
 * Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.
 *
 * A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 *
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 *
 * Example 3:
 *
 * Input: s = "1111"
 * Output: ["1.1.1.1"]
 *
 * Example 4:
 *
 * Input: s = "010010"
 * Output: ["0.10.0.10","0.100.1.0"]
 *
 * Example 5:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3000
 * s consists of digits only.
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        int n = s.length();
        if (n < 4 || n > 12) {
            return Collections.emptyList();
        }
        List<String> ans = new ArrayList<>();
        char[] digits = s.toCharArray();
        for (int i = 1; i < Math.min(4, n - 2); i++) {
            for (int j = i + 1; j < Math.min(i + 4, n - 1); j++) {
                mark:
                for (int k = j + 1; k < Math.min(j + 4, n); k++) {
                    if (n - k > 3) {
                        continue;
                    }
                    String[] temp = new String[]{new String(Arrays.copyOfRange(digits, 0, i)),
                            new String(Arrays.copyOfRange(digits, i, j)),
                            new String(Arrays.copyOfRange(digits, j, k)),
                            new String(Arrays.copyOfRange(digits, k, n))};
                    for (String str : temp) {
                        if (str.length() > 1 && str.charAt(0) == '0') {
                            continue mark;
                        }
                    }
                    for (String str : temp) {
                        if (Integer.parseInt(str) > 255) {
                            continue mark;
                        }
                    }
                    ans.add(temp[0] + "." + temp[1] + "." + temp[2] + "." + temp[3]);
                }
            }
        }
        return ans;
    }


    private List<String> ans = new ArrayList<>();
    private int[] segments = new int[4];

    public List<String> restoreIpAddresses1(String s) {
        backtrack(s.toCharArray(), 0, 0);
        return ans;
    }

    private void backtrack(char[] digits, int idx, int segId) {
        if (segId == 4) {
            if (idx == digits.length) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    sb.append(segments[i]);
                    if (i < 3) {
                        sb.append('.');
                    }
                }
                ans.add(sb.toString());
            }
            return;
        }
        if (idx == digits.length) {
            return;
        }
        if (digits[idx] == '0') {
            segments[segId] = 0;
            backtrack(digits, idx + 1, segId + 1);
        }
        int addr = 0;
        for (int i = idx; i < digits.length; i++) {
            addr = addr * 10 + (digits[i] - '0');
            if (addr <= 0 || addr > 255) {
                return;
            }
            segments[segId] = addr;
            backtrack(digits, i + 1, segId + 1);
        }
    }



}
