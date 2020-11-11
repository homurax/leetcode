/**
 * 731. My Calendar II
 *
 *
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 *
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 *
 * Note:
 *
 * 1. The number of calls to MyCalendar.book per test case will be at most 1000.
 * 2. In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */
public class MyCalendarII {

    class MyCalendarTwo1 {

        private TreeMap<Integer, Integer> map;

        public MyCalendarTwo1() {
            this.map = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            map.merge(start, 1, Integer::sum);
            map.merge(end, -1, Integer::sum);
            int active = 0;
            for (int val: map.values()) {
                active += val;
                if (active >= 3) {
                    map.put(start, map.get(start) - 1);
                    map.put(end, map.get(end) + 1);
                    if (map.get(start) == 0) {
                        map.remove(start);
                    }
                    return false;
                }
            }
            return true;
        }
    }

    class MyCalendarTwo {

        private final TreeMap<Integer, Integer> intervals;
        private final TreeMap<Integer, Integer> overlaps;

        public MyCalendarTwo() {
            this.intervals = new TreeMap<>();
            this.overlaps = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            if (hasOverlap(start, end)) {
                return false;
            }
            if (intervals.isEmpty()) {
                intervals.put(start, end);
                return true;
            }
            // 取出交集更新 overlaps，移除 intervals，最后维护更宽的 intervals
            Integer floor = intervals.floorKey(start);
            if (floor != null && start < intervals.get(floor)) {
                int floorEnd = intervals.get(floor);
                overlaps.put(start, Math.min(end, floorEnd));
                start = floor;
                end = Math.max(end, floorEnd);
                intervals.remove(floor);
            }
            Integer ceil = intervals.ceilingKey(start);
            while (ceil != null && ceil < end) {
                int ceilEnd = intervals.get(ceil);
                overlaps.put(ceil, Math.min(end, ceilEnd));
                end = Math.max(end, ceilEnd);
                intervals.remove(ceil);
                ceil = intervals.ceilingKey(start);
            }
            intervals.put(start, end);
            return true;
        }

        private boolean hasOverlap(int start, int end) {
            Integer floor = overlaps.floorKey(start);
            if (floor != null && start < overlaps.get(floor)) {
                return true;
            }
            Integer ceil = overlaps.ceilingKey(start);
            return ceil != null && ceil < end;
        }
    }

}
