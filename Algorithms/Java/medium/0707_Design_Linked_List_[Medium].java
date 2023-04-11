/**
 * 707. Design Linked List
 *
 *
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement the MyLinkedList class:
 *
 * MyLinkedList() Initializes the MyLinkedList object.
 * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
 * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
 * void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
 * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output
 * [null, null, null, null, 2, null, 3]
 *
 * Explanation
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
 * myLinkedList.get(1);              // return 2
 * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
 * myLinkedList.get(1);              // return 3
 *
 *
 * Constraints:
 *
 * 1. 0 <= index, val <= 1000
 * 2. Please do not use the built-in LinkedList library.
 * 3. At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
 */
public class DesignLinkedList {

    class MyLinkedList {

        class Node {
            Node prev, next;
            int val;

            Node(int val) {
                this.val = val;
            }
        }

        int size = 0;
        Node head = new Node(-1);
        Node tail = new Node(-1);

        public MyLinkedList() {
            head.next = tail;
            tail.prev = head;
        }

        public int get(int index) {
            Node node = getNode(index);
            return node == null ? -1 : node.val;
        }

        public void addAtHead(int val) {
            Node node = new Node(val);
            node.next = head.next;
            node.prev = head;
            node.next.prev = node;
            head.next = node;
            size++;
        }

        public void addAtTail(int val) {
            Node node = new Node(val);
            node.prev = tail.prev;
            node.next = tail;
            node.prev.next = node;
            tail.prev = node;
            size++;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index == 0) {
                addAtHead(val);
            } else if (index == size) {
                addAtTail(val);
            } else {
                Node node = new Node(val);
                Node cur = getNode(index);
                node.next = cur;
                node.prev = cur.prev;
                cur.prev.next = node;
                cur.prev = node;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            Node curr = getNode(index);
            if (curr == null) {
                return;
            }
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            size--;
        }

        private Node getNode(int index) {
            boolean isLeft = index < size / 2;
            if (!isLeft) {
                index = size - index - 1;
            }
            for (Node cur = isLeft ? head.next : tail.prev; cur != head && cur != tail; cur = isLeft ? cur.next : cur.prev) {
                if (index-- == 0) {
                    return cur;
                }
            }
            return null;
        }

    }

}
