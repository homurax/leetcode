/**
 * 886. Possible Bipartition
 *
 *
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 *
 * Each person may dislike some other people, and they should not go into the same group.
 *
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 *
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 *
 * Example 2:
 *
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 *
 * Example 3:
 *
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. 1 <= N <= 2000
 * 2. 0 <= dislikes.length <= 10000
 * 3. dislikes[i].length == 2
 * 4. 1 <= dislikes[i][j] <= N
 * 5. dislikes[i][0] < dislikes[i][1]
 * 6. There does not exist i != j for which dislikes[i] == dislikes[j].
 */
public class PossibleBipartition {

    public boolean possibleBipartition1(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] dislike : dislikes) {
            graph.computeIfAbsent(dislike[0], key -> new ArrayList<>()).add(dislike[1]);
            graph.computeIfAbsent(dislike[1], key -> new ArrayList<>()).add(dislike[0]);
        }
        Map<Integer, Integer> colorMap = new HashMap<>();
        for (int people = 1; people <= N; people++) {
            if (!colorMap.containsKey(people) && !dfs(graph, colorMap, people, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, Map<Integer, Integer> colorMap, int people, int color) {
        if (colorMap.containsKey(people)) {
            return colorMap.get(people) == color;
        }
        colorMap.put(people, color);
        if (!graph.containsKey(people)) {
            return true;
        }
        for (Integer neighbor : graph.get(people)) {
            if (!dfs(graph, colorMap, neighbor, color ^ 1)) {
                return false;
            }
        }
        return true;
    }

    // union-find
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] colors = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            colors[i] = i;
        }
        for (int[] dislike : dislikes) {
            int p1 = dislike[0], p2 = dislike[1];
            if (colors[p2] == p2) {
                colors[p2] = p1;
            } else {
                int[] uf1 = find(p1, colors), uf2 = find(p2, colors);
                if (uf1[0] == uf2[0] && uf1[1] == uf2[1]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] find(int p, int[] colors) {
        int color = 0;
        while (colors[p] != p) {
            p = colors[p];
            color ^= 1;
        }
        return new int[]{p, color};
    }


}
