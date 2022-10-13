/**
 * 61. Rotate List
 *
 *
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 *
 * Example 2:
 *
 *
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the list is in the range [0, 500].
 * 2. -100 <= Node.val <= 100
 * 3. 0 <= k <= 2 * 10^9
 */
public class RotateList {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode temp = head;
        ListNode tail = head;
        while (temp != null) {
            tail = temp;
            temp = temp.next;
            len++;
        }
        k = k % len;
        if (len == 1 || k == 0) {
            return head;
        }
        int target = len - k;
        ListNode newTail = head;
        while (--target > 0) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
    }

}
