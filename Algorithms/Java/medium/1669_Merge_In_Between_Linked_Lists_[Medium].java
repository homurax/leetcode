/**
 * 1669. Merge In Between Linked Lists
 *
 *
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.
 *
 * Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 *
 * The blue edges and nodes in the following figure incidate the result:
 *
 *
 * Build the result list and return its head.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * Output: [0,1,2,1000000,1000001,1000002,5]
 * Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
 *
 * Example 2:
 *
 *
 * Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
 * Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
 * Explanation: The blue edges and nodes in the above figure indicate the result.
 *
 *
 * Constraints:
 *
 * 1. 3 <= list1.length <= 10^4
 * 2. 1 <= a <= b < list1.length - 1
 * 3. 1 <= list2.length <= 10^4
 */
public class MergeInBetweenLinkedLists {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int index = 0;
        ListNode prev = list1;
        while (index < a - 1) {
            prev = prev.next;
            index++;
        }
        ListNode tail = prev;
        while (index < b + 1) {
            tail = tail.next;
            index++;
        }
        ListNode end = list2;
        while (end.next != null) {
            end = end.next;
        }
        prev.next = list2;
        end.next = tail;
        return list1;
    }

}
