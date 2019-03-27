/**
 * 590. N-ary Tree Postorder Traversal
 *
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 *
 * For example, given a 3-ary tree:
 *
 * Return its postorder traversal as: [5,6,3,2,4,1].
 *
 * Note:
 *
 * Recursive solution is trivial, could you do it iteratively?
 */
public class NAryTreePostorderTraversal {

    class Node {
        public int val;
        public List<Node> children;
        public Node() {}
        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    List<Integer> list = new ArrayList<>();

    /**
     * 后序遍历 递归
     */
    public List<Integer> postorder(Node root) {
        if (root == null)
            return list;
        for (Node child : root.children) {
            postorder(child);
        }
        list.add(root.val);
        return list;
    }

    /**
     * 迭代
     */
    public List<Integer> postorder2(Node root) {

        List<Integer> valList = new ArrayList<>();
        if (root == null)
            return valList;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            valList.add(node.val);
            for (Node child : node.children) {
                stack.push(child);
            }
        }
        Collections.reverse(valList);
        return valList;
    }

}
