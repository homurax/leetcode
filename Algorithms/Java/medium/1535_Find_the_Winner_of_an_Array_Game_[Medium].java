/**
 * 1535. Find the Winner of an Array Game
 *
 *
 * Given an integer array arr of distinct integers and an integer k.
 *
 * A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]). In each round of the game, we compare arr[0] with arr[1], the larger integer wins and remains at position 0 and the smaller integer moves to the end of the array. The game ends when an integer wins k consecutive rounds.
 *
 * Return the integer which will win the game.
 *
 * It is guaranteed that there will be a winner of the game.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1,3,5,4,6,7], k = 2
 * Output: 5
 * Explanation: Let's see the rounds of the game:
 * Round |       arr       | winner | win_count
 *   1   | [2,1,3,5,4,6,7] | 2      | 1
 *   2   | [2,3,5,4,6,7,1] | 3      | 1
 *   3   | [3,5,4,6,7,1,2] | 5      | 1
 *   4   | [5,4,6,7,1,2,3] | 5      | 2
 * So we can see that 4 rounds will be played and 5 is the winner because it wins 2 consecutive games.
 *
 * Example 2:
 *
 * Input: arr = [3,2,1], k = 10
 * Output: 3
 * Explanation: 3 will win the first 10 rounds consecutively.
 *
 * Example 3:
 *
 * Input: arr = [1,9,8,2,3,7,6,4,5], k = 7
 * Output: 9
 *
 * Example 4:
 *
 * Input: arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000
 * Output: 99
 *
 *
 * Constraints:
 *
 * 1. 2 <= arr.length <= 10^5
 * 2. 1 <= arr[i] <= 10^6
 * 3. arr contains distinct integers.
 * 4. 1 <= k <= 10^9
 */
public class FindTheWinnerOfAnArrayGame {

    /**
     * 模拟比赛不需要真的移动数组
     *
     * 第 i 回合游戏一定在第 i-1 回合游戏中取得胜利的整数和 arr[i] 之间进行
     * 如果遍历 arr 之后仍未发现有整数赢得 k 个连续回合，则不需要继续模拟，arr 中的最大值即为游戏的赢家
     *
     * 因为最大值在遍历一遍后一定位于 arr[0]，并且可以一直胜利
     */
    public int getWinner1(int[] arr, int k) {
        int pre = Math.max(arr[0], arr[1]);
        if (k == 1) {
            return pre;
        }
        int consecutive = 1;
        int ans = pre;
        for (int i = 2; i < arr.length; i++) {
            int cur = arr[i];
            if (pre > cur) {
                if (++consecutive == k) {
                    return pre;
                }
            } else {
                pre = cur;
                consecutive = 1;
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public int getWinner(int[] arr, int k) {
        int cur = arr[0];
        int consecutive = 0;
        for (int i = 1; i < arr.length; i++) {
            if (cur < arr[i]) {
                cur = arr[i];
                consecutive = 1;
            } else {
                consecutive++;
            }
            if (consecutive >= k) {
                return cur;
            }
        }
        return cur;
    }

}
