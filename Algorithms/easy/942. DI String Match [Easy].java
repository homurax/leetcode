/**
 * 942. DI String Match
 *
 * Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.
 *
 * Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:
 *
 * If S[i] == "I", then A[i] < A[i+1]
 * If S[i] == "D", then A[i] > A[i+1]
 *
 *
 * Example 1:
 *
 * Input: "IDID"
 * Output: [0,4,1,3,2]
 *
 * Example 2:
 *
 * Input: "III"
 * Output: [0,1,2,3]
 *
 * Example 3:
 *
 * Input: "DDI"
 * Output: [3,2,0,1]
 *
 * Note:
 *
 * 1 <= S.length <= 10000
 * S only contains characters "I" or "D".
 */
public class DIStringMatch {

    /**
     * 可以理解为将一个升序序列与降序序列合并在一起
     * 那么升序的部分由0自增 降序的部分由length自减即可
     * 即遇到I放置较小元素 遇到D放置较大元素
     */
    public int[] diStringMatch(String S) {

        int length = S.length();
        int[] array = new int[length+1];
        int x = 0, y = length;
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == 'I')
                array[i] = x++;
            else
                array[i] = y--;
        }
        array[length] = y;
        return array;
    }
}
