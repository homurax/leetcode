/**
 * 1404. Number of Steps to Reduce a Number in Binary Representation to One
 *
 *
 * Given a number s in their binary representation. Return the number of steps to reduce it to 1 under the following rules:
 *
 * If the current number is even, you have to divide it by 2.
 *
 * If the current number is odd, you have to add 1 to it.
 *
 * It's guaranteed that you can always reach to one for all testcases.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1101"
 * Output: 6
 * Explanation: "1101" corressponds to number 13 in their decimal representation.
 * Step 1) 13 is odd, add 1 and obtain 14.
 * Step 2) 14 is even, divide by 2 and obtain 7.
 * Step 3) 7 is odd, add 1 and obtain 8.
 * Step 4) 8 is even, divide by 2 and obtain 4.
 * Step 5) 4 is even, divide by 2 and obtain 2.
 * Step 6) 2 is even, divide by 2 and obtain 1.
 *
 * Example 2:
 *
 * Input: s = "10"
 * Output: 1
 * Explanation: "10" corressponds to number 2 in their decimal representation.
 * Step 1) 2 is even, divide by 2 and obtain 1.
 *
 * Example 3:
 *
 * Input: s = "1"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of characters '0' or '1'
 * s[0] == '1'
 */
public class NumberOfStepsToReduceANumberInBinaryRepresentationToOne {

    /**
     * 0 被右移掉，一次操作
     * 1 被加 1 变为 0，0 变为 1，所以 1 会被操作两次
     *
     * 没有遇到过 1：0 被右移掉，操作一次；1 操作 两次
     * 遇到过 1：0 在之前的操作中会被变为 1，操作两次；1 会被变为 0，操作一次
     *
     * 除了最开始最低位可能是若干个 0，开始处理后最低为就不会是 0 了
     * 因为其他位置的 0 总会被低位的 1 进位，变为 1
     */
    public int numSteps(String s) {
        int ans = 0;
        boolean meet1 = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                ans += meet1 ? 2 : 1;
            } else {
                if (meet1) {
                    ans++;
                } else {
                    // 到高位 1 就不需要操作了
                    if (i != 0) {
                        ans += 2;
                    }
                    meet1 = true;
                }
            }
        }
        return ans;
    }

}
