/**
 * 215. Kth Largest Element in an Array
 *
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1. 1 <= k <= nums.length <= 10^4
 * 2. -10^4 <= nums[i] <= 10^4
 */
public class KthLargestElementInAnArray {

    // 排序
    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // 优先队列
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.peek();
    }


    // 基于快排
    public int findKthLargest(int[] nums, int k) {
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }

    public int partition(int[] nums, int l, int r, int target) {
        // l 至 r 之间随机选择
        int randomIndex = ThreadLocalRandom.current().nextInt(r - l + 1) + l;
        // 将 pivot 换倒最后
        swap(nums, r, randomIndex);
        int pivot = nums[r];
        int pivotIdx = l;
        for (int curIdx = l; curIdx < r; curIdx++) {
            if (nums[curIdx] <= pivot) {
                swap(nums, pivotIdx++, curIdx);
            }
        }
        // 将 pivot 归位
        swap(nums, pivotIdx, r);
        if (pivotIdx < target) {
            return partition(nums, pivotIdx + 1, r, target);
        } else if (pivotIdx > target) {
            return partition(nums, l, pivotIdx - 1, target);
        } else {
            return nums[target];
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
