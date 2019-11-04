/**
 * 1122. Relative Sort Array
 *
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 * Example 1:
 *
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 *
 * Constraints:
 *
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 */
public class RelativeSortArray {


    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        int write = 0;
        for (int target : arr2) {
            for (int i = write; i < arr1.length; i++) {
                if (arr1[i] == target) {
                    if (i != write) {
                        int temp = arr1[write];
                        arr1[write] = arr1[i];
                        arr1[i] = temp;

                    }
                    write++;
                }
            }
        }

        if (write < arr1.length) {
            Arrays.sort(arr1, write, arr1.length);
        }

        return arr1;
    }


    public int[] relativeSortArray2(int[] arr1, int[] arr2) {

        int[] ans = new int[arr1.length];

        int[] table = new int[1001];
        for (int num : arr1) {
            table[num]++;
        }

        int write = 0;
        for (int target : arr2) {
            while (table[target] > 0) {
                ans[write++] = target;
                table[target] -= 1;
            }
        }
        for (int i = 0; i < 1001; i++) {
            while (table[i] > 0) {
                ans[write++] = i;
                table[i] -= 1;
            }
        }

        return ans;
    }

}
