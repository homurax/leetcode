/**
 * 1042. Flower Planting With No Adjacent
 *
 * You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.
 *
 * paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.
 *
 * Also, there is no garden that has more than 3 paths coming into or leaving it.
 *
 * Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.
 *
 * Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 3, paths = [[1,2],[2,3],[3,1]]
 * Output: [1,2,3]
 * Example 2:
 *
 * Input: N = 4, paths = [[1,2],[3,4]]
 * Output: [1,2,1,2]
 * Example 3:
 *
 * Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * Output: [1,2,3,4]
 *
 *
 * Note:
 *
 * 1 <= N <= 10000
 * 0 <= paths.size <= 20000
 * No garden has 4 or more paths coming into or leaving it.
 * It is guaranteed an answer exists.
 */
public class FlowerPlantingWithNoAdjacent {


    /**
     * 四种 任意花园周围最多三个花园
     * 选剩下的颜色即可
     */
    public int[] gardenNoAdj(int N, int[][] paths) {

        int[] ans = new int[N];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++)
            map.put(i, new HashSet<>());

        for (int[] path : paths) {
            map.get(path[0] - 1).add(path[1] - 1);
            map.get(path[1] - 1).add(path[0] - 1);
        }

        int[] colors = new int[5];
        for (int i = 0; i < N; i++) {

            for (int j : map.get(i)) {
                colors[ans[j]] = 1;
            }
            for (int j = 4; j > 0; j--) {
                if (colors[j] == 0)
                    ans[i] = j;
            }
            Arrays.fill(colors, 0);
        }
        return ans;
    }



    public int[] gardenNoAdj2(int N, int[][] paths) {

        int[] ans = new int[N];
        int length = paths.length;
        int[] fromArr = new int[length];
        int[] toArr = new int[length];
        for (int i = 0; i < length; i++) {
            fromArr[i] = paths[i][0] - 1;
            toArr[i] = paths[i][1] - 1;
        }

        //
        int[][] g = new int[N][];
        int[] p = new int[N];

        for (int form : fromArr)
            p[form]++;

        for (int to : toArr)
            p[to]++;

        for (int i = 0; i < N; i++)
            g[i] = new int[p[i]];

        for (int i = 0; i < length; i++) {
            g[fromArr[i]][--p[fromArr[i]]] = toArr[i];
            g[toArr[i]][--p[toArr[i]]] = fromArr[i];
        }


        for (int i = 0; i < N; i++) {
            int ptn = 0;
            for (int e : g[i]) {
                if (ans[e] != 0) {
                    ptn |= 1 << ans[e] - 1;
                }
            }
            ans[i] = Integer.numberOfTrailingZeros(~ptn) + 1;
        }
        return ans;
    }

}
