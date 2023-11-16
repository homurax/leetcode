/**
 * 2385. Amount of Time for Binary Tree to Be Infected
 *
 *
 * You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.
 *
 * Each minute, a node becomes infected if:
 *
 * The node is currently uninfected.
 * The node is adjacent to an infected node.
 * Return the number of minutes needed for the entire tree to be infected.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,5,3,null,4,10,6,9,2], start = 3
 * Output: 4
 * Explanation: The following nodes are infected during:
 * - Minute 0: Node 3
 * - Minute 1: Nodes 1, 10 and 6
 * - Minute 2: Node 5
 * - Minute 3: Node 4
 * - Minute 4: Nodes 9 and 2
 * It takes 4 minutes for the whole tree to be infected so we return 4.
 *
 *
 * Example 2:
 *
 *
 * Input: root = [1], start = 1
 * Output: 0
 * Explanation: At minute 0, the only node in the tree is infected so we return 0.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^5
 * Each node has a unique value.
 * A node with a value of start exists in the tree.
 */
public class AmountOfTimeForBinaryTreeToBeInfected {

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


    // 不建图
    private int ans = 0;
    private int start;
    private int targetLevel = -1;

    public int amountOfTime(TreeNode root, int start) {
        this.start = start;
        dfs(root, 0);
        return ans;
    }

    private int dfs(TreeNode node, int level) {
        if (node == null) {
            return 0;
        }
        if (node.val == start) {
            targetLevel = level;
        }
        int l = dfs(node.left, level + 1);
        boolean inLeft = targetLevel != -1;
        int r = dfs(node.right, level + 1);
        // 从 start 向下感染
        if (node.val == start) {
            ans = Math.max(ans, Math.max(l, r));
        }
        // 从 start 向上感染 root 后再感染 root 的另一子树
        if (inLeft) {
            ans = Math.max(ans, targetLevel - level + r);
        } else {
            ans = Math.max(ans, targetLevel - level + l);
        }
        return Math.max(l, r) + 1;
    }




    // 见图
    private Map<Integer, TreeNode> map;
    private TreeNode startNode;
    // private int start;

    public int amountOfTime1(TreeNode root, int start) {
        this.start = start;
        this.map = new HashMap<>();

        dfs(null, root);

        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.offerLast(startNode);
        boolean[] visited = new boolean[100001];
        visited[start] = true;
        int ans = -1;
        for (; !nodes.isEmpty(); ans++) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.removeFirst();
                TreeNode left = node.left;
                if (left != null && !visited[left.val]) {
                    visited[left.val] = true;
                    nodes.offerLast(left);
                }
                TreeNode right = node.right;
                if (right != null && !visited[right.val]) {
                    visited[right.val] = true;
                    nodes.offerLast(right);
                }
                TreeNode parent = map.get(node.val);
                if (parent != null && !visited[parent.val]) {
                    visited[parent.val] = true;
                    nodes.offerLast(parent);
                }
            }
        }
        return ans;
    }

    private void dfs(TreeNode parent, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.val == start) {
            startNode = node;
        }
        map.put(node.val, parent);
        dfs(node, node.left);
        dfs(node, node.right);
    }


}
