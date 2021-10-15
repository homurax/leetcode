/**
 * 341. Flatten Nested List Iterator
 *
 *
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 *
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * Your code will be tested with the following pseudocode:
 *
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 *     append iterator.next() to the end of res
 * return res
 * If res matches the expected flattened list, then your code will be judged as correct.
 *
 *
 *
 * Example 1:
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 *
 *
 * Example 2:
 *
 * Input: nestedList = [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 *
 *
 * Constraints:
 *
 * 1. 1 <= nestedList.length <= 500
 * 2. The values of the integers in the nested list is in the range [-10^6, 10^6].
 */
public class FlattenNestedListIterator {

    interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    class NestedIterator implements Iterator<Integer> {

        /*private int cursor;
        private List<Integer> list;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.cursor = 0;
            this.list = new ArrayList<>();
            flatMap(nestedList);
        }

        private void flatMap(List<NestedInteger> nestedList) {
            for (NestedInteger nested : nestedList) {
                if (nested.isInteger()) {
                    list.add(nested.getInteger());
                } else {
                    flatMap(nested.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return list.get(cursor++);
        }

        @Override
        public boolean hasNext() {
            return cursor < list.size();
        }*/




        /*private Deque<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.stack = new ArrayDeque<>();
            stack.addAll(nestedList);
        }

        @Override
        public Integer next() {
            NestedInteger nested = stack.poll();
            while (!nested.isInteger()) {
                List<NestedInteger> list = nested.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.push(list.get(i));
                }
                nested = stack.poll();
            }
            return nested.getInteger();
        }

        @Override
        public boolean hasNext() {
            for (NestedInteger nested : stack) {
                if (isNotEmpty(nested)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isNotEmpty(NestedInteger nested) {
            if (nested.isInteger()) {
                return true;
            }
            for (NestedInteger n : nested.getList()) {
                if (isNotEmpty(n)) {
                    return true;
                }
            }
            return false;
        }*/




        private Deque<Iterator<NestedInteger>> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.stack = new LinkedList<>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return stack.peek().next().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                Iterator<NestedInteger> it = stack.peek();
                if (!it.hasNext()) {
                    stack.pop();
                    continue;
                }
                NestedInteger nest = it.next();
                if (nest.isInteger()) {
                    List<NestedInteger> list = new ArrayList<>();
                    list.add(nest);
                    stack.push(list.iterator());
                    return true;
                }
                stack.push(nest.getList().iterator());
            }
            return false;
        }

    }


}
