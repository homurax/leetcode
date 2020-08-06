/**
 * 155. Min Stack
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 *
 * Constraints:
 *
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 */
public class MinStack {

    private final Stack<Integer> stack;
    private final PriorityQueue<Integer> priorityQueue;

    public MinStack() {
        this.stack = new Stack<>();
        this.priorityQueue = new PriorityQueue<>();
    }

    public void push(int x) {
        stack.push(x);
        priorityQueue.offer(x);
    }

    public void pop() {
        priorityQueue.remove(stack.pop());
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return priorityQueue.peek();
    }

    /*class Node {
        int val;
        int min;
        Node next;
        Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    private Node head = null;
    private int min = Integer.MAX_VALUE;

    public MinStack() {

    }

    public void push(int x) {
        this.min = Math.min(this.min, x);
        Node node = new Node(x, this.min);
        node.next = head;
        this.head = node;
    }

    public void pop() {
        this.head = this.head.next;
        if (this.head == null) {
            this.min = Integer.MAX_VALUE;
        } else {
            this.min = head.min;
        }
    }

    public int top() {
        return this.head.val;
    }

    public int getMin() {
        return this.head.min;
    }*/

}
