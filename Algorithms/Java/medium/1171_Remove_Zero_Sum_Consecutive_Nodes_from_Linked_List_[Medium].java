/**
 * 1171. Remove Zero Sum Consecutive Nodes from Linked List
 *
 *
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 *
 * After doing so, return the head of the final linked list.  You may return any such answer.
 *
 *
 *
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 *
 * Example 1:
 *
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 *
 * Example 2:
 *
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 *
 * Example 3:
 *
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1. The given linked list will contain between 1 and 1000 nodes.
 * 2. Each node in the linked list has -1000 <= node.val <= 1000.
 */
public class RemoveZeroSumConsecutiveNodesFromLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        Map<Integer, ListNode> sumRecord = new HashMap<>();
        int sum = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            sumRecord.put(sum, node);
        }
        sum = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            // 存在 sum 相同的节点就说明 node 到 sumRecord.get(sum) 之间的和为 0
            node.next = sumRecord.get(sum).next;
        }
        return dummy.next;
    }

}
