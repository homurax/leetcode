/**
 * 2745. Construct the Longest New String
 *
 *
 * You are given three integers x, y, and z.
 *
 * You have x strings equal to "AA", y strings equal to "BB", and z strings equal to "AB". You want to choose some (possibly all or none) of these strings and concatenate them in some order to form a new string. This new string must not contain "AAA" or "BBB" as a substring.
 *
 * Return the maximum possible length of the new string.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2, y = 5, z = 1
 * Output: 12
 * Explanation: We can concactenate the strings "BB", "AA", "BB", "AA", "BB", and "AB" in that order. Then, our new string is "BBAABBAABBAB".
 * That string has length 12, and we can show that it is impossible to construct a string of longer length.
 *
 *
 * Example 2:
 *
 * Input: x = 3, y = 2, z = 2
 * Output: 14
 * Explanation: We can concactenate the strings "AB", "AB", "AA", "BB", "AA", "BB", and "AA" in that order. Then, our new string is "ABABAABBAABBAA".
 * That string has length 14, and we can show that it is impossible to construct a string of longer length.
 *
 *
 * Constraints:
 *
 * 1 <= x, y, z <= 50
 */
public class ConstructTheLongestNewString {

    // 如果没有 AB，AA 和 BB 只能交替出现，如果两者数量不等，则可以多拼接一个（左端追加 BB 或者右端追加 AA）
    // (min(x, y) * 2 + (x != y ? 1 : 0)) * 2
    // 对于 AB，只能插入在在 BB 和 AA 之间，并且连续出现
    // (min(x, y) * 2 + (x != y ? 1 : 0) + z) * 2
    public int longestString(int x, int y, int z) {
        return (Math.min(x, y) * 2 + (x != y ? 1 : 0) + z) * 2;
    }

}
