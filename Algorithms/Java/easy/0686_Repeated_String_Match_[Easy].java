/**
 * 686. Repeated String Match
 *
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
 *
 * For example, with A = "abcd" and B = "cdabcdab".
 *
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
 *
 * Note:
 * The length of A and B will be between 1 and 10000.
 */
public class RepeatedStringMatch {

    public int repeatedStringMatch(String A, String B) {
        int[] count = new int[26];
        for (char ch : A.toCharArray()) {
            count[ch - 'a']++;
        }
        for (char ch : B.toCharArray()) {
            if (count[ch - 'a'] == 0)
                return -1;
        }
        StringBuilder sb = new StringBuilder(A);
        int ans = 1;
        int repeat = B.length() / A.length() + 1;
        while (repeat >= 0) {
            if (sb.indexOf(B) != -1)
                return ans;
            ans++;
            repeat--;
            sb.append(A);
        }
        return -1;
    }


    public int repeatedStringMatch2(String A, String B) {
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        for (int i = 0; i < a.length; i++) {
            int len = loop(a, b, i);
            if (len > 0) {
                int count = 1;
                len = B.length() - a.length + i;
                count += len / a.length;
                count += len % a.length > 0 ? 1 : 0;
                return count;
            } else if (len + a.length <= 0) {
                return -1;
            }
        }
        return -1;
    }

    private int loop(char[] a, char[] b, int start) {
        int count = start;
        for (char c : b) {
            if (a[start % a.length] != c) {
                return count - start;
            }
            start++;
        }
        return 1;
    }
}
