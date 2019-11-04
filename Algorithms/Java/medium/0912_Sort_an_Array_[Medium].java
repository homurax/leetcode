/**
 * 912. Sort an Array
 *
 * Given an numsay of integers nums, sort the numsay in ascending order.
 *
 * Example 1:
 *
 * Input: [5,2,3,1]
 * Output: [1,2,3,5]
 *
 * Example 2:
 *
 * Input: [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * -50000 <= A[i] <= 50000
 */
public class SortAnArray {

    /* 稳定排序 */

    /**
     * 冒泡排序
     * О(n²)
     */
    public int[] sortArray(int[] nums) {
        boolean changed = true;
        for (int i = 0; i < nums.length && changed; i++) {
            changed = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    nums[j] = nums[j] ^ nums[j + 1];
                    nums[j + 1] = nums[j] ^ nums[j + 1];
                    nums[j] = nums[j] ^ nums[j + 1];
                    changed = true;
                }
            }
        }
        return nums;
    }

    /**
     * 鸡尾酒排序/定向冒泡排序
     * 以双向在序列中进行排序
     * 在随机数序列的状态下，鸡尾酒排序与冒泡排序的效率都很差劲
     * О(n²)
     */
    public int[] sortArray2(int[] nums) {

        int low = 0, high = nums.length - 1;
        while (low < high) {
            for (int i = low; i < high; i++) {
                if (nums[i] > nums[i + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                }
            }
            high--;
            for (int i = high; i > low; i--) {
                if (nums[i - 1] > nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                }
            }
            low++;
        }
        return nums;
    }

    /**
     * 插入排序
     * О(n²)
     */
    public int[] sortArray3(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            int pick = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > pick) {
                nums[j + 1] = nums[j];
                j--;
            }
            /*for (; j >= 0 && nums[j] > pick; j--) {
                nums[j + 1] = nums[j];
            }*/
            nums[j + 1] = pick;
        }
        return nums;
    }


    /**
     * 桶排序
     * О(n²)
     */
    public int[] sortArray4(int[] nums) {

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        // int bucketNum = max / 10 - min / 10 + 1;
        int[] buckets = new int[max - min + 1];
        for (int num : nums) {
            // (num - min) / 10
            buckets[num - min]++;
        }

        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i]; j++) {
                nums[index++] = min + i;
            }
        }

        return nums;
    }

    /**
     * 归并排序
     * 分治法的典型应用
     * О(n log n)
     */
    public int[] sortArray5(int[] nums) {

        int[] result = new int[nums.length];
        sort(nums, result, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] source, int[] result, int low, int high) {

        if (low >= high) {
            return;
        }

        int mid = low + ((high - low) >> 1);
        sort(source, result, low, mid);
        sort(source, result, mid + 1, high);

        int index = low;
        int low1 = low, high1 = mid;
        int low2 = mid + 1, high2 = high;

        while (low1 <= high1 && low2 <= high2) {
            result[index++] = source[low1] < source[low2] ? source[low1++] : source[low2++];
        }
        while (low1 <= high1) {
            result[index++] = source[low1++];
        }
        while (low2 <= high2) {
            result[index++] = source[low2++];
        }
        for (index = low; index <= high; index++) {
            source[index] = result[index];
        }
    }


    /**
     * 原地归并
     */
    public int[] sortArray5InPlace(int[] nums) {
        sortInPlace(nums, 0, nums.length - 1);
        return nums;
    }

    private void sortInPlace(int[] nums, int low, int high) {

        if (low >= high) return;

        int mid = low + ((high - low) >> 1);
        sortInPlace(nums, low, mid);
        sortInPlace(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        while (i < j && j <= high) {
            while (i < j && nums[i] <= nums[j]) {
                i++;
            }
            int index = j;
            while (j <= high && nums[j] <= nums[i]) {
                j++;
            }
            // nums[low, i - 1] < nums[i, mid] > nums[mid + 1, j - 1]
            swap(nums, i, index - 1);
            swap(nums, index, j - 1);
            swap(nums, i, j - 1);
            i += (j - index);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i < 0 || i > j) return;
        for (; i < j; i++, j--) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
    }


    /* 不稳定排序 */

    /**
     * 选择排序
     * О(n²)
     */
    public int[] sortArray6(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = temp;
            }
        }

        return nums;
    }

    /**
     * 希尔排序
     * 递减增量排序算法 插入排序的一种更高效的改进版本
     * О(n log²n)
     *
     * 步长推荐
     * 1, 5, 19, 41, 109,...
     * 1, 9, 34, 182, 836, 4025, 19001, 90358, 428481, 2034035, 9651787, 45806244, 217378076, 1031612713,…
     */
    public int[] sortArray7(int[] nums) {

        int step = nums.length >> 1;
        while (step >= 1) {
            for (int i = step; i < nums.length; i++) {
                int pick = nums[i];
                int j = i - step;
                while (j >= 0 && nums[j] > pick) {
                    nums[j + step] = nums[j];
                    j = j - step;
                }
                nums[j + step] = pick;
            }
            step = step >> 1; // 减少步长
        }
        return nums;
    }


    /**
     * 快速排序
     * О(n log n)
     */
    public int[] sortArray8(int[] nums) {

        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] arr, int low, int high) {

        if (low >= high || arr.length <= 1) {
            return;
        }

        int mid = low + ((high - low) >> 1);
        int i = low, j = high, pivot = arr[mid];
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            } else if (i == j) {
                i++;
            }
        }
        sort(arr, low, j);
        sort(arr, i, high);
    }

}
