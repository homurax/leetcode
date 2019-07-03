/**
 * 1103. Distribute Candies to People
 *
 * We distribute some number of candies, to a row of n = num_people people in the following way:
 *
 * We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to the last person.
 *
 * Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second person, and so on until we give 2 * n candies to the last person.
 *
 * This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach the end) until we run out of candies.  The last person will receive all of our remaining candies (not necessarily one more than the previous gift).
 *
 * Return an array (of length num_people and sum candies) that represents the final distribution of candies.
 *
 *
 *
 * Example 1:
 *
 * Input: candies = 7, num_people = 4
 * Output: [1,2,3,1]
 * Explanation:
 * On the first turn, ans[0] += 1, and the array is [1,0,0,0].
 * On the second turn, ans[1] += 2, and the array is [1,2,0,0].
 * On the third turn, ans[2] += 3, and the array is [1,2,3,0].
 * On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1].
 *
 * Example 2:
 *
 * Input: candies = 10, num_people = 3
 * Output: [5,2,3]
 * Explanation:
 * On the first turn, ans[0] += 1, and the array is [1,0,0].
 * On the second turn, ans[1] += 2, and the array is [1,2,0].
 * On the third turn, ans[2] += 3, and the array is [1,2,3].
 * On the fourth turn, ans[0] += 4, and the final array is [5,2,3].
 *
 *
 * Constraints:
 *
 * 1 <= candies <= 10^9
 * 1 <= num_people <= 1000
 */
public class DistributeCandiesToPeople {

    public int[] distributeCandies(int candies, int num_people) {

        int[] ans = new int[num_people];
        int index = 0, num = 0;
        while (candies > 0) {
            ans[index++ % num_people] += candies >= ++num ? num : candies;
            candies -= num;
        }
        return ans;
    }

    public int[] distributeCandies2(int candies, int num_people) {

        int[] ans = new int[num_people];

        int count = 0;
        long sum = 0;

        while (sum < candies) {
            long next = ((count) * num_people + 1 + (count + 1) * num_people) * num_people / 2;
            if (sum + next < candies) {
                count++;
                sum += next;
            } else {
                break;
            }
        }

        long remain = candies - sum;
        int index = 0;
        while (remain > 0) {
            if (remain >= (count * num_people) + index + 1) {
                ans[index] += (count * num_people) + index + 1;
                remain -= (count * num_people) + index + 1;
                index++;
            } else {
                ans[index] += remain;
                break;
            }
        }

        for (int i = 0; i < ans.length; i++) {
            ans[i] += (count - 1) * num_people * count / 2 + count * (i + 1);
        }
        return ans;
    }

    public int[] distributeCandies3(int candies, int num_people) {

        int[] ans = new int[num_people];
        int n = 1;
        while (n * (n + 1) <= 2 * candies) {
            n++;
        }
        for (int i = 1; i < n; i++) {
            ans[(i - 1) % num_people] += i;
            candies -= i;
        }
        ans[(n - 1) % num_people] += candies;
        return ans;
    }

}
