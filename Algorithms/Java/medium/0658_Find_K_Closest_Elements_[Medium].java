/**
 * 658. Find K Closest Elements
 *
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 *
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * 1. 1 <= k <= arr.length
 * 2. 1 <= arr.length <= 10^4
 * 3. arr is sorted in ascending order.
 * 4. -10^4 <= arr[i], x <= 10^4
 */
public class FindKClosestElements {

    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        List<Integer> ans = Arrays.stream(arr).boxed()
                .sorted((a, b) -> a.equals(b) ? 0 : Math.abs(a - x) - Math.abs(b - x))
                .collect(Collectors.toList());
        ans = ans.subList(0, k);
        Collections.sort(ans);
        return ans;
    }


    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>(k);
        if (x <= arr[0]) {
            for (int i = 0; i < k; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }
        if (x >= arr[arr.length - 1]) {
            for (int i = arr.length - k; i < arr.length; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }
        int left = 0, right = arr.length - 1;
        int d = arr.length - k;
        while (d > 0) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }
            d--;
        }
        for (int i = left; i < left + k; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }


    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }


}
