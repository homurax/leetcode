/**
 * 3175. Find The First Player to win K Games in a Row
 *
 *
 * A competition consists of n players numbered from 0 to n - 1.
 *
 * You are given an integer array skills of size n and a positive integer k, where skills[i] is the skill level of player i. All integers in skills are unique.
 *
 * All players are standing in a queue in order from player 0 to player n - 1.
 *
 * The competition process is as follows:
 *
 * The first two players in the queue play a game, and the player with the higher skill level wins.
 * After the game, the winner stays at the beginning of the queue, and the loser goes to the end of it.
 * The winner of the competition is the first player who wins k games in a row.
 *
 * Return the initial index of the winning player.
 *
 *
 *
 * Example 1:
 *
 * Input: skills = [4,2,6,3,9], k = 2
 *
 * Output: 2
 *
 * Explanation:
 *
 * Initially, the queue of players is [0,1,2,3,4]. The following process happens:
 *
 * Players 0 and 1 play a game, since the skill of player 0 is higher than that of player 1, player 0 wins. The resulting queue is [0,2,3,4,1].
 * Players 0 and 2 play a game, since the skill of player 2 is higher than that of player 0, player 2 wins. The resulting queue is [2,3,4,1,0].
 * Players 2 and 3 play a game, since the skill of player 2 is higher than that of player 3, player 2 wins. The resulting queue is [2,4,1,0,3].
 * Player 2 won k = 2 games in a row, so the winner is player 2.
 *
 *
 * Example 2:
 *
 * Input: skills = [2,5,4], k = 3
 *
 * Output: 1
 *
 * Explanation:
 *
 * Initially, the queue of players is [0,1,2]. The following process happens:
 *
 * Players 0 and 1 play a game, since the skill of player 1 is higher than that of player 0, player 1 wins. The resulting queue is [1,2,0].
 * Players 1 and 2 play a game, since the skill of player 1 is higher than that of player 2, player 1 wins. The resulting queue is [1,0,2].
 * Players 1 and 0 play a game, since the skill of player 1 is higher than that of player 0, player 1 wins. The resulting queue is [1,2,0].
 * Player 1 won k = 3 games in a row, so the winner is player 1.
 *
 *
 *
 * Constraints:
 *
 * n == skills.length
 * 2 <= n <= 10^5
 * 1 <= k <= 10^9
 * 1 <= skills[i] <= 10^6
 * All integers in skills are unique.
 */
public class FindTheFirstPlayerToWinKGamesInARow {

    // K 足够大时会 TLE
    public int findWinningPlayer(int[] skills, int k) {
        int n = skills.length;
        Deque<Integer> deque = new ArrayDeque<>(n - 1);
        for (int i = 1; i < n; i++) {
            deque.addLast(i);
        }
        int win = 0;
        int winner = 0;
        while (win < k) {
            int player = deque.removeFirst();
            if (skills[winner] > skills[player]) {
                win++;
                deque.addLast(player);
            } else {
                deque.addLast(winner);
                win = 1;
                winner = player;
            }
        }
        return winner;
    }


    // 不要去模拟
    // 比较一轮后的胜者就是最大值, 会一直赢下去, 即 k 很大的情况
    public int findWinningPlayer1(int[] skills, int k) {
        int win = 0;
        int winner = 0;
        for (int i = 1; i < skills.length && win < k; i++) {
            if (skills[i] > skills[winner]) {
                win = 0;
                winner = i;
            }
            win++;
        }
        return winner;
    }

}
