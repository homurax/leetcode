/**
 * 848. Shifting Letters
 *
 *
 * We have a string S of lowercase letters, and an integer array shifts.
 *
 * Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
 *
 * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
 *
 * Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
 *
 * Return the final string after all such shifts to S are applied.
 *
 * Example 1:
 *
 * Input: S = "abc", shifts = [3,5,9]
 * Output: "rpl"
 * Explanation:
 * We start with "abc".
 * After shifting the first 1 letters of S by 3, we have "dbc".
 * After shifting the first 2 letters of S by 5, we have "igc".
 * After shifting the first 3 letters of S by 9, we have "rpl", the answer.
 * Note:
 *
 * 1 <= S.length = shifts.length <= 20000
 * 0 <= shifts[i] <= 10 ^ 9
 */
public class ShiftingLetters {


    public String shiftingLetters1(String S, int[] shifts) {
        char[] letters = S.toCharArray();
        int n = letters.length;
        shifts[n - 1] = shifts[n - 1] % 26;
        letters[n - 1] = (char) (((letters[n - 1] - 'a') + shifts[n - 1]) % 26 + 'a');
        for (int i = n - 2; i >= 0; i--) {
            shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
            letters[i] = (char) (((letters[i] - 'a') + shifts[i]) % 26 + 'a');
        }
        return new String(letters);
    }

    public String shiftingLetters(String S, int[] shifts) {
        char[] letters = S.toCharArray();
        int count = 0;
        for (int i = letters.length - 1; i >= 0; i--) {
            count += shifts[i] % 26;
            letters[i] = (char) (((letters[i] - 'a' + count) % 26) + 'a');
        }
        return new String(letters);
    }


}
