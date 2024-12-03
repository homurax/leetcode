/**
 * 385. Mini Parser
 *
 *
 * Given a string s represents the serialization of a nested list, implement a parser to deserialize it and return the deserialized NestedInteger.
 *
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "324"
 * Output: 324
 * Explanation: You should return a NestedInteger object which contains a single integer 324.
 *
 *
 * Example 2:
 *
 * Input: s = "[123,[456,[789]]]"
 * Output: [123,[456,[789]]]
 * Explanation: Return a NestedInteger object containing a nested list with 2 elements:
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 *     i.  An integer containing value 456.
 *     ii. A nested list with one element:
 *          a. An integer containing value 789
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 10^4
 * s consists of digits, square brackets "[]", negative sign '-', and commas ','.
 * s is the serialization of valid NestedInteger.
 * All the values in the input are in the range [-10^6, 10^6].
 */
public class MiniParser {

    public class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return null;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }

    public NestedInteger deserialize(String s) {
        NestedInteger mark = new NestedInteger(0);
        char[] chars = s.toCharArray();
        int i = 0;
        int n = chars.length;
        Deque<NestedInteger> deque = new ArrayDeque<>();
        while (i < n) {
            if (chars[i] == ',') {
                i++;
                continue;
            }
            if (chars[i] == '-' || Character.isDigit(chars[i])) { // 数值
                int j = chars[i] == '-' ? i + 1 : i;
                int num = 0;
                while (j < n && Character.isDigit(chars[j])) {
                    num = num * 10 + (chars[j++] - '0');
                }
                deque.addLast(new NestedInteger(chars[i] == '-' ? -num : num));
                i = j;
            } else if (chars[i] == '[') { // NestedInteger
                deque.addLast(new NestedInteger());
                // 标识符 之后的 NestedInteger 属于上一个
                deque.addLast(mark);
                i++;
            } else { // ']'
                List<NestedInteger> list = new ArrayList<>();
                while (!deque.isEmpty()) {
                    NestedInteger t = deque.removeLast();
                    if (t == mark) {
                        break;
                    }
                    list.add(t);
                }
                for (int j = list.size() - 1; j >= 0; j--) {
                    deque.peekLast().add(list.get(j));
                }
                i++;
            }
        }
        return deque.peekLast();
    }

}
