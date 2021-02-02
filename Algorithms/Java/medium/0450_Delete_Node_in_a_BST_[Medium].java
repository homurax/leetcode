/**
 * 450. Delete Node in a BST
 *
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Follow up: Can you solve it with time complexity O(height of tree)?
 *
 *
 *
 * Example 1:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 *
 * Example 3:
 *
 * Input: root = [], key = 0
 * Output: []
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the tree is in the range [0, 10^4].
 * 2. -10^5 <= Node.val <= 10^5
 * 3. Each node has a unique value.
 * 4. root is a valid binary search tree.
 * 5. -10^5 <= key <= 10^5
 */
public class DeleteNodeInABST {

    public class TreeNode {
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

    /**
     * 1. target 为叶子节点，直接删除。
     * 2. target 只有左子树（右子树），就令 target 的左子树（右子树）代替 target。
     * 3. target 的左子树和右子树均不空，在删去之后可按中序遍历保持有序进行调整。
     *  其一是令 target 的左子树为父节点的左/右子树（依 target 是父节点的左子树还是右子树而定），target 左子树的最右节点右子节点指向 target 的右子树。
     *  其二是令 target 的直接前驱（in-order predecessor）或直接后继（in-order successor）替代 target。
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode target = root, parent = null;
        while (target != null && target.val != key) {
            parent = target;
            if (key > target.val) {
                target = target.right;
            } else {
                target = target.left;
            }
        }
        // target 不存在
        if (target == null) {
            return root;
        }
        // target 为根
        if (target == root) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode prev = target.left;
            while (prev.right != null) {
                prev = prev.right;
            }
            prev.right = root.right;
            return root.left;
        }
        // target 为叶子
        if (target.left == target.right) {
            if (key > parent.val) {
                parent.right = null;
            } else {
                parent.left = null;
            }
            return root;
        }
        // target 单子树
        TreeNode child = null;
        if (target.left != null && target.right == null) {
            child = target.left;
        } else if (target.left == null) {
            child = target.right;
        }
        if (child != null) {
            if (key > parent.val) {
                parent.right = child;
            } else {
                parent.left = child;
            }
            return root;
        }
        // target 左右子树均不空
        TreeNode prev = target.left;
        while (prev.right != null) {
            prev = prev.right;
        }
        prev.right = target.right;
        if (key > parent.val) {
            parent.right = target.left;
        } else {
            parent.left = target.left;
        }
        return root;
    }


    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key > root.val) {
            root.right = deleteNode2(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode2(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                TreeNode successor = root.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                root.val = successor.val;
                root.right = deleteNode2(root.right, root.val);
            } else {
                TreeNode predecessor = root.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                root.val = predecessor.val;
                root.left = deleteNode2(root.left, root.val);
            }
        }
        return root;
    }


    public TreeNode deleteNode3(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key > root.val) {
            root.right = deleteNode3(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode3(root.left, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode prev = root.left;
            while (prev.right != null) {
                prev = prev.right;
            }
            root.val = prev.val;
            root.left = deleteNode3(root.left, prev.val);
        }
        return root;
    }


}
