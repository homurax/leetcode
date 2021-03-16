/**
 * 855. Exam Room
 *
 *
 * In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.
 *
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)
 *
 * Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.
 *
 *
 *
 * Example 1:
 *
 * Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * Output: [null,0,9,4,2,null,5]
 * Explanation:
 * ExamRoom(10) -> null
 * seat() -> 0, no one is in the room, then the student sits at seat number 0.
 * seat() -> 9, the student sits at the last seat number 9.
 * seat() -> 4, the student sits at the last seat number 4.
 * seat() -> 2, the student sits at the last seat number 2.
 * leave(4) -> null
 * seat() -> 5, the student sits at the last seat number 5.
 *
 *
 * Note:
 *
 * 1. 1 <= N <= 10^9
 * 2. ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
 * 3. Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.
 */
public class ExamRoom {

    /*private final TreeSet<Integer> students;
    private final int n;

    public ExamRoom(int N) {
        this.students = new TreeSet<>();
        this.n = N;
    }

    public int seat() {
        int student = 0;
        if (students.size() > 0) {
            int dist = students.first();
            Integer prev = null;
            for (Integer s : students) {
                if (prev != null) {
                    int d = (s - prev) / 2;
                    if (d > dist) {
                        dist = d;
                        student = prev + d;
                    }
                }
                prev = s;
            }
            if (n - 1 - students.last() > dist) {
                student = n - 1;
            }
        }
        students.add(student);
        return student;
    }

    public void leave(int p) {
        students.remove(p);
    }*/



    /////////////////////////////////

    class Interval {
        int start;
        int end;
        int length;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            if ( this.start == 0 ||  this.end == n - 1) {
                this.length =  this.end -  this.start;
            } else {
                this.length = (this.end -  this.start) / 2;
            }
        }
    }

    private final int n;

    private final PriorityQueue<Interval> pq;

    public ExamRoom(int N) {
        this.n = N;
        this.pq = new PriorityQueue<>((a, b) -> a.length != b.length ? b.length - a.length : a.start - b.start);
        pq.offer(new Interval(0, n - 1));
    }

    public int seat() {
        Interval interval = pq.remove();
        int position;
        if (interval.start == 0) {
            position = 0;
        } else if (interval.end == n - 1) {
            position = n - 1;
        } else {
            position = interval.start + interval.length;
        }
        if (position > interval.start) {
            pq.offer(new Interval(interval.start, position - 1));
        }
        if (position < interval.end) {
            pq.offer(new Interval(position + 1, interval.end));
        }
        return position;
    }

    public void leave(int p) {
        Interval prev = null;
        Interval next = null;
        for (Interval interval : pq) {
            if (interval.end + 1 == p) {
                prev = interval;
            } else if (interval.start - 1 == p) {
                next = interval;
            }
        }
        int start = p, end = p;
        if (prev != null) {
            pq.remove(prev);
            start = prev.start;
        }
        if (next != null) {
            pq.remove(next);
            end = next.end;
        }
        pq.offer(new Interval(start, end));
    }

}
