/**
 * 777. Swap Adjacent in LR String
 *
 *
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string result, return True if and only if there exists a sequence of moves to transform start to result.
 *
 *
 * Example 1:
 *
 * Input: start = "RXXLRXRXL", result = "XRLXXRRLX"
 * Output: true
 * Explanation: We can transform start to result following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *
 *
 * Example 2:
 *
 * Input: start = "X", result = "L"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= start.length <= 10^4
 * start.length == result.length
 * Both start and result will only consist of characters in 'L', 'R', and 'X'.
 */
public class SwapAdjacentInLRString {

    // L 和 R 不能互相穿过
    // L && i < j || R && i > j
    public boolean canTransform(String start, String end) {
        if (!start.replaceAll("X", "").equals(end.replaceAll("X", ""))) {
            return false;
        }
        for (int i = 0, j = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'X') {
                continue;
            }
            while (end.charAt(j) == 'X') {
                j++;
            }
            if (i != j && (start.charAt(i) == 'L') != (i > j)) {
                return false;
            }
            j++;
        }
        return true;
    }

}
