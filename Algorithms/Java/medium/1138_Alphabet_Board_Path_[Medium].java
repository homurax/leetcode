/**
 * 1138. Alphabet Board Path
 *
 *
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 *
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 *
 *
 *
 * We may make the following moves:
 *
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the board;
 * 'L' moves our position left one column, if the position exists on the board;
 * 'R' moves our position right one column, if the position exists on the board;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 *
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.
 *
 *
 *
 * Example 1:
 *
 * Input: target = "leet"
 * Output: "DDR!UURRR!!DDD!"
 *
 * Example 2:
 *
 * Input: target = "code"
 * Output: "RR!DDRR!UUL!R!"
 *
 *
 * Constraints:
 *
 * 1. 1 <= target.length <= 100
 * 2. target consists only of English lowercase letters.
 */
public class AlphabetBoardPath {

    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        int currX = 0, currY = 0;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            int targetX = (c - 'a') / 5, targetY = (c - 'a') % 5;
            if (c == 'z') {
                moveY(sb, currY, targetY);
                moveX(sb, currX, targetX);
            } else {
                moveX(sb, currX, targetX);
                moveY(sb, currY, targetY);
            }
            sb.append('!');
            currX = targetX;
            currY = targetY;
        }
        return sb.toString();
    }

    private void moveX(StringBuilder sb, int currX, int targetX) {
        if (currX < targetX) {
            while (currX < targetX) {
                sb.append('D');
                currX++;
            }
        } else {
            while (currX > targetX) {
                sb.append('U');
                currX--;
            }
        }
    }

    private void moveY(StringBuilder sb, int currY, int targetY) {
        if (currY > targetY) {
            while (currY > targetY) {
                sb.append('L');
                currY--;
            }
        } else {
            while (currY < targetY) {
                sb.append('R');
                currY++;
            }
        }
    }

}
