/**
 * 1753. Maximum Score From Removing Stones
 *
 *
 * You are playing a solitaire game with three piles of stones of sizes a, b, and c respectively. Each turn you choose two different non-empty piles, take one stone from each, and add 1 point to your score. The game stops when there are fewer than two non-empty piles (meaning there are no more available moves).
 *
 * Given three integers a, b, and c, return the maximum score you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 2, b = 4, c = 6
 * Output: 6
 * Explanation: The starting state is (2, 4, 6). One optimal set of moves is:
 * - Take from 1st and 3rd piles, state is now (1, 4, 5)
 * - Take from 1st and 3rd piles, state is now (0, 4, 4)
 * - Take from 2nd and 3rd piles, state is now (0, 3, 3)
 * - Take from 2nd and 3rd piles, state is now (0, 2, 2)
 * - Take from 2nd and 3rd piles, state is now (0, 1, 1)
 * - Take from 2nd and 3rd piles, state is now (0, 0, 0)
 * There are fewer than two non-empty piles, so the game ends. Total: 6 points.
 *
 *
 * Example 2:
 *
 * Input: a = 4, b = 4, c = 6
 * Output: 7
 * Explanation: The starting state is (4, 4, 6). One optimal set of moves is:
 * - Take from 1st and 2nd piles, state is now (3, 3, 6)
 * - Take from 1st and 3rd piles, state is now (2, 3, 5)
 * - Take from 1st and 3rd piles, state is now (1, 3, 4)
 * - Take from 1st and 3rd piles, state is now (0, 3, 3)
 * - Take from 2nd and 3rd piles, state is now (0, 2, 2)
 * - Take from 2nd and 3rd piles, state is now (0, 1, 1)
 * - Take from 2nd and 3rd piles, state is now (0, 0, 0)
 * There are fewer than two non-empty piles, so the game ends. Total: 7 points.
 *
 *
 * Example 3:
 *
 * Input: a = 1, b = 8, c = 8
 * Output: 8
 * Explanation: One optimal set of moves is to take from the 2nd and 3rd piles for 8 turns until they are empty.
 * After that, there are fewer than two non-empty piles, so the game ends.
 *
 *
 * Constraints:
 *
 * 1 <= a, b, c <= 10^5
 */
public class MaximumScoreFromRemovingStones {

    // 模拟
    public static int maximumScore1(int a, int b, int c) {
        int point = 0;
        int[] stones = new int[]{a, b, c};
        Arrays.sort(stones);
        while (stones[1] != 0) {
            stones[2]--;
            stones[1]--;
            Arrays.sort(stones);
            point++;
        }
        return point;
    }

    // 优化意义不大 本来 stones 就很小
    private void sortStones(int[] stones) {
        if (stones[0] > stones[1]) {
            stones[0] += stones[1];
            stones[1] = stones[0] - stones[1];
            stones[0] -= stones[1];
        }
        if (stones[1] > stones[2]) {
            stones[1] += stones[2];
            stones[2] = stones[1] - stones[2];
            stones[1] -= stones[2];
        }
        if (stones[0] > stones[1]) {
            stones[0] += stones[1];
            stones[1] = stones[0] - stones[1];
            stones[0] -= stones[1];
        }
    }


    /**
     * 设 a <= b <= c
     *  若 a + b <= c, 则先取空 a，再取空 b，得分为 a + b
     *
     *  若 a + b > c，先 a 与 b 互相消耗到 a' + b' <= c, 此时适用于第一种情况
     *  设 d = a + b - c, x = a - d, y = b - d, z = c - d, x + y = z
     *  则 a、b、c 分别取 x、y、z 时，剩余三堆均为 d
     *      d, d, d -> d-1, d-1, d ->  d-2, d-1, d-1 -> d-2, d-2, d-2
     *  当 d 为偶数时, 3d 为偶数, a+b+c 为偶数, 最终各堆剩余为 0,0,0; 最终所有石子均取出, 得分为 (a+b+c)/2
     *  当 d 为奇数时, 3d 为奇数，a+b+c 为奇数, 各堆剩余可化简至 1,1,1; 此时还可任意两堆取值, 最终剩一石子未取出, 得分为 (a+b+c-1)/2
     */
    public static int maximumScore(int a, int b, int c) {
        int[] stones = new int[]{a, b, c};
        Arrays.sort(stones);
        if (stones[0] + stones[1] <= stones[2]) {
            return stones[0] + stones[1];
        }
        return (a + b + c) / 2;
    }


}
