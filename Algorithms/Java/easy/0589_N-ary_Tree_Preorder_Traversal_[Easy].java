/**
 * 589. N-ary Tree Preorder Traversal
 *
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 *
 * For example, given a 3-ary tree:
 *
 *
 * Return its preorder traversal as: [1,3,5,6,2,4].
 *
 * Note:
 *
 * Recursive solution is trivial, could you do it iteratively?
 */
public class NAryTreePreorderTraversal {


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
     * 前序遍历
     * 递归
     */
    public List<Integer> preorder(Node root) {

        if (root == null)
            return list;
        list.add(root.val);
        List<Node> children = root.children;
        for (Node child : children)
            preorder(child);
        return list;
    }

    /**
     * 迭代
     */
    public List<Integer> preorder2(Node root) {

        List<Integer> valList = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            valList.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--)
                stack.push(node.children.get(i));
        }
        return valList;
    }


}
