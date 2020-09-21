/**
 * 684. Redundant Connection
 *
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 *
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 *
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 *
 * Update (2017-09-26):
 * We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.
 */
public class RedundantConnection {

    private int[] id;
    private int[] size;
    private int[] ans;

    // union-find
    public int[] findRedundantConnection(int[][] edges) {
        this.id = new int[edges.length];
        this.size = new int[edges.length];
        for (int i = 0; i < edges.length; i++) {
            this.id[i] = i;
            this.size[i] = 1;
        }
        for (int[] edge : edges) {
            if (connected(edge[0] - 1, edge[1] - 1)) {
                ans = edge;
            } else {
                union(edge[0] - 1, edge[1] - 1);
            }
        }
        return ans;
    }

    private boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }
        while (p != id[p]) {
            int temp = p;
            p = id[p];
            id[temp] = root;
        }
        return p;
    }

    private void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
    }


}
