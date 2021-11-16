/**
 * 752. Open the Lock
 *
 *
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 *
 *
 * Example 1:
 *
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 *
 * Example 2:
 *
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".
 *
 * Example 3:
 *
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.
 *
 * Example 4:
 *
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1. 1 <= deadends.length <= 500
 * 2. deadends[i].length == 4
 * 3. target.length == 4
 * 4. target will not be in the list deadends.
 * 5. target and deadends[i] consist of digits only.
 */
public class OpenTheLock {

    // BFS
    // 考虑每一步可能出现的所有状态
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> dead = new HashSet<>();
        Collections.addAll(dead, deadends);
        if (dead.contains("0000")) {
            return -1;
        }

        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.remove();
                for (String nextStatus : rotate(status)) {
                    if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    // status 旋转一次可能出现的新状态
    private List<String> rotate(String status) {
        List<String> ret = new ArrayList<>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; i++) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSuc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }

    private char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    private char numSuc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    // A* 算法
    // 启发式搜索
    // https://oi-wiki.org/search/astar/
    // https://en.wikipedia.org/wiki/A*_search_algorithm
    public int openLock2(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> dead = new HashSet<>();
        Collections.addAll(dead, deadends);
        if (dead.contains("0000")) {
            return -1;
        }

        PriorityQueue<AStar> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.f));
        pq.offer(new AStar("0000", target, 0));
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        while (!pq.isEmpty()) {
            AStar node = pq.poll();
            for (String nextStatus : rotate(node.status)) {
                if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                    if (nextStatus.equals(target)) {
                        return node.g + 1;
                    }
                    pq.offer(new AStar(nextStatus, target, node.g + 1));
                    seen.add(nextStatus);
                }
            }
        }
        return -1;
    }

}

// G(x) 表示从起点 s 到节点 x 的「实际」路径长度，注意 G(x) 并不一定是最短的
// H(x) 表示从节点 x 到终点 t 的「估计」最短路径长度，称为启发函数
// H∗(x) 表示从节点 x 到终点 t 的「实际」最短路径长度，这是在广度优先搜索的过程中无法求出的，需要用 H(x) 近似 H*(x)
// F(x) = G(x) + H(x)，即为从起点 s 到终点 t 的「估计」路径长度。总是挑选出最小的 F(x) 对应的 x 进行搜索，因此 A* 算法需要借助优先队列来实现
class AStar {
    String status;
    int f, g, h;

    public AStar(String status, String target, int g) {
        this.status = status;
        this.g = g;
        this.h = getH(status, target);
        this.f = this.g + this.h;
    }

    // 计算启发函数
    public static int getH(String status, String target) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int dist = Math.abs(status.charAt(i) - target.charAt(i));
            sum += Math.min(dist, 10 - dist);
        }
        return sum;
    }
}
