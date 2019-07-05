/**
 * 21. Merge Two Sorted Lists
 *
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    /**
     * 递归
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }


    /**
     * 迭代
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        ListNode node = new ListNode(0);
        ListNode head = node;

        while (l1 != null || l2 != null) {
            if (l1 == null || (l2 != null && l1.val >= l2.val)) {
                head = head.next = l2;
                l2 = l2.next;
            } else {
                head = head.next = l1;
                l1 = l1.next;
            }
        }
        return node.next;
    }

}
