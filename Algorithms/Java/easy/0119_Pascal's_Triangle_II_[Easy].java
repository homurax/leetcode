/**
 * 119. Pascal's Triangle II
 *
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalTriangleII {


    /**
     * 杨辉三角第n行第m个数 C(m-1, n-1) 此处n为行数 即第0层为第一行
     *      n-1 <=> rowIndex
     *      m-1 <=> i
     * C(i, rowIndex) => rowIndex! / [(rowIndex - i)! * i!]
     * C(i + 1, rowIndex) / C(i, rowIndex) => (rowIndex - i) / (i + 1)
     * C(i + 1, rowIndex) = C(i, rowIndex) * (rowIndex - i) / (i + 1)
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        long cur = 1;
        for (int i = 0; i < rowIndex + 1; i++) {
            row.add((int) cur);
            cur = cur * (rowIndex - i) / (i + 1);
        }
        return row;
    }

    public List<Integer> getRow2(int rowIndex) {

        List<Integer> ans = new ArrayList<>(rowIndex + 1);
        if (rowIndex == 0) {
            ans.add(1);
            return ans;
        }
        if (rowIndex == 1) {
            ans.add(1);
            ans.add(1);
            return ans;
        }

        int[] last = new int[rowIndex + 1];
        int[] cur = new int[rowIndex + 1];
        int[] tmp;

        last[0] = last[1] = last[rowIndex] = cur[0] = cur[rowIndex] = 1;

        for (int i = 3; i <= rowIndex + 1; i++) {
            for (int j = 1; j < i - 1; j++) {
                cur[j] = last[j] + last[j - 1];
            }
            cur[i - 1] = 1;
            tmp = last;
            last = cur;
            cur = tmp;
        }

        for (int n : last) {
            ans.add(n);
        }
        return ans;
    }
}
