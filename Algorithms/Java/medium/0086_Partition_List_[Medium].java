/**
 * 86. Partition List
 *
 *
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 * Example 2:
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 1. The number of nodes in the list is in the range [0, 200].
 * 2. -100 <= Node.val <= 100
 * 3. -200 <= x <= 200
 */
public class PartitionList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**
     * 分为 [first half] 和 [second half] 各自维护 最后拼接
     * 任意一半为空的情况不需要考虑设置 tail.next 为 null
     * 两半均不空时中间要连接也不需要考虑 secondTail 需要考虑设置 tail.next 为 null
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode firstHead = null, firstTail = null;
        ListNode secondHead = null, secondTail = null;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                if (firstHead == null) {
                    firstHead = curr;
                    firstTail = firstHead;
                } else {
                    firstTail.next = curr;
                    firstTail = firstTail.next;
                }
            } else {
                if (secondHead == null) {
                    secondHead = curr;
                    secondTail = secondHead;
                } else {
                    secondTail.next = curr;
                    secondTail = secondTail.next;
                }
            }
            curr = curr.next;
        }
        if (firstHead == null) {
            return secondHead;
        }
        if (secondHead == null) {
            return firstHead;
        }
        firstTail.next = secondHead;
        secondTail.next = null;
        return firstHead;
    }


    // 两半都 new head 减少 if 块
    public ListNode partition1(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }


}
