/**
 * 1089. Duplicate Zeros
 *
 * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written.
 *
 * Do the above modifications to the input array in place, do not return anything from your function.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,0,2,3,0,4,5,0]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 * Example 2:
 *
 * Input: [1,2,3]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 *
 *
 * Note:
 *
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 */
public class DuplicateZeros {


    /**
     * 从后往前原地修改数组
     * 当写指针在数组范围内时 非0写一次 0写两次
     */
    public void duplicateZeros(int[] arr) {

        int length = arr.length;
        int count = 0;
        for (int i : arr) {
            if (i == 0) count++;
        }
        if (count == 0 || count == length)
            return;
        int index = length - 1;
        int write = length + count - 1;

        while (index >= 0 && write >= 0)  {

            if (arr[index] != 0) {
                if (write < length)
                    arr[write] = arr[index];
            } else {
                if (write < length)
                    arr[write] = arr[index];
                write--;
                if (write < length)
                    arr[write] = arr[index];
            }
            write--;
            index--;
        }
    }


    /**
     * 递归分治
     */
    public void duplicateZeros2(int[] arr) {
        helper(arr, 0, 0);
    }

    private void helper(int[] arr, int r, int w) {

        if (r >= arr.length || w >= arr.length)
            return;

        if (arr[r] == 0)
            w++;

        helper(arr, r + 1, w + 1);

        if (w < arr.length)
            arr[w] = arr[r];
        if (arr[r] == 0)
            arr[w - 1] = 0;
    }
}
