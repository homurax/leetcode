/**
 * 6. Zigzag Conversion
 *
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 *
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 *
 * Example 3:
 *
 * Input: s = "A", numRows = 1
 * Output: "A"
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 1000
 * 2. s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 3. 1 <= numRows <= 1000
 */
public class ZigzagConversion {

    //
    public String convert(String s, int numRows) {
        int n = s.length();
        if (numRows == 1 || n <= numRows) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            if (i == 0 || i == numRows - 1) {
                int pos = i, offset = 2 * (numRows - 1);
                while (pos < n) {
                    sb.append(s.charAt(pos));
                    pos += offset;
                }
            } else {
                int pos1 = i, pos2 = 2 * numRows - i - 2;
                int offset = 2 * (numRows - 1);
                while (pos1 < n || pos2 < n) {
                    if (pos1 < n) {
                        sb.append(s.charAt(pos1));
                        pos1 += offset;
                    }
                    if (pos2 < n) {
                        sb.append(s.charAt(pos2));
                        pos2 += offset;
                    }
                }
            }
        }
        return sb.toString();
    }


    public String convert1(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int idx = 0;
        int flag = -1;
        for (char c : s.toCharArray()) {
            rows[idx].append(c);
            if (idx == 0 || idx == numRows - 1) {
                flag = -flag;
            }
            idx += flag;
        }
        return Arrays.stream(rows).reduce(StringBuilder::append).get().toString();
    }

}
