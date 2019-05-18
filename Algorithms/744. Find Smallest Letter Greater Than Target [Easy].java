/**
 * 744. Find Smallest Letter Greater Than Target
 *
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
 *
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 *
 * Examples:
 * Input:
 * letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "g"
 * Output: "j"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "j"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 *
 * Note:
 * letters has a length in range [2, 10000].
 * letters consists of lowercase letters, and contains at least 2 unique letters.
 * target is a lowercase letter.
 */
public class FindSmallestLetterGreaterThanTarget {

    /**
     * for循环可以换成二分查找
     */
    public char nextGreatestLetter(char[] letters, char target) {

        int length = letters.length;
        if (target >= letters[length - 1])
            return letters[0];

        for (char letter : letters) {
            if (target < letter)
                return letter;
        }
        throw new IllegalArgumentException();
    }


    public char nextGreatestLetter2(char[] letters, char target) {

        boolean[] table = new boolean[26];
        for (char c: letters)
            table[c - 'a'] = true;

        while (true) {
            target++;
            if (target > 'z')
                target = 'a';
            if (table[target - 'a'])
                return target;
        }
    }

}
