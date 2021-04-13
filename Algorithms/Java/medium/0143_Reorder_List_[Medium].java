/**
 * 143. Reorder List
 *
 *
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 *
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the list is in the range [1, 5 * 10^4].
 * 2. 1 <= Node.val <= 1000
 */
public class ReorderList {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    public void reorderList1(ListNode head) {
        // 快慢指针定位 mid 节点
        // fast != null && fast.next != null            ->  1 2 [3] 4; 1 2 [3] 4 5
        // fast.next != null && fast.next.next != null  ->  1 [2] 3 4; 1 2 [3] 4 5
        ListNode mid = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }
        // 反转后半部分
        ListNode curr = mid.next, prev = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        mid.next = null;
        // merge head & prev
        ListNode node1 = head, node2 = prev;
        ListNode temp1, temp2;
        while (node1 != null && node2 != null) {
            temp1 = node1.next;
            temp2 = node2.next;
            node1.next = node2;
            node2.next = temp1;
            node1 = temp1;
            node2 = temp2;
        }
    }

}
