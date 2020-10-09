/**
 * 863. All Nodes Distance K in Binary Tree
 *
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 *
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 *
 * Output: [7,4,1]
 *
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 *
 *
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 *
 * Note:
 *
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
 */
public class AllNodesDistanceKInBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    private List<Integer> ans;
    private TreeNode target;
    private int K;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        this.target = target;
        this.K = K;
        this.ans = new LinkedList<>();
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return -1;
        }
        else if (node == target) {
            subtreeAdd(node, 0);
            return 1;
        }
        else {
            int L = dfs(node.left);
            int R = dfs(node.right);
            if (L != -1) {
                if (L == K) {
                    ans.add(node.val);
                }
                subtreeAdd(node.right, L + 1);
                return L + 1;
            } else if (R != -1) {
                if (R == K) {
                    ans.add(node.val);
                }
                subtreeAdd(node.left, R + 1);
                return R + 1;
            } else {
                return -1;
            }
        }
    }

    private void subtreeAdd(TreeNode node, int dist) {
        if (node == null) {
            return;
        }
        if (dist == K) {
            ans.add(node.val);
        }
        else {
            subtreeAdd(node.left, dist + 1);
            subtreeAdd(node.right, dist + 1);
        }
    }


}
