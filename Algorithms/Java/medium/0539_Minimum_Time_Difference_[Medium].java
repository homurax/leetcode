/**
 * 539. Minimum Time Difference
 *
 *
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 *
 *
 * Example 1:
 *
 * Input: timePoints = ["23:59","00:00"]
 * Output: 1
 *
 * Example 2:
 *
 * Input: timePoints = ["00:00","23:59","00:00"]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] is in the format "HH:MM".
 */
public class MinimumTimeDifference {

    /**
     *      min(ans, min(t2 - t1, 24:00 - t2 + t1))
     * ->   min(ans, min(t2 - t1, 24:00 - (t2 - t1)))
     */
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int min = Integer.MAX_VALUE;
        /*for (int i = 0; i < timePoints.size(); i++) {
            String s1, s2;
            if (i < timePoints.size() - 1) {
                s1 = timePoints.get(i);
                s2 = timePoints.get(i + 1);
            } else {
                s1 = timePoints.get(0);
                s2 = timePoints.get(i);
            }
            if (s1.equals(s2)) {
                return 0;
            }
            String[] t1 = s1.split(":");
            String[] t2 = s2.split(":");
            int h1 = Integer.parseInt(t1[0]);
            int m1 = Integer.parseInt(t1[1]);
            int h2 = Integer.parseInt(t2[0]);
            int m2 = Integer.parseInt(t2[1]);
            int diff = (h2 - h1) * 60 + (m2 - m1);
            min = Math.min(min, Math.min(diff, 1440 - diff));
        }*/
        for (int i = 1; i < timePoints.size(); i++) {
            int t1 = toMinutes(timePoints.get(i - 1));
            int t2 = toMinutes(timePoints.get(i));
            min = Math.min(min, t2 - t1);
        }
        min = Math.min(min, 1440 - (toMinutes(timePoints.get(timePoints.size() - 1)) - toMinutes(timePoints.get(0))));
        return min;
    }


    public int findMinDifference2(List<String> timePoints) {
        boolean[] existed = new boolean[1440];
        for (String timePoint : timePoints) {
            int pos = toMinutes(timePoint);
            if (existed[pos]) {
                return 0;
            } else {
                existed[pos] = true;
            }
        }
        int min = Integer.MAX_VALUE;
        int first = 0;
        while (!existed[first]) {
            first++;
        }
        int prev = first, curr = first + 1;
        while (curr < 1440) {
            while (curr < 1440 && !existed[curr]) {
                curr++;
            }
            if (curr < 1440) {
                min = Math.min(min, curr - prev);
                prev = curr;
                curr++;
            }
        }
        min = Math.min(min, 1440 + first - prev);
        return min;
    }

    private int toMinutes(String s) {
        char[] t = s.toCharArray();
        return ((t[0] - '0') * 10 + t[1] - '0') * 60 + (t[3] - '0') * 10 + t[4] - '0';
    }
}
