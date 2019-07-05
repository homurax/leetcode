/**
 * 1010. Pairs of Songs With Total Durations Divisible by 60
 *
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 *
 * Example 2:
 *
 * Input: [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 *
 *
 * Note:
 *
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {


    public int numPairsDivisibleBy60(int[] time) {

        int ans = 0;
        int[] table = new int[60];
        for (int i : time) {
            int remainder = i % 60;
            ans += remainder == 0 ? table[0] : table[60 - remainder];
            table[remainder]++;
        }

        return ans;
    }

    /**
     * C(n,2) = n(n-1)/2
     */
    public int numPairsDivisibleBy602(int[] time) {

        int[] table = new int[60];

        for (int i : time) {
            table[i % 60]++;
        }
        int ans = table[0] < 2 ? 0 : table[0] * (table[0] - 1) / 2;
        ans += table[30] <2 ? 0 : table[30] * (table[30] - 1) / 2;
        for (int i = 1; i < 30; i++) {
            ans += table[i] * table[60 - i];
        }

        return ans;
    }


}
