/**
 * 559. Maximum Depth of N-ary Tree
 *
 * Given a n-ary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * For example, given a 3-ary tree:
 *
 * We should return its max depth, which is 3.
 *
 * Note:
 *
 * The depth of the tree is at most 1000.
 * The total number of nodes is at most 5000.
 */
public class MaximumDepthOfNAryTree {

    class Node {

        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 递归 DFS
     */
    public int maxDepth(Node root) {

        if (root == null)
            return 0;
        int depth = 0;
        for (Node node: root.children)
            depth = Math.max(depth, maxDepth(node));
        return depth + 1;
    }

    /**
     * 迭代 BFS
     */
    public int maxDepth2(Node root) {

        int depth = 0;
        if (root == null)
            return depth;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Node node = queue.poll();
                for (Node child : node.children) {
                    queue.offer(child);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }

}
