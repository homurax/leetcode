/**
 * 973. K Closest Points to Origin
 *
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 *
 * Note:
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class KClosestPointsToOrigin {


    public int[][] kClosest(int[][] points, int K) {

        int[][] ans = new int[K][];
        List<int[]> dataList = new ArrayList<>();
        Collections.addAll(dataList, points);
        dataList.sort(Comparator.comparing(this::distance));
        for (int i = 0; i < K; i++) {
            ans[i] = dataList.get(i);
        }
        return ans;
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    // ------------------------------------------------------------------------

    private int[][] points;
    public int[][] kClosest2(int[][] points, int K) {
        this.points = points;
        sort(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void sort(int i, int j, int K) {
        if (i >= j) {
            return;
        }
        // 随机选择一个关键元素
        int k = ThreadLocalRandom.current().nextInt(i, j);
        swap(i, k);

        int mid = partition(i, j);
        int leftLength = mid - i + 1;
        if (K < leftLength) {
            sort(i, mid - 1, K);
        } else if (K > leftLength) {
            sort(mid + 1, j, K - leftLength);
        }
    }

    private int partition(int i, int j) {
        int oi = i;
        int pivot = dist(i);
        i++;
        while (true) {
            while (i < j && dist(i) < pivot) {
                i++;
            }
            while (i <= j && dist(j) > pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(i, j);
        }
        swap(oi, j);
        return j;
    }

    private int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    private void swap(int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    // ------------------------------------------------------------------------

    public int[][] kClosest3(int[][] points, int K) {
        quickSort(points, 0, points.length - 1, K);
        return Arrays.copyOf(points, K);
    }

    private void quickSort(int[][] points, int l, int r, int k) {
        // small -- pivot -- large
        int[] pivot = points[l];
        int i = l;
        int j = r;
        while (i <= j) {
            while (i <= j && compare(points[i], pivot) < 0) {
                i++;
            }
            while (i <= j && compare(pivot, points[j]) < 0) {
                j--;
            }
            if (i <= j) { // swap
                int[] tmp = points[i];
                points[i] = points[j];
                points[j] = tmp;
                i++;
                j--;
            }
        }
        if (j - l + 1 >= k) {
            quickSort(points, l, j, k);
        }
        if ((i - 1) - l + 1 < k) {
            quickSort(points, i, r, k - (i - l));
        }
    }

    private int compare(int[] arr1, int[] arr2) {
        return (arr1[0] * arr1[0] + arr1[1] * arr1[1]) - (arr2[0] * arr2[0] + arr2[1] * arr2[1]);
    }

}
