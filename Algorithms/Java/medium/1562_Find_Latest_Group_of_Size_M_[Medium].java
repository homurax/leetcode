/**
 * 1562. Find Latest Group of Size M
 *
 *
 * Given an array arr that represents a permutation of numbers from 1 to n.
 *
 * You have a binary string of size n that initially has all its bits set to zero. At each step i (assuming both the binary string and arr are 1-indexed) from 1 to n, the bit at position arr[i] is set to 1.
 *
 * You are also given an integer m. Find the latest step at which there exists a group of ones of length m. A group of ones is a contiguous substring of 1's such that it cannot be extended in either direction.
 *
 * Return the latest step at which there exists a group of ones of length exactly m. If no such group exists, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,5,1,2,4], m = 1
 * Output: 4
 * Explanation:
 * Step 1: "00100", groups: ["1"]
 * Step 2: "00101", groups: ["1", "1"]
 * Step 3: "10101", groups: ["1", "1", "1"]
 * Step 4: "11101", groups: ["111", "1"]
 * Step 5: "11111", groups: ["11111"]
 * The latest step at which there exists a group of size 1 is step 4.
 *
 *
 * Example 2:
 *
 * Input: arr = [3,1,5,4,2], m = 2
 * Output: -1
 * Explanation:
 * Step 1: "00100", groups: ["1"]
 * Step 2: "10100", groups: ["1", "1"]
 * Step 3: "10101", groups: ["1", "1", "1"]
 * Step 4: "10111", groups: ["1", "111"]
 * Step 5: "11111", groups: ["11111"]
 * No group of size 2 exists during any step.
 *
 *
 * Constraints:
 *
 * 1. n == arr.length
 * 2. 1 <= m <= n <= 10^5
 * 3. 1 <= arr[i] <= n
 * 4. All integers in arr are distinct.
 */
public class FindLatestGroupOfSizeM {

    // 记录长度 同样只需要考虑更新左右端点
    // 发生长度变化时才需要考虑记录 step
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) {
            return n;
        }
        int ans = -1;
        int[] length = new int[n + 2];
        for (int i = 0; i < n; i++) {
            int index = arr[i];
            int leftLen = length[index - 1];
            int rightLen = length[index + 1];
            length[index - leftLen] = length[index + rightLen] = leftLen + rightLen + 1;
            if (leftLen == m || rightLen == m) {
                ans = i;
            }
        }
        return ans;
    }

    // 二维数组代替 TreeMap
    // 左右更新只需要考虑两边范围的端点 不需要更新范围中间的记录
    public static int findLatestStep2(int[] arr, int m) {
        int n = arr.length;
        if (m == n) {
            return n;
        }
        int[][] range = new int[n + 1][2];
        for (int[] point : range) {
            Arrays.fill(point, -1);
        }
        int ans = -1, count = 0;
        for (int i = 0; i < n; i++) {
            int l = arr[i], r = arr[i];
            if (arr[i] > 1 && range[arr[i] - 1][0] != -1) {
                int[] leftPoint = range[arr[i] - 1];
                l = leftPoint[0];
                if (leftPoint[1] - leftPoint[0] + 1 == m) {
                    count--;
                }
            }
            if (arr[i] < n && range[arr[i] + 1][1] != -1) {
                int[] rightPoint = range[arr[i] + 1];
                r = rightPoint[1];
                if (rightPoint[1] - rightPoint[0] + 1 == m) {
                    count--;
                }
            }
            if (r - l + 1 == m) {
                count++;
            }
            if (count > 0) {
                ans = i + 1;
            }
            range[l][0] = range[r][0] = l;
            range[l][1] = range[r][1] = r;
        }
        return ans;
    }


    // 动态更新每种长度的数量
    public static int findLatestStep1(int[] arr, int m) {
        int n = arr.length;
        if (m == n) {
            return n;
        }
        int[] count = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int index = arr[i];
            Integer lowStart = null, lowEnd = null, higherStart = null, higherEnd = null;
            Map.Entry<Integer, Integer> lowerEntry = map.lowerEntry(index);
            if (lowerEntry != null) {
                lowStart = lowerEntry.getKey();
                lowEnd = lowerEntry.getValue();
            }
            Map.Entry<Integer, Integer> higherEntry = map.higherEntry(index);
            if (higherEntry != null) {
                higherStart = higherEntry.getKey();
                higherEnd = higherEntry.getValue();
            }
            if (lowerEntry != null && higherEntry != null && lowEnd + 1 == higherStart - 1) {
                map.put(lowStart, higherEnd);
                map.remove(higherStart);
                count[lowEnd - lowStart]--;
                count[higherEnd - higherStart]--;
                count[higherEnd - lowStart]++;
            } else if (lowerEntry != null && lowEnd + 1 == index) {
                map.put(lowStart, index);
                count[lowEnd - lowStart]--;
                count[index - lowStart]++;
            } else if (higherEntry != null && higherStart - 1 == index) {
                map.remove(higherStart);
                map.put(index, higherEnd);
                count[higherEnd - higherStart]--;
                count[higherEnd - index]++;
            } else {
                map.put(index, index);
                count[0]++;
            }
            if (count[m - 1] > 0) {
                ans = i + 1;
            }
        }
        return ans;
    }

}
