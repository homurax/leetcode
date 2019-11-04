/**
 * 1154. Day of the Year
 *
 * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.
 *
 * Example 1:
 * Input: date = "2019-01-09"
 * Output: 9
 * Explanation: Given date is the 9th day of the year in 2019.
 *
 * Example 2:
 * Input: date = "2019-02-10"
 * Output: 41
 *
 * Example 3:
 * Input: date = "2003-03-01"
 * Output: 60
 *
 * Example 4:
 * Input: date = "2004-03-01"
 * Output: 61
 *
 *
 * Constraints:
 *
 * date.length == 10
 * date[4] == date[7] == '-', and all other date[i]'s are digits
 * date represents a calendar date between Jan 1st, 1900 and Dec 31, 2019.
 */
public class DayOfTheYear {

    public int dayOfYear(String date) {
        return LocalDate.parse(date).getDayOfYear();
    }

    public int dayOfYear2(String date) {

        int[] firstDayArr = {1, 32, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
        int year = parse(date, 0, 4);
        int month = parse(date, 5, 2);
        int day = parse(date, 8, 2);
        int leap = 0;
        if (month > 2) {
            leap = ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0) ? 1 : 0;
        }
        return firstDayArr[month - 1] + day + leap - 1;
    }

    private int parse(String date, int offset, int length) {
        int sum = 0;
        while (length-- > 0) {
            sum = sum * 10 + date.charAt(offset++) - '0';
        }
        return sum;
    }

}
