/**
 * 641. Design Circular Deque
 *
 *
 * Design your implementation of the circular double-ended queue (deque).
 *
 * Implement the MyCircularDeque class:
 *
 * MyCircularDeque(int k) Initializes the deque with a maximum size of k.
 * boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
 * int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
 * boolean isEmpty() Returns true if the deque is empty, or false otherwise.
 * boolean isFull() Returns true if the deque is full, or false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 2, true, true, true, 4]
 *
 * Explanation
 * MyCircularDeque myCircularDeque = new MyCircularDeque(3);
 * myCircularDeque.insertLast(1);  // return True
 * myCircularDeque.insertLast(2);  // return True
 * myCircularDeque.insertFront(3); // return True
 * myCircularDeque.insertFront(4); // return False, the queue is full.
 * myCircularDeque.getRear();      // return 2
 * myCircularDeque.isFull();       // return True
 * myCircularDeque.deleteLast();   // return True
 * myCircularDeque.insertFront(4); // return True
 * myCircularDeque.getFront();     // return 4
 *
 *
 * Constraints:
 *
 * 1. 1 <= k <= 1000
 * 2. 0 <= value <= 1000
 * 3. At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
 */
public class DesignCircularDeque {

    class MyCircularDeque {

        private int[] arr;
        private int head;
        private int tail;
        private int count;
        private int capacity;

        public MyCircularDeque(int k) {
            this.arr = new int[k];
            this.head = 0;
            this.tail = 0;
            this.count = 0;
            this.capacity = k;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            arr[head = (head + capacity - 1) % capacity] = value;
            count++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            arr[tail] = value;
            tail = (tail + 1) % capacity;
            count++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            head = (head + 1) % capacity;
            count--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            tail = (tail + capacity - 1) % capacity;
            count--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return arr[head];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return arr[(tail + capacity - 1) % capacity];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == capacity;
        }

        /*public MyCircularDeque(int k) {
            this.arr = new int[k];
            this.head = 0;
            this.tail = 0;
            this.count = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            count++;
            head = (head == 0) ? arr.length - 1 : head - 1;
            arr[head] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            count++;
            arr[tail] = value;
            tail = (tail == arr.length - 1) ? 0 : tail + 1;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            count--;
            head = (head == arr.length - 1) ? 0 : head + 1;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            count--;
            tail = (tail == 0) ? arr.length - 1 : tail - 1;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return arr[head];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return arr[(tail == 0) ? arr.length - 1 : tail - 1];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == arr.length;
        }*/
    }



}
