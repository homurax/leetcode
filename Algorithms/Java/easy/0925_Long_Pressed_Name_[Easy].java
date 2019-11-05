/**
 * 925. Long Pressed Name
 *
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 *
 *
 * Example 1:
 *
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 *
 * Example 2:
 *
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 *
 * Example 3:
 *
 * Input: name = "leelee", typed = "lleeelee"
 * Output: true
 *
 * Example 4:
 *
 * Input: name = "laiden", typed = "laiden"
 * Output: true
 * Explanation: It's not necessary to long press any character.
 *
 *
 * Note:
 *
 * name.length <= 1000
 * typed.length <= 1000
 * The characters of name and typed are lowercase letters.
 */
public class LongPressedName {

    public boolean isLongPressedName(String name, String typed) {

        int nr = 0, tr = 0;
        final int nameLen = name.length();
        final int typedLen = typed.length();

        while (nr < nameLen) {

            if (tr >= typedLen) return false;

            int count = 1;
            char curr = name.charAt(nr);

            while (nr + 1 < nameLen && curr == name.charAt(nr + 1)) {
                nr++;
                count++;
            }

            if (curr != typed.charAt(tr)) return false;

            while (tr + 1 < typedLen && curr == typed.charAt(tr + 1)) {
                tr++;
                count--;
            }

            if (--count > 0) return false;

            nr++;
            tr++;
        }

        return tr >= typedLen;
    }

    public boolean isLongPressedName2(String name, String typed) {

        char[] nameArr = name.toCharArray();
        char[] typedArr = typed.toCharArray();

        int r1 = 0, r2 = 0;
        while (r1 < nameArr.length && r2 < typedArr.length) {
            if (nameArr[r1] == typedArr[r2]) {
                r1++;
            }
            r2++;
        }
        return r1 == nameArr.length;
    }
}
