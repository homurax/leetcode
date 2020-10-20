/**
 * 445. Add Two Numbers II
 *
 *
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<ListNode> stack1 = new LinkedList<>();
        LinkedList<ListNode> stack2 = new LinkedList<>();
        ListNode cur = l1;
        while (cur != null) {
            stack1.push(cur);
            cur = cur.next;
        }
        cur = l2;
        while (cur != null) {
            stack2.push(cur);
            cur = cur.next;
        }
        // 尽量复用 ListNode 不去 new 新的
        int add = 0;
        ListNode curHead = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode node1 = stack1.pop();
            ListNode node2 = stack2.pop();
            int sum = node1.val + node2.val + add;
            add = sum / 10;
            node1.val = sum % 10;
            if (curHead != null) {
                node1.next = curHead;
            }
            curHead = node1;
        }
        LinkedList<ListNode> stack = stack1.isEmpty() ? stack2 : stack1;
        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            int sum = node.val + add;
            add = sum / 10;
            node.val = sum % 10;
            if (curHead != null) {
                node.next = curHead;
            }
            curHead = node;
        }
        if (add == 0) {
            return curHead;
        }
        ListNode head = new ListNode(add);
        head.next = curHead;
        return head;
    }



    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int curVal = a + b + carry;
            carry = curVal / 10;
            curVal %= 10;
            ListNode curNode = new ListNode(curVal);
            curNode.next = ans;
            ans = curNode;
        }
        return ans;
    }



    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        int len1 = length(l1);
        int len2 = length(l2);
        if (len2 > len1) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }

        int carry = add(l1, l2, Math.abs(len1 - len2));
        if (carry > 0) {
            l1 = new ListNode(carry, l1);
        }
        return l1;
    }

    private int length(ListNode node) {
        int size = 0;
        while (node != null) {
            node = node.next;
            size++;
        }
        return size;
    }

    private int add(ListNode l1, ListNode l2, int startAfter) {
        if (l1 == null) {
            return 0;
        }
        int digit;
        if (startAfter > 0) {
            int carry = add(l1.next, l2, startAfter - 1);
            digit = l1.val + carry;
        } else {
            int carry = add(l1.next, l2.next, 0);
            digit = l1.val + l2.val + carry;
        }
        l1.val = digit % 10;
        return digit / 10;
    }
}
