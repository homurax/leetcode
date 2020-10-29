/**
 * 382. Linked List Random Node
 *
 *
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 *
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
 *
 * Example:
 *
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 *
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 */
public class LinkedListRandomNode {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    /**
     * init 时可以直接用 array or list 储存所有的 val
     * getRandom 中伪随机数下标访问取值即可
     *
     * 当内存无法加载全部数据时，从包含未知大小的数据流中随机选取 K（此处 K = 1） 个数据，并且要保证每个数据被抽取到的概率相等。
     * 假设数据流共 N 个数，每个数选中的概率应为 1/N。
     * 每次只保留一个数，当遇到第 n 个数时，以 1/n 的概率保留它，(i - 1)/ i 的概率保留原来的数。
     *
     * N = 10; n in [1, 10]
     * n = 1: P(1) = 1
     * n = 2: P(2) = 1/2; P(1) = (2-1)/2 = 1/2
     * n = 3: P(3) = 1/3; P(1|1 和 2 中假设 1 被保留) = 1/2 * (3 - 1)/3 = 1/2 * 2/3 = 1/3
     * n = 4: P(4) = 1/4; P(1|1 和 3 中假设 1 被保留) = 1/2 * 2/3 * 3/4 = 1/4
     * ...
     */
    class Solution {

        private ListNode head;

        public Solution(ListNode head) {
            this.head = head;
        }

        public int getRandom() {
            int count = 0, reserve = 0;
            ListNode cur = head;
            while (cur != null) {
                count++;
                // random in [1, count]
                int random = new Random().nextInt(count) + 1;
                if (random == count) {
                    reserve = cur.val;
                }
                cur = cur.next;
            }
            return reserve;
        }
    }

}
