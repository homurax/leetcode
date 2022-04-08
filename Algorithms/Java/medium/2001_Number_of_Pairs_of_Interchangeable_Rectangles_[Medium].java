/**
 * 2001. Number of Pairs of Interchangeable Rectangles
 *
 *
 * You are given n rectangles represented by a 0-indexed 2D integer array rectangles, where rectangles[i] = [widthi, heighti] denotes the width and height of the ith rectangle.
 *
 * Two rectangles i and j (i < j) are considered interchangeable if they have the same width-to-height ratio. More formally, two rectangles are interchangeable if widthi/heighti == widthj/heightj (using decimal division, not integer division).
 *
 * Return the number of pairs of interchangeable rectangles in rectangles.
 *
 *
 *
 * Example 1:
 *
 * Input: rectangles = [[4,8],[3,6],[10,20],[15,30]]
 * Output: 6
 * Explanation: The following are the interchangeable pairs of rectangles by index (0-indexed):
 * - Rectangle 0 with rectangle 1: 4/8 == 3/6.
 * - Rectangle 0 with rectangle 2: 4/8 == 10/20.
 * - Rectangle 0 with rectangle 3: 4/8 == 15/30.
 * - Rectangle 1 with rectangle 2: 3/6 == 10/20.
 * - Rectangle 1 with rectangle 3: 3/6 == 15/30.
 * - Rectangle 2 with rectangle 3: 10/20 == 15/30.
 *
 * Example 2:
 *
 * Input: rectangles = [[4,5],[7,8]]
 * Output: 0
 * Explanation: There are no interchangeable pairs of rectangles.
 *
 *
 * Constraints:
 *
 * 1. n == rectangles.length
 * 2. 1 <= n <= 10^5
 * 3. rectangles[i].length == 2
 * 4. 1 <= widthi, heighti <= 10^5
 */
public class NumberOfPairsOfInterchangeableRectangles {

    public long interchangeableRectangles(int[][] rectangles) {
        int n = rectangles.length;
        double[] ratios = new double[n];
        for (int i = 0; i < n; i++) {
            ratios[i] = (double) rectangles[i][0] / rectangles[i][1];
        }
        Arrays.sort(ratios);
        long ans = 0;
        int prev = 0;
        for (int i = 1; i < n; i++) {
            if (ratios[i - 1] == ratios[i]) {
                ans += i - prev;
            } else {
                prev = i;
            }
        }
        return ans;
    }

    public long interchangeableRectangles1(int[][] rectangles) {
        Map<Integer, Long> count = new HashMap<>();
        long ans = 0;
        for (int[] rectangle : rectangles) {
            int a = rectangle[0], b = rectangle[1];
            int gcd = gcd(a, b);
            int key = a / gcd * 100000 + b / gcd;
            ans += count.getOrDefault(key, 0L);
            count.merge(key, 1L, Long::sum);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
