/**
 * 234. Palindrome Linked List
 *
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 *
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int low = 0, high = list.size() - 1;
        while (low < high) {
            if (!list.get(low).equals(list.get(high))) {
                return false;
            }
            low++;
            high--;
        }

        return true;
    }


    /**
     * 通过快慢指针获取中间节点
     * 反转前半部分链表与后半部分比较
     */
    public boolean isPalindrome2(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // // unlink first half from second half

        ListNode firstNode = reverse(head);
        ListNode secondNode = fast == null ? slow : slow.next;
        while (firstNode != null) {
            if (firstNode.val != secondNode.val) {
                return false;
            }
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }

        return true;
    }

    private ListNode reverse(ListNode node) {
        ListNode curr = node;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
