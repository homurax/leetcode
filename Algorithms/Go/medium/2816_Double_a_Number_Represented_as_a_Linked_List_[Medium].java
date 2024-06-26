/**
 * 2816. Double a Number Represented as a Linked List
 *
 *
 * You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.
 *
 * Return the head of the linked list after doubling it.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,8,9]
 * Output: [3,7,8]
 * Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.
 *
 *
 * Example 2:
 *
 *
 * Input: head = [9,9,9]
 * Output: [1,9,9,8]
 * Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 10^4]
 * 0 <= Node.val <= 9
 * The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.
 */
public class DoubleANumberRepresentedAsALinkedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // 长度最多加一 正好可以利用 dummy
    public ListNode doubleIt(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy, cur = head;
        while (cur != null) {
            cur.val *= 2;
            if (cur.val > 9) {
                prev.val += 1;
                cur.val %= 10;
            }
            cur = cur.next;
            prev = prev.next;
        }
        return dummy.val == 0 ? dummy.next : dummy;
    }

    // 提前考虑要不要增加节点 以及当前节点是否加一
    public ListNode doubleIt1(ListNode head) {
        if (head.val > 4) {
            head = new ListNode(0, head);
        }
        for (ListNode cur = head; cur != null; cur = cur.next) {
            cur.val = cur.val * 2 % 10;
            if (cur.next != null && cur.next.val > 4) {
                cur.val++;
            }
        }
        return head;
    }

}
