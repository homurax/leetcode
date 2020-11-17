/**
 * 838. Push Dominoes
 *
 *
 * There are N dominoes in a line, and we place each domino vertically upright.
 *
 * In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
 *
 *
 *
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left.
 *
 * Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
 *
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 *
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
 *
 * Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
 *
 * Return a string representing the final state.
 *
 * Example 1:
 *
 * Input: ".L.R...LR..L.."
 * Output: "LL.RR.LLRRLL.."
 * Example 2:
 *
 * Input: "RR.L"
 * Output: "RR.L"
 * Explanation: The first domino expends no additional force on the second domino.
 *
 * Note:
 *
 * 1. 0 <= N <= 10^5
 * 2. String dominoes contains only 'L', 'R' and '.'
 */
public class PushDominoes {

    public String pushDominoes1(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int n = chars.length;
        char[] temp = new char[n];
        boolean modify;
        do {
            modify = false;
            for (int i = 0; i < n; i++) {
                if (chars[i] == '.') continue;
                if (chars[i] == 'L') {
                    if ((i - 1 >= 0 && chars[i - 1] == '.')
                            && (i - 2 < 0 || chars[i - 2] != 'R')) {
                        temp[i - 1] = 'L';
                        modify = true;
                    }
                }
                else {
                    if ((i + 1 < n && chars[i + 1] == '.')
                            && (i + 2 >= n || chars[i + 2] != 'L')) {
                        temp[++i] = 'R';
                        modify = true;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (temp[i] != '\u0000') {
                    chars[i] = temp[i];
                    temp[i] = '\u0000';
                }
            }
        } while (modify);
        return new String(chars);
    }

    public String pushDominoes(String dominoes) {
        char[] chars = dominoes.toCharArray();
        int L = -1, R = -1, n = dominoes.length();
        for (int i = 0; i <= n; i++) {
            if (i == chars.length || chars[i] == 'R') {
                if (R > L) {
                    while (R < i) chars[R++] = 'R';
                }
                R = i;
            }
            else if (chars[i] == 'L') {
                if (L > R || R == -1) {
                    while (++L < i) chars[L] = 'L';
                }
                else {
                    L = i;
                    int low = R + 1, high = L - 1;
                    while (low < high) {
                        chars[low++] = 'R';
                        chars[high--] = 'L';
                    }
                }
            }
        }
        return new String(chars);
    }

}
