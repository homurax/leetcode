/**
 * 1419. Minimum Number of Frogs Croaking
 *
 *
 * Given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs, that is, multiple frogs can croak at the same time, so multiple “croak” are mixed. Return the minimum number of different frogs to finish all the croak in the given string.
 *
 * A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ sequentially. The frogs have to print all five letters to finish a croak. If the given string is not a combination of valid "croak" return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: croakOfFrogs = "croakcroak"
 * Output: 1
 * Explanation: One frog yelling "croak" twice.
 *
 * Example 2:
 *
 * Input: croakOfFrogs = "crcoakroak"
 * Output: 2
 * Explanation: The minimum number of frogs is two.
 * The first frog could yell "crcoakroak".
 * The second frog could yell later "crcoakroak".
 *
 * Example 3:
 *
 * Input: croakOfFrogs = "croakcrook"
 * Output: -1
 * Explanation: The given string is an invalid combination of "croak" from different frogs.
 *
 * Example 4:
 *
 * Input: croakOfFrogs = "croakcroa"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1. 1 <= croakOfFrogs.length <= 10^5
 * 2. All characters in the string are: 'c', 'r', 'o', 'a' or 'k'.
 */
public class MinimumNumberOfFrogsCroaking {

    public int minNumberOfFrogs1(String croakOfFrogs) {
        if (croakOfFrogs.length() % 5 != 0) {
            return -1;
        }
        int ans = 0;
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        for (char letter : croakOfFrogs.toCharArray()) {
            switch (letter) {
                case 'c': c++; break;
                case 'r': r++; break;
                case 'o': o++; break;
                case 'a': a++; break;
                case 'k': k++; break;
            }
            // 任何时刻靠前字符数量都应该大于靠后字符数量
            if (r > c || o > r || a > o || k > a) {
                return -1;
            }
            if (k == 1) {
                c--;
                r--;
                o--;
                a--;
                k--;
            }
            ans = Math.max(ans, c);
        }
        if (c > 0) {
            return -1;
        }
        return ans;
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        if (croakOfFrogs.length() % 5 != 0) {
            return -1;
        }
        int ans = 0;
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        for (char letter : croakOfFrogs.toCharArray()) {
            if (letter == 'c') {
                if (c - k >= ans) {
                    ans++;
                }
                c++;
            } else if (letter == 'r') {
                if (c > r) r++; else return -1;
            } else if (letter == 'o') {
                if (r > o) o++; else return -1;
            } else if (letter == 'a') {
                if (o > a) a++; else return -1;
            } else if (letter == 'k') {
                if (a > k) k++; else return -1;
            }
        }
        return c == k ? ans : -1;
    }

}
