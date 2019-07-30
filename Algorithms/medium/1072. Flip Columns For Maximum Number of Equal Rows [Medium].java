/**
 * 1072. Flip Columns For Maximum Number of Equal Rows
 *
 * Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.
 *
 * Return the maximum number of rows that have all values equal after some number of flips.
 *
 * Example 1:
 * Input: [[0,1],[1,1]]
 * Output: 1
 * Explanation: After flipping no values, 1 row has all values equal.
 *
 * Example 2:
 * Input: [[0,1],[1,0]]
 * Output: 2
 * Explanation: After flipping values in the first column, both rows have equal values.
 *
 * Example 3:
 * Input: [[0,0,0],[0,0,1],[1,1,0]]
 * Output: 2
 * Explanation: After flipping values in the first two columns, the last two rows have equal values.
 *
 *
 * Note:
 *
 * 1 <= matrix.length <= 300
 * 1 <= matrix[i].length <= 300
 * All matrix[i].length's are equal
 * matrix[i][j] is 0 or 1
 */
public class FlipColumnsForMaximumNumberOfEqualRows {


    /**
     * 以matrix[i]作为特征 找出出现最多次的特征
     * matrix[i].length 足够短的话
     * int key1 = (matrix[i] to int);
     * int key2 = key1 ^ 1;
     */
    public int maxEqualRowsAfterFlips(int[][] matrix) {

        Map<String, Integer> map = new HashMap<>();
        int len = matrix[0].length;
        char[] chars1 = new char[len];
        char[] chars2 = new char[len];
        for (int[] ints : matrix) {
            for (int i = 0; i < len; i++) {
                if (ints[i] == 0) {
                    chars1[i] = 48;
                    chars2[i] = 49;
                } else {
                    chars1[i] = 49;
                    chars2[i] = 48;
                }
            }
            String key1 = new String(chars1);
            String key2 = new String(chars2);
            if (map.containsKey(key1)) {
                map.put(key1, map.get(key1) + 1);
            } else if (map.containsKey(key2)) {
                map.put(key2, map.get(key2) + 1);
            } else {
                map.put(key1, 1);
            }
        }
        int max = Integer.MIN_VALUE;
        for (Integer value : map.values()) {
            max = Math.max(max, value);
        }
        return max;
    }


    /**
     * 重写hash和equals
     */
    static class Key {

        final int hash;
        final int value;

        private Key(int[] row) {
            int h = 0, val = 0;
            for (int v : row) {
                if (row[0] != 0) {
                    v ^= 1;
                }
                h = h * 31 + v;
                val = val * 33 + v;
            }
            this.hash = h;
            this.value = val;
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object x) {
            return ((Key) x).value == this.value;
        }
    }

    public int maxEqualRowsAfterFlips2(int[][] matrix) {
        Map<Key, Integer> map = new HashMap<>();
        for (int[] ints : matrix) {
            map.merge(new Key(ints), 1, Integer::sum);
        }
        int max = Integer.MIN_VALUE;
        for (Integer value : map.values()) {
            max = Math.max(max, value);
        }
        return max;
    }
}
