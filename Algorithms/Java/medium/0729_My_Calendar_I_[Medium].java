/**
 * 729. My Calendar I
 *
 *
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 *
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 *
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */
public class MyCalendarI {

    class MyCalendar {

        private final List<Integer> books;

        public MyCalendar() {
            this.books = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            if (books.isEmpty()) {
                books.add(start);
                books.add(end);
                return true;
            }
            for (int i = 0; i < books.size(); i += 2) {
                Integer x = books.get(i);
                Integer y = books.get(i + 1);
                if (!(end <= x || start >= y)) {
                    return false;
                }
            }
            books.add(start);
            books.add(end);
            return true;
        }
    }


    // 按时间顺序维护日程安排 通过二分查找日程安排的情况
    class MyCalendar2 {

        private final TreeMap<Integer, Integer> calendar;

        public MyCalendar2() {
            calendar = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer prev = calendar.floorKey(start);
            Integer next = calendar.ceilingKey(start);
            if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
                calendar.put(start, end);
                return true;
            }
            return false;
        }
    }


    class MyCalendar3 {
        class Node {
            int start;
            int end;
            Node right;
            Node left;

            Node(int start, int end) {
                this.start = start;
                this.end = end;
                this.left = null;
                this.right = null;
            }
        }


        private Node root = null;

        public MyCalendar3() {

        }

        public boolean book(int start, int end) {
            if (root == null) {
                root = new Node(start, end);
                return true;
            }
            Node insert = new Node(start, end);
            Node curr = root;
            while (true) {
                if (curr.start >= end) {
                    if (curr.left == null) {
                        curr.left = insert;
                        return true;
                    }
                    curr = curr.left;
                } else if (curr.end <= start) {
                    if (curr.right == null) {
                        curr.right = insert;
                        return true;
                    }
                    curr = curr.right;
                } else {
                    return false;
                }
            }
        }
    }

}
