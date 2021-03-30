/**
 * 117. Populating Next Right Pointers in Each Node II
 *
 *
 * Given a binary tree
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
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the given tree is less than 6000.
 * 2. -100 <= node.val <= 100
 */
public class PopulatingNextRightPointersInEachNodeII {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node level = root;
        while (level != null) {
            Node curr = level;
            level = null;
            Node prev = null;
            while (curr != null) {
                if (curr.left != null) {
                    if (level == null) {
                        level = curr.left;
                    }
                    if (prev != null) {
                        prev.next = curr.left;
                    }
                    prev = curr.left;
                }
                if (curr.right != null) {
                    if (level == null) {
                        level = curr.right;
                    }
                    if (prev != null) {
                        prev.next = curr.right;
                    }
                    prev = curr.right;
                }
                curr = curr.next;
            }
        }
        return root;
    }


}
