/**
 * 2471. Minimum Number of Operations to Sort a Binary Tree by Level
 * <p>
 * <p>
 * You are given the root of a binary tree with unique values.
 * <p>
 * In one operation, you can choose any two nodes at the same level and swap their values.
 * <p>
 * Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
 * <p>
 * The level of a node is the number of edges along the path between it and the root node.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
 * Output: 3
 * Explanation:
 * - Swap 4 and 3. The 2nd level becomes [3,4].
 * - Swap 7 and 5. The 3rd level becomes [5,6,8,7].
 * - Swap 8 and 7. The 3rd level becomes [5,6,7,8].
 * We used 3 operations so return 3.
 * It can be proven that 3 is the minimum number of operations needed.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,3,2,7,6,5,4]
 * Output: 3
 * Explanation:
 * - Swap 3 and 2. The 2nd level becomes [2,3].
 * - Swap 7 and 4. The 3rd level becomes [4,6,5,7].
 * - Swap 6 and 5. The 3rd level becomes [4,5,6,7].
 * We used 3 operations so return 3.
 * It can be proven that 3 is the minimum number of operations needed.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6]
 * Output: 0
 * Explanation: Each level is already sorted in increasing order so return 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^5
 * All the values of the tree are unique.
 */
public class MinimumNumberOfOperationsToSortABinaryTreeByLevel {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    // 在数组 [2,0,1,4,3] 中，[2,0,1] 和 [4,3] 分别是两个置换环，环与环之间是数字是不需要发生交换的，只会在环内发生交换。
    // 怎么找到环呢？从第一个数开始，把这个数字当成下标去访问数组，不断循环直到回到这个数本身。
    // 只需要计算每个环内需要多少次交换。
    // 对于每个环，交换次数为环的大小减一，即数组长度 - 环数。
    public int minimumOperations(TreeNode root) {
        int ans = 0;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int n = list.size();
            List<TreeNode> temp = list;
            list = new ArrayList<>();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                TreeNode node = temp.get(i);
                nums[i] = node.val;
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }
            }
            int[] copy = Arrays.copyOf(nums, n);
            Arrays.sort(copy);
            Map<Integer, Integer> idxMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                idxMap.put(copy[i], i);
            }
            // 交换数
            /*for (int i = 0; i < n; i++) {
                while (nums[i] != copy[i]) {
                    int j = idxMap.get(nums[i]);
                    nums[i] ^= nums[j];
                    nums[j] ^= nums[i];
                    nums[i] ^= nums[j];
                    ans++;
                }
            }*/
            // 成环数
            ans += n;
            boolean[] flag = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!flag[i]) {
                    for (int j = i; !flag[j]; j = idxMap.get(nums[j])) {
                        flag[j] = true;
                    }
                    ans--;
                }
            }
        }
        return ans;
    }


    public int minChanges(int[] nums) {
        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);
        HashMap<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idxMap.put(copy[i], i);
        }
        boolean[] flag = new boolean[n];
        int loop = 0;
        for (int i = 0; i < n; i++) {
            if (!flag[i]) {
                for (int j = i; !flag[j]; j = idxMap.get(nums[j])) {
                    flag[j] = true;
                }
                loop++;
            }
        }
        return n - loop;
    }

}
