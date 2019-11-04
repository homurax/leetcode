/**
 * 894. All Possible Full Binary Trees
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.
 *
 * Each node of each tree in the answer must have node.val = 0.
 *
 * You may return the final list of trees in any order.
 *
 *
 * Example 1:
 *
 * Input: 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * Explanation:
 *
 *
 * Note:
 *
 * 1 <= N <= 20
 */
public class AllPossibleFullBinaryTrees {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private Map<Integer, List<TreeNode>> map = new HashMap<>();

    /**
     * F(N) = F(x) + F(N - x -1)
     */
    public List<TreeNode> allPossibleFBT(int N) {

        if (map.containsKey(N)) {
            return map.get(N);
        } else {
            List<TreeNode> ans = new LinkedList<>();
            if (N == 1) {
                ans.add(new TreeNode(0));
            } else if ((N & 1) == 1) {
                for (int x = 0; x < N; x++) {
                    int y = N - x - 1;
                    for (TreeNode left : allPossibleFBT(x)) {
                        for (TreeNode right : allPossibleFBT(y)) {
                            TreeNode node = new TreeNode(0);
                            node.left = left;
                            node.right = right;
                            ans.add(node);
                        }
                    }
                }
            }
            map.put(N, ans);
        }

        return map.get(N);
    }
}
