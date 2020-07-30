/**
 * 1496. Path Crossing
 *
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
 *
 * Return True if the path crosses itself at any point, that is, if at any time you are on a location you've previously visited. Return False otherwise.
 *
 *
 * Example 1:
 *
 * Input: path = "NES"
 * Output: false
 * Explanation: Notice that the path doesn't cross any point more than once.
 *
 * Example 2:
 *
 * Input: path = "NESWW"
 * Output: true
 * Explanation: Notice that the path visits the origin twice.
 *
 *
 * Constraints:
 *
 * 1 <= path.length <= 10^4
 * path will only consist of characters in {'N', 'S', 'E', 'W}
 */
public class PathCrossing {

    public boolean isPathCrossing(String path) {
        int x = 0, y = 0;
        Set<Integer> history = new HashSet<>();
        history.add(0);
        for (char c : path.toCharArray()) {
            switch (c) {
                case 'N': y++; break;
                case 'S': y--; break;
                case 'E': x++; break;
                case 'W': x--; break;
            }
            if (!history.add(x * 20001 + y)) {
                return true;
            }
        }
        return false;
    }

}
