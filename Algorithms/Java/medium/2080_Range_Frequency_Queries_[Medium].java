/**
 * 2080. Range Frequency Queries
 *
 *
 * Design a data structure to find the frequency of a given value in a given subarray.
 *
 * The frequency of a value in a subarray is the number of occurrences of that value in the subarray.
 *
 * Implement the RangeFreqQuery class:
 *
 * RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
 * int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
 * A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * Output
 * [null, 1, 2]
 *
 * Explanation
 * RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
 * rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
 * rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.
 *
 *
 * Constraints:
 *
 * 1. 1 <= arr.length <= 10^5
 * 2. 1 <= arr[i], value <= 10^4
 * 3. 0 <= left <= right < arr.length
 * 4. At most 10^5 calls will be made to query
 */
public class RangeFrequencyQueries {

    // freq[i] -> arr[i] 是第几次出现
    class RangeFreqQuery {

        private int[] arr;
        private int[] freq;

        public RangeFreqQuery(int[] arr) {
            int n = arr.length;
            this.arr = arr;
            this.freq = new int[n];
            int[] cnt = new int[10001];
            for (int i = 0; i < n; i++) {
                freq[i] = cnt[arr[i]]++;
            }
        }

        public int query(int left, int right, int value) {
            int l = right, r = left;
            for (int i = left; i <= right; i++) {
                if (arr[i] == value) {
                    l = i;
                    break;
                }
            }
            for (int i = right; i >= l; i--) {
                if (arr[i] == value) {
                    r = i;
                    break;
                }
            }
            return arr[l] != value ? 0 : freq[r] - freq[l] + 1;
        }
    }



    // 记录每一个值对应的下标 二分查边界
    /*class RangeFreqQuery {

        private Map<Integer, List<Integer>> map;

        public RangeFreqQuery(int[] arr) {
            map = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
            }
        }

        public int query(int left, int right, int value) {
            List<Integer> idxList = map.get(value);
            if (idxList == null || idxList.get(0) > right || idxList.get(idxList.size() - 1) < left) {
                return 0;
            }
            int l = Collections.binarySearch(idxList, left);
            l = l < 0 ? -l - 1 : l;
            int r = Collections.binarySearch(idxList, right);
            r = r < 0 ? -r - 2 : r;
            return r - l + 1;
        }
    }*/

}
