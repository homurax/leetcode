/**
 * 2487. Remove Nodes From Linked List
 *
 *
 * You are given the head of a linked list.
 *
 * Remove every node which has a node with a strictly greater value anywhere to the right side of it.
 *
 * Return the head of the modified linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [5,2,13,3,8]
 * Output: [13,8]
 * Explanation: The nodes that should be removed are 5, 2 and 3.
 * - Node 13 is to the right of node 5.
 * - Node 13 is to the right of node 2.
 * - Node 8 is to the right of node 3.
 *
 *
 * Example 2:
 *
 * Input: head = [1,1,1,1]
 * Output: [1,1,1,1]
 * Explanation: Every node has value 1, so no nodes are removed.
 *
 *
 * Constraints:
 *
 * The number of the nodes in the given list is in the range [1, 10^5].
 * 1 <= Node.val <= 10^5
 */
public class RemoveNodesFromLinkedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    // 递归本质就是在倒着遍历链表
    public ListNode removeNodes(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode node = removeNodes(head.next);
        if (node.val > head.val) {
            return node;
        }
        head.next = node;
        return head;
    }


    public ListNode removeNodes1(ListNode head) {
        ListNode cur = head;
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (cur != null) {
            while (!stack.isEmpty() && stack.peek().val < cur.val) {
                stack.pop();
            }
            stack.push(cur);
            cur = cur.next;
        }
        head = stack.removeLast();
        cur = head;
        while (!stack.isEmpty()) {
            cur.next = stack.removeLast();
            cur = cur.next;
        }
        cur.next = null;
        return head;
    }

}
