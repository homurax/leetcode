/**
 * 1721. Swapping Nodes in a Linked List
 *
 *
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 *
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 *
 * Example 3:
 *
 * Input: head = [1], k = 1
 * Output: [1]
 *
 * Example 4:
 *
 * Input: head = [1,2], k = 1
 * Output: [2,1]
 *
 * Example 5:
 *
 * Input: head = [1,2,3], k = 2
 * Output: [1,2,3]
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the list is n.
 * 2. 1 <= k <= n <= 10^5
 * 3. 0 <= Node.val <= 100
 */
public class SwappingNodesInALinkedList {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**
     * k1 k2 指向 head
     * k1 走 k 步，到 k 位置，余下 n - k
     * k2 走 n - k 步，余下 k，即为倒数 k 的位置
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode k1 = head, k2 = head;
        for (int i = 1; i < k; i++) {
            k1 = k1.next;
        }
        ListNode curr = k1;
        while (curr.next != null) {
            curr = curr.next;
            k2 = k2.next;
        }
        if (k1 != k2) {
            int temp = k2.val;
            k2.val = k1.val;
            k1.val = temp;
        }
        return head;
    }



    public ListNode swapNodes1(ListNode head, int k) {
        int n = 0, count = 0;
        ListNode k1 = null, k2 = null, curr = head;
        while (curr != null) {
            if (++n == k) {
                k1 = curr;
            }
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            if (++count == n - k + 1) {
                k2 = curr;
            }
            curr = curr.next;
        }
        if (k1 != k2) {
            int temp = k2.val;
            k2.val = k1.val;
            k1.val = temp;
        }
        return head;
    }

}
