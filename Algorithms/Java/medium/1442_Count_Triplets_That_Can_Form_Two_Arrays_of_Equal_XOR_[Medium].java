/**
 * 1442. Count Triplets That Can Form Two Arrays of Equal XOR
 *
 * Given an array of integers arr.
 *
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 *
 * Let's define a and b as follows:
 *
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 *
 * Return the number of triplets (i, j and k) Where a == b.
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,1,6,7]
 * Output: 4
 * Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
 *
 * Example 2:
 *
 * Input: arr = [1,1,1,1,1]
 * Output: 10
 *
 * Example 3:
 *
 * Input: arr = [2,3]
 * Output: 0
 *
 * Example 4:
 *
 * Input: arr = [1,3,5,7,9]
 * Output: 3
 *
 * Example 5:
 *
 * Input: arr = [7,11,12,9,5,2,7,17,22]
 * Output: 8
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXOR {

    public static int countTriplets(int[] arr) {
        int ans = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int xor = arr[i];
            for (int k = i + 1; k < len; k++) {
                if ((xor ^= arr[k]) == 0) {
                    ans += k - i;
                }
            }
        }
        return ans;
    }


    /**
     * xor[i] = arr[0] ^ arr[1] ^ ... ^ arr[i]
     *
     *      arr[i] ^ arr[i+1] ^ ... ^ arr[k] == 0
     * ->   arr[0] ^ arr[1] ^ ... ^ arr[i - 1] ^ (arr[i] ^ ... ^ arr[k]) == arr[0] ^ arr[1] ^ ... ^ arr[i - 1]
     * ->   xor[k] == xor[i-1]
     */
    public static int countTriplets2(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> base = new ArrayList<>();
        base.add(-1);
        map.put(0, base);
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                map.get(xor).add(i);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                map.put(xor, temp);
            }
        }
        int ans = 0;
        /*for (List<Integer> list : map.values()) {
            for (int idx = 0; idx < list.size() - 1; idx++) {
                int i = list.get(idx) + 1;
                for (int j = idx + 1; j < list.size(); j++) {
                    int k = list.get(j);
                    ans += k - i;
                }
            }
        }*/
        for (List<Integer> list : map.values()) {
            List<Integer> temp = new ArrayList<>(list);
            for (int i = 1; i < temp.size(); i++) {
                temp.set(i, temp.get(i) + temp.get(i - 1));
            }
            int end = temp.size() - 1;
            for (int i = 0; i < end; i++) {
                ans += temp.get(end) - temp.get(i) - (end - i) * (list.get(i) + 1);
            }
        }
        return ans;
    }


    public static int countTriplets3(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> base = new ArrayList<>();
        base.add(1);
        base.add(-1);
        base.add(0);
        map.put(0, base);
        int xor = 0, ans = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                List<Integer> temp = map.get(xor);
                int n = temp.get(0);
                int lastK = temp.get(1);
                int sum = temp.get(2);
                sum += n * (i - lastK) - 1;
                ans += sum;
                temp.set(0, n + 1);
                temp.set(1, i);
                temp.set(2, sum);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(1);
                temp.add(i);
                temp.add(0);
                map.put(xor, temp);
            }
        }
        return ans;
    }

}
