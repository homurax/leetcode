/**
 * 429. N-ary Tree Level Order Traversal
 *
 * Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example, given a 3-ary tree:
 *
 *
 * We should return its level order traversal:
 *
 * [
 *      [1],
 *      [3,2,4],
 *      [5,6]
 * ]
 *
 *
 * Note:
 *
 * The depth of the tree is at most 1000.
 * The total number of nodes is at most 5000.
 */
public class NAryTreeLevelOrderTraversal {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    /**
     * 迭代
     */
    public List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> ans = new ArrayList<>(1000);
        if (root == null) {
            return ans;
        }

        List<Integer> first = new ArrayList<>(1);
        first.add(root.val);
        ans.add(first);

        LinkedList<List<Node>> nodes = new LinkedList<>();
        if (root.children != null && !root.children.isEmpty()) {
            nodes.add(root.children);
        }

        while (!nodes.isEmpty()) {

            LinkedList<List<Node>> nextNodes = new LinkedList<>();
            List<Integer> integers = new ArrayList<>();
            while (!nodes.isEmpty()) {
                List<Node> pop = nodes.pop();
                for (Node node : pop) {
                    integers.add(node.val);
                    if (node.children!= null && !node.children.isEmpty()) {
                        nextNodes.add(node.children);
                    }
                }
            }
            ans.add(integers);
            nodes = nextNodes;
        }

        return ans;
    }

    /**
     * while循环中优化 降低空间
     */
    public List<List<Integer>> levelOrder2(Node root) {

        List<List<Integer>> ans = new ArrayList<>(1000);

        if (root == null) {
            return ans;
        }

        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            List<Integer> integers = new LinkedList<>();
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                Node curr = nodes.poll();
                integers.add(curr.val);
                for (Node r : curr.children) {
                    nodes.add(r);
                }
            }
            ans.add(integers);
        }

        return ans;
    }

    /**
     * 递归
     */
    public List<List<Integer>> levelOrder3(Node root) {

        List<List<Integer>> ans = new ArrayList<>(1000);

        if (root == null) {
            return ans;
        }

        levelOrderTraversal(root, ans, 0);
        return ans;
    }

    private void levelOrderTraversal(Node root, List<List<Integer>> ans, int level) {

        if (root == null) {
            return;
        }

        if (ans.size() <= level) {
            ans.add(new ArrayList<>());
        }

        ans.get(level).add(root.val);

        List<Node> children = root.children;
        if (children == null || children.isEmpty()) {
            return;
        }

        for (Node child : children) {
            levelOrderTraversal(child, ans, level + 1);
        }
    }


}
