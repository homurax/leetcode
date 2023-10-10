/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 *
 *
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 *
 * Example 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

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


    private Map<Integer, Integer> inorderMap;
    private int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorderMap = new HashMap<>();
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(0, n - 1, 0);
    }

    private TreeNode build(int preLeftIdx, int preRightIdx, int inLeftIdx) {
        if (preLeftIdx > preRightIdx) {
            return null;
        }
        // 前序遍历第一个节点为跟
        int rootVal = preorder[preLeftIdx];
        int inRootIdx = inorderMap.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        int leftSubCnt = inRootIdx - inLeftIdx;
        // 先序遍历中 [左边界 + 1, 左边界 + leftSubCnt] 对应中序遍历中「从左边界开始到根节点定位 - 1」
        root.left = build(preLeftIdx + 1, preLeftIdx + leftSubCnt, inLeftIdx);
        // 先序遍历中 [左边界 + 1 + leftSubCnt, preRightIdx] 对应中序遍历中「从根节点定位 + 1 到右边界」
        root.right = build(preLeftIdx + leftSubCnt + 1, preRightIdx, inRootIdx + 1);
        return root;
    }

}
