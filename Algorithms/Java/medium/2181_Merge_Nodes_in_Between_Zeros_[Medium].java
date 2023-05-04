/**
 * 2181. Merge Nodes in Between Zeros
 *
 *
 * You are given the head of a linked list, which contains a series of integers separated by 0's. The beginning and end of the linked list will have Node.val == 0.
 *
 * For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the sum of all the merged nodes. The modified list should not contain any 0's.
 *
 * Return the head of the modified linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [0,3,1,0,4,5,2,0]
 * Output: [4,11]
 * Explanation:
 * The above figure represents the given linked list. The modified list contains
 * - The sum of the nodes marked in green: 3 + 1 = 4.
 * - The sum of the nodes marked in red: 4 + 5 + 2 = 11.
 *
 *
 * Example 2:
 *
 *
 * Input: head = [0,1,0,3,0,2,2,0]
 * Output: [1,3,4]
 * Explanation:
 * The above figure represents the given linked list. The modified list contains
 * - The sum of the nodes marked in green: 1 = 1.
 * - The sum of the nodes marked in red: 3 = 3.
 * - The sum of the nodes marked in yellow: 2 + 2 = 4.
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the list is in the range [3, 2 * 10^5].
 * 2. 0 <= Node.val <= 1000
 * 3. There are no two consecutive nodes with Node.val == 0.
 * 4. The beginning and end of the linked list have Node.val == 0.
 */
public class MergeNodesInBetweenZeros {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    // 原地修改
    public ListNode mergeNodes(ListNode head) {
        ListNode cur = head;
        int sum = 0;
        for (ListNode node = head.next; node != null; node = node.next) {
            if (node.val > 0) {
                sum += node.val;
            } else {
                cur.next.val = sum;
                cur = cur.next;
                sum = 0;
            }
        }
        cur.next = null;
        return head.next;
    }

    public ListNode mergeNodes1(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (head.next != null) {
            int sum = 0;
            for (head = head.next; head.val != 0; head = head.next) {
                sum += head.val;
            }
            cur.next = new ListNode(sum);
            cur = cur.next;
        }
        return dummy.next;
    }


}
