/**
 * 1319. Number of Operations to Make Network Connected
 *
 *
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
 *
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected. Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
 * Output: 1
 * Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
 *
 * Example 2:
 *
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * Output: 2
 *
 * Example 3:
 *
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * Output: -1
 * Explanation: There are not enough cables.
 *
 * Example 4:
 *
 * Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 10^5
 * 2. 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 * 3. connections[i].length == 2
 * 4. 0 <= connections[i][0], connections[i][1] < n
 * 5. connections[i][0] != connections[i][1]
 * 6. There are no repeated connections.
 * 7. No two computers are connected by more than one cable.
 */
public class NumberOfOperationsToMakeNetworkConnected {

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] conn : connections) {
            uf.union(conn[0], conn[1]);
        }
        return uf.count - 1;
    }

    class UnionFind {
        int[] id;
        int[] size;
        int count;

        public UnionFind(int n) {
            this.count = n;
            this.size = new int[n];
            Arrays.fill(size, 1);
            this.id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
        }

        public int find(int i) {
            return (id[i] == i) ? i : (id[i] = find(id[i]));
        }

        public void union(int p, int q) {
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
            count--;
        }
    }

}
