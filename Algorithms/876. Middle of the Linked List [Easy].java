/**
 * 876. Middle of the Linked List
 *
 * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,4,5]
 * Output: Node 3 from this list (Serialization: [3,4,5])
 * The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
 * Note that we returned a ListNode object ans, such that:
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
 * Example 2:
 *
 * Input: [1,2,3,4,5,6]
 * Output: Node 4 from this list (Serialization: [4,5,6])
 * Since the list has two middle nodes with values 3 and 4, we return the second one.
 *
 *
 * Note:
 *
 * The number of nodes in the given list will be between 1 and 100.
 */
public class MiddleOfTheLinkedList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    /**
     * 获取链表长度 计算中位数
     * 获取节点返回
     */
    public ListNode middleNode(ListNode head) {

        int length = 1;
        ListNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
            length++;
        }

        int mid = length/2 + 1;
        length = 1;
        while (length < mid) {
            head = head.next;
            length++;
        }

        return head;
    }

    /**
     * 使用数组储存节点 直接获取
     */
    public ListNode middleNode2(ListNode head) {

        ListNode[] array = new ListNode[100];
        int lenth = 0;
        while (head != null) {
            array[lenth++] = head;
            head = head.next;
        }
        return array[lenth/2];
    }

    /**
     * 快慢指针法
     * 当用慢指针slow遍历列表时 让另一个指针 fast 的速度是它的两倍
     * 当fast到达列表的末尾时 slow必然位于中间。
     */
    public ListNode middleNode3(ListNode head) {

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
