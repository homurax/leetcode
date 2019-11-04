/**
 * 993. Cousins in Binary Tree
 *
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 *
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 *
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 *
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 * Example 2:
 *
 *
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 * Example 3:
 *
 *
 *
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 *
 *
 * Note:
 *
 * The number of nodes in the tree will be between 2 and 100.
 * Each node has a unique integer value from 1 to 100.
 */
public class CousinsInBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * while中取出的node均为同一节点 取出时记录值
     * 每取出一层时就判断一次
     */
    public boolean isCousins(TreeNode root, int x, int y) {

        if (root == null || root.val == x || root.val == y) {
            return false;
        }

        int[] ints = new int[101];
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);

        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                int val = poll.val;
                ints[val]++;
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
                if ((poll.left != null && poll.right != null)
                        && (poll.left.val == x && poll.right.val == y || poll.left.val == y && poll.right.val == x)) {
                    return false;
                }
            }
            if (ints[x] > 0 && ints[y] > 0) {
                return true;
            } else if (ints[x] > 0 && ints[y] == 0) {
                return false;
            } else if (ints[x] == 0 && ints[y] > 0) {
                return false;
            }
        }

        return false;
    }


    /**
     * 建立值与深度、值与父节点的映射 然后在判断
     */
    private Map<Integer, Integer> depth;
    private Map<Integer, TreeNode> parent;

    public boolean isCousins2(TreeNode root, int x, int y) {
        depth = new HashMap<>();
        parent = new HashMap<>();
        dfs(root, null);
        return (depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y));
    }

    private void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            depth.put(node.val, par != null ? 1 + depth.get(par.val) : 0);
            parent.put(node.val, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}
