/**
 * 1424. Diagonal Traverse II
 *
 *
 * Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
 *
 *
 * Example 1:
 *
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 *
 * Example 2:
 *
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 *
 * Example 3:
 *
 * Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 * Output: [1,4,2,5,3,8,6,9,7,10,11]
 *
 * Example 4:
 *
 * Input: nums = [[1,2,3,4,5,6]]
 * Output: [1,2,3,4,5,6]
 *
 *
 * Constraints:
 *
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i].length <= 10^5
 * 3. 1 <= nums[i][j] <= 10^9
 * 4. There at most 10^5 elements in nums.
 */
public class DiagonalTraverseII {

    // Time Limit Exceeded
    public int[] findDiagonalOrder1(List<List<Integer>> nums) {
        List<Integer> collect = new ArrayList<>();
        int[] maxSizeRecord = new int[nums.size()];
        int curMaxSize = 0;
        for (int i = 0; i < nums.size(); i++) {
            maxSizeRecord[i] = curMaxSize = Math.max(curMaxSize, nums.get(i).size());
            collect(nums, collect, maxSizeRecord, i, 0);
        }
        for (int j = 1; j < maxSizeRecord[nums.size() - 1]; j++) {
            collect(nums, collect, maxSizeRecord, nums.size() - 1, j);
        }
        int[] arr = new int[collect.size()];
        for (int i = 0; i < collect.size(); i++) {
            arr[i] = collect.get(i);
        }
        return arr;
    }

    private void collect(List<List<Integer>> nums, List<Integer> collect, int[] maxSizeRecord, int i, int j) {
        while (i >= 0) {
            if (j >= maxSizeRecord[i]) {
                break;
            }
            List<Integer> row = nums.get(i);
            if (j < row.size()) {
                collect.add(row.get(j));
            }
            j++;
            i--;
        }
    }


    public int[] findDiagonalOrder2(List<List<Integer>> nums) {
        int length = 0;
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);
            length += row.size();
            for (int j = 0; j < row.size(); j++) {
                // List<Integer> temp = map.getOrDefault(i + j, new ArrayList<>());
                // temp.add(nums.get(i).get(j));
                // map.putIfAbsent(i + j, temp);
                if (map.containsKey(i + j)) {
                    map.get(i + j).add(row.get(j));
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(row.get(j));
                    map.put(i + j, list);
                }
            }
        }
        int[] ans = new int[length];
        int index = 0;
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int j = list.size() - 1; j >= 0; j--) {
                ans[index++] = list.get(j);
            }
        }
        return ans;
    }


    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<Integer> list = new ArrayList<>();
        int[] next = new int[nums.size()];
        int head = -1;
        for (int i = 0; i < nums.size() || head != -1; i++) {
            if (i < nums.size()) {
                next[i] = head;
                head = i;
            }
            int cur = head;
            int pre = -1;
            while (cur != -1) {
                if (nums.get(cur).size() == i - cur) {
                    if (pre == -1) {
                        head = next[cur];
                    } else {
                        next[pre] = next[cur];
                    }
                } else {
                    list.add(nums.get(cur).get(i - cur));
                    pre = cur;
                }
                cur = next[cur];
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }


}
