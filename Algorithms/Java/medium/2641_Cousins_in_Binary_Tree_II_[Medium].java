/**
 * 2641. Cousins in Binary Tree II
 *
 *
 * Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.
 *
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 *
 * Return the root of the modified tree.
 *
 * Note that the depth of a node is the number of edges in the path from the root node to it.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,9,1,10,null,7]
 * Output: [0,0,0,7,7,null,11]
 * Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
 * - Node with value 5 does not have any cousins so its sum is 0.
 * - Node with value 4 does not have any cousins so its sum is 0.
 * - Node with value 9 does not have any cousins so its sum is 0.
 * - Node with value 1 has a cousin with value 7 so its sum is 7.
 * - Node with value 10 has a cousin with value 7 so its sum is 7.
 * - Node with value 7 has cousins with values 1 and 10 so its sum is 11.
 *
 *
 * Example 2:
 *
 *
 * Input: root = [3,1,2]
 * Output: [0,0,0]
 * Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
 * - Node with value 3 does not have any cousins so its sum is 0.
 * - Node with value 1 does not have any cousins so its sum is 0.
 * - Node with value 2 does not have any cousins so its sum is 0.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^4
 */
public class CousinsInBinaryTreeII {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // node.val 更新为这一层全部节点值的和 - 自己及兄弟节点的和
    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int nextLevelSum = 0;
            List<TreeNode> temp = list;
            list = new ArrayList<>();
            for (TreeNode node : temp) {
                if (node.left != null) {
                    list.add(node.left);
                    nextLevelSum += node.left.val;
                }
                if (node.right != null) {
                    list.add(node.right);
                    nextLevelSum += node.right.val;
                }
            }
            for (TreeNode node : temp) {
                int childrenSum = (node.left != null ? node.left.val : 0) + (node.right != null ? node.right.val : 0);
                if (node.left != null) {
                    node.left.val = nextLevelSum - childrenSum;
                }
                if (node.right != null) {
                    node.right.val = nextLevelSum - childrenSum;
                }
            }
        }
        return root;
    }

}
