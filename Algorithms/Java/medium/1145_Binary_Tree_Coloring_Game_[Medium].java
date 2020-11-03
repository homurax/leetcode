/**
 * 1145. Binary Tree Coloring Game
 *
 * Two players play a turn based game on a binary tree.  We are given the root of this binary tree, and the number of nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.
 *
 * Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.  The first player colors the node with value x red, and the second player colors the node with value y blue.
 *
 * Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)
 *
 * If (and only if) a player cannot choose such a node in this way, they must pass their turn.  If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
 *
 * You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.  If it is not possible, return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
 * Output: true
 * Explanation: The second player can choose the node with value 2.
 *
 *
 * Constraints:
 *
 * root is the root of a binary tree with n nodes and distinct node values from 1 to n.
 * n is odd.
 * 1 <= x <= n <= 100
 */
public class BinaryTreeColoringGame {

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
     * 想要赢就要尽可能的切断一号玩家的可染色通路
     * 最优选择就是在一号玩家所选择的节点的邻节点（左/右/父）着色
     * 因此要选择所在方向节点数最多的临节点着色
     *
     * 胜利条件为有任何一个方向总节点数目比另外两个方向总和还多
     */
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        return dfs(root, n, x);
    }

    private boolean dfs(TreeNode node, int n, int x) {
        if (node == null) return false;
        if (node.val == x) {
            int left = count(node.left);
            int right = count(node.right);
            int parent = n - left - right - 1;
            return (parent > left + right) || (left > right + parent) || (right > left + parent);
        }
        return dfs(node.left, n, x) || dfs(node.right, n, x);
    }

    private int count(TreeNode node) {
        if (node == null) return 0;
        return count(node.left) + count(node.right) + 1;
    }

}
