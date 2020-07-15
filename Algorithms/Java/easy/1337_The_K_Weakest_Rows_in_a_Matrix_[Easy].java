/**
 * 1337. The K Weakest Rows in a Matrix
 *
 * Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.
 *
 * A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.
 *
 *
 * Example 1:
 *
 * Input: mat =
 * [[1,1,0,0,0],
 *  [1,1,1,1,0],
 *  [1,0,0,0,0],
 *  [1,1,0,0,0],
 *  [1,1,1,1,1]],
 * k = 3
 * Output: [2,0,3]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 2
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 2
 * row 4 -> 5
 * Rows ordered from the weakest to the strongest are [2,0,3,1,4]
 *
 * Example 2:
 *
 * Input: mat =
 * [[1,0,0,0],
 *  [1,1,1,1],
 *  [1,0,0,0],
 *  [1,0,0,0]],
 * k = 2
 * Output: [0,2]
 * Explanation:
 * The number of soldiers for each row is:
 * row 0 -> 1
 * row 1 -> 4
 * row 2 -> 1
 * row 3 -> 1
 * Rows ordered from the weakest to the strongest are [0,2,3,1]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] is either 0 or 1.
 */
public class TheKWeakestRowsInAMatrix {

    public int[] kWeakestRows(int[][] mat, int k) {

        return IntStream
                .range(0, mat.length)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), i -> (int) Arrays.stream(mat[i]).filter(x -> x == 1).count()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .mapToInt(Map.Entry::getKey)
                .limit(k)
                .toArray();
    }


    public int[] kWeakestRows2(int[][] mat, int k) {
        int[] buckets = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            buckets[i] = count(mat[i]) * 1000 + i;
        }
        sort(buckets, 0, mat.length - 1);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = buckets[i] % 1000;
        }
        return ans;
    }

    private int count(int[] lines) {
        int min = 0;
        int max = lines.length - 1;
        int index = -1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (lines[mid] == 0) {
                max = mid - 1;
            } else {
                min = mid + 1;
                if (index < mid) {
                    index = mid;
                }
            }
        }
        return index + 1;
    }

    private void sort(int[] buckets, int left, int right) {
        if (left > right) {
            return;
        }
        int num = buckets[left];
        int i = left;
        int j = right;
        while (i != j) {
            while (buckets[j] >= num && i < j) {
                j--;
            }
            while (buckets[i] <= num && i < j) {
                i++;
            }
            if (i < j) {
                int temp = buckets[i];
                buckets[i] = buckets[j];
                buckets[j] = temp;
            }
        }
        buckets[left] = buckets[i];
        buckets[i] = num;
        sort(buckets, left, i - 1);
        sort(buckets, i + 1, right);
    }
}
