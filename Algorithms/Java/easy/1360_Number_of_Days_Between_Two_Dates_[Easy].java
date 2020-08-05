/**
 * 1360. Number of Days Between Two Dates
 *
 * Write a program to count the number of days between two dates.
 *
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
 *
 *
 *
 * Example 1:
 *
 * Input: date1 = "2019-06-29", date2 = "2019-06-30"
 * Output: 1
 *
 * Example 2:
 *
 * Input: date1 = "2020-01-15", date2 = "2019-12-31"
 * Output: 15
 *
 *
 * Constraints:
 *
 * The given dates are valid dates between the years 1971 and 2100.
 */
public class NumberOfDaysBetweenTwoDates {

    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(dateToInt(date1) - dateToInt(date2));
    }

    private boolean leapYear(int year) {
        return ((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0));
    }

    private int dateToInt(String date) {
        String[] dateArr = date.split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);
        int[] monthLength = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int ans = day - 1;
        while (month != 0) {
            month--;
            ans += monthLength[month];
            if (month == 2 && leapYear(year)) {
                ans += 1;
            }
        }
        ans += 365 * (year - 1971);
        ans += (year - 1) / 4 - 1971 / 4;
        ans -= (year - 1) / 100 - 1971 / 100;
        ans += (year - 1) / 400 - 1971 / 400;
        return ans;
    }

}
