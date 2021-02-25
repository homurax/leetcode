/**
 * 1054. Distant Barcodes
 *
 *
 * In a warehouse, there is a row of barcodes, where the ith barcode is barcodes[i].
 *
 * Rearrange the barcodes so that no two adjacent barcodes are equal. You may return any answer, and it is guaranteed an answer exists.
 *
 *
 * Example 1:
 *
 * Input: barcodes = [1,1,1,2,2,2]
 * Output: [2,1,2,1,2,1]
 *
 * Example 2:
 *
 * Input: barcodes = [1,1,1,1,2,2,3,3]
 * Output: [1,3,1,3,1,2,1,2]
 *
 *
 * Constraints:
 *
 * 1. 1 <= barcodes.length <= 10000
 * 2. 1 <= barcodes[i] <= 10000
 */
public class DistantBarcodes {

    public int[] rearrangeBarcodes(int[] barcodes) {
        int[] cnt = new int[10001];
        for (int barcode : barcodes) {
            cnt[barcode]++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> arr2[1] - arr1[1]);
        for (int i = 1; i < 10001; i++) {
            if (cnt[i] > 0) {
                pq.offer(new int[]{i, cnt[i]});
            }
        }
        int write = 0;
        while (!pq.isEmpty()) {
            int[] node1 = pq.poll();
            int[] node2 = pq.poll();

            barcodes[write++] = node1[0];
            if (--node1[1] > 0) {
                pq.offer(node1);
            }
            if (node2 != null) {
                barcodes[write++] = node2[0];
                if (--node2[1] > 0) {
                    pq.offer(node2);
                }
            }
        }
        return barcodes;
    }

    public int[] rearrangeBarcodes2(int[] barcodes) {
        for (int i = 1; i < barcodes.length; i++) {
            int next = i + 1;
            if (barcodes[i] == barcodes[i - 1]) {
                while (next < barcodes.length && barcodes[next] == barcodes[i]) {
                    next++;
                }
                if (next < barcodes.length) {
                    int temp = barcodes[next];
                    barcodes[next] = barcodes[i];
                    barcodes[i] = temp;
                }
            }
        }

        for (int i = barcodes.length - 2; i >= 0; i--) {
            int prev = i - 1;
            if (barcodes[i] == barcodes[i + 1]) {
                while (prev >= 0 && barcodes[prev] == barcodes[i]) {
                    prev--;
                }
                if (prev >= 0) {
                    int temp = barcodes[prev];
                    barcodes[prev] = barcodes[i];
                    barcodes[i] = temp;
                }
            }
        }
        return barcodes;
    }
}
