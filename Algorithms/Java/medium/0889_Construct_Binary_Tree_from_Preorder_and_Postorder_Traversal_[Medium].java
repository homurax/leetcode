/**
 * 889. Construct Binary Tree from Preorder and Postorder Traversal
 *
 * Return any binary tree that matches the given preorder and postorder traversals.
 *
 * Values in the traversals pre and post are distinct positive integers.
 *
 * Example 1:
 *
 *             1
 *         2       3
 *       4   5   6   7
 *
 * Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 *
 *
 * Note:
 *
 * 1 <= pre.length == post.length <= 30
 * pre[] and post[] are both permutations of 1, 2, ..., pre.length.
 * It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * pre  NLR  1    245  367
     * post LRN  452  673  1
     *
     * left node -> pre[1] = post[leftNodeNum - 1]
     * left  -> pre[1, leftNodeNum + 1] post[0, leftNodeNum]
     * right -> pre[L + 1, N] post[L, N - 1]
     *
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {

        return build(pre, 0, post, 0, pre.length);
    }

    private TreeNode build(int[] pre, int offsetPre, int[] post, int offsetPost, int len) {

        if (len == 0) return null;
        TreeNode root = new TreeNode(pre[offsetPre]);
        if (len == 1) return root;

        int leftNodeNum = 1;
        while (leftNodeNum < len) {
            if (pre[offsetPre + 1] == post[offsetPost + leftNodeNum - 1]) break;
            leftNodeNum++;
        }

        root.left = build(pre, offsetPre + 1, post, offsetPost, leftNodeNum);
        root.right = build(pre, offsetPre + 1 + leftNodeNum, post, offsetPost + leftNodeNum, len - 1 - leftNodeNum);
        return root;
    }
}
