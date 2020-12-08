/**
 * 116. Populating Next Right Pointers in Each Node
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Follow up:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the given tree is less than 4096.
 * 2. -1000 <= node.val <= 1000
 */
public class PopulatingNextRightPointersInEachNode {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}
        public Node(int val) {
            this.val = val;
        }
        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }


    public Node connect(Node root) {
        link(root);
        return root;
    }

    private void link(Node node) {
        if (node == null || node.left == node.right) {
            return;
        }
        node.left.next = node.right;
        if (node.next != null) {
            node.right.next = node.next.left;
        }
        link(node.left);
        link(node.right);
    }

    // ↑ 由 parent 去操作 child 就不用考虑当前节点是左还是右了
    private void link(Node node, Node parent, boolean left) {
        if (node == null) return;
        if (parent != null) {
            if (left) {
                node.next = parent.right;
            } else {
                if (parent.next != null) {
                    node.next = parent.next.left;
                }
            }
        }
        link(node.left, node, true);
        link(node.right, node, false);
    }


    // 常数级别额外空间要求 -> 不能用 Queue 去做 BFS
    // 正好通过 next 在一层之间移动
    public Node connect1(Node root) {
        if (root == null) return null;
        Node level = root;
        Node cur;
        while (level.left != level.right) {
            cur = level;
            // 通过 next 处理本层节点 link child node
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            // 去下一层
            level = level.left;
        }
        return root;
    }

}
