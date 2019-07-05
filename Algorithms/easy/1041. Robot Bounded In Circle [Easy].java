/**
 * 1041. Robot Bounded In Circle
 *
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:
 *
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degress to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 *
 *
 * Example 1:
 *
 * Input: "GGLLGG"
 * Output: true
 * Explanation:
 * The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 * Example 2:
 *
 * Input: "GG"
 * Output: false
 * Explanation:
 * The robot moves north indefinitely.
 * Example 3:
 *
 * Input: "GL"
 * Output: true
 * Explanation:
 * The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 *
 *
 * Note:
 *
 * 1 <= instructions.length <= 100
 * instructions[i] is in {'G', 'L', 'R'}
 */
public class RobotBoundedInCircle {

    /**
     * 如果一次循环后仍在原点 或者朝向改变 即为true
     */
    public boolean isRobotBounded(String instructions) {

        int x = 0;
        int y = 0;
        int index = 0;
        int length = instructions.length();
        int[][] table = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0} }; // 上右下左

        for (int i = 0; i < length; i++) {
            if (instructions.charAt(i) == 'L') {
                index = (index + 1) % 4;
            } else if (instructions.charAt(i) == 'R') {
                index = (index + 3) % 4;
            } else {
                x += table[index][0];
                y += table[index][1];
            }
        }

        return x == 0 && y == 0 || index > 0;
    }

}
