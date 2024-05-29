/**
 * 825. Friends Of Appropriate Ages
 *
 *
 * There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.
 *
 * A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:
 *
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * Otherwise, x will send a friend request to y.
 *
 * Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.
 *
 * Return the total number of friend requests made.
 *
 *
 *
 * Example 1:
 *
 * Input: ages = [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 *
 * Example 2:
 *
 * Input: ages = [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 *
 * Example 3:
 *
 * Input: ages = [20,30,100,110,120]
 * Output: 3
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 *
 *
 * Constraints:
 *
 * n == ages.length
 * 1 <= n <= 2 * 10^4
 * 1 <= ages[i] <= 120
 */
public class FriendsOfAppropriateAges {

    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int n = ages.length, ans = 0;
        for (int k = 0, i = 0, j = 0; k < n; k++) {
            while (i < k && !check(ages[i], ages[k])) {
                i++;
            }
            if (j < k) {
                j = k;
            }
            while (j < n && check(ages[j], ages[k])) {
                j++;
            }
            if (j > i) {
                ans += j - i - 1;
            }
        }
        return ans;
    }

    private boolean check(int x, int y) {
        if (y <= 0.5 * x + 7) {
            return false;
        }
        if (y > x) {
            return false;
        }
        return true;
    }

}
