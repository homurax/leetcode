/**
 * 92. Reverse Linked List II
 *
 *
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the list is n.
 * 2. 1 <= n <= 500
 * 3. -500 <= Node.val <= 500
 * 4. 1 <= left <= right <= n
 */
public class ReverseLinkedListII {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;

        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        ListNode leftNode = pre.next;
        ListNode tail = rightNode.next;
        pre.next = null;
        rightNode.next = null;

        ListNode precursor = null;
        ListNode curr = leftNode;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = precursor;
            precursor = curr;
            curr = next;
        }

        pre.next = rightNode;
        leftNode.next = tail;
        return dummy.next;
    }


    // 头插法
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode curr = pre.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }


}
