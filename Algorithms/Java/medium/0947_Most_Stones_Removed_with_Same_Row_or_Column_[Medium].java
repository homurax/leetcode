/**
 * 947. Most Stones Removed with Same Row or Column
 *
 *
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 *
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Explanation: One way to remove 5 stones is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,1].
 * 2. Remove stone [2,1] because it shares the same column as [0,1].
 * 3. Remove stone [1,2] because it shares the same row as [1,0].
 * 4. Remove stone [1,0] because it shares the same column as [0,0].
 * 5. Remove stone [0,1] because it shares the same row as [0,0].
 * Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
 *
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Explanation: One way to make 3 moves is as follows:
 * 1. Remove stone [2,2] because it shares the same row as [2,0].
 * 2. Remove stone [2,0] because it shares the same column as [0,0].
 * 3. Remove stone [0,2] because it shares the same row as [0,0].
 * Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
 *
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 * Explanation: [0,0] is the only stone on the plane, so you cannot remove it.
 *
 *
 * Constraints:
 *
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 10^4
 * No two stones are at the same coordinate point.
 */
public class MostStonesRemovedWithSameRowOrColumn {

    public int removeStones(int[][] stones) {
        if (stones.length == 1) {
            return 0;
        }
        UnionFind uf = new UnionFind();
        for (int[] stone : stones) {
            uf.union(stone[0] + 1, stone[1] + 10001);
        }
        return stones.length - uf.count;
    }

    class UnionFind {
        int[] id;
        int count;

        public UnionFind() {
            this.id = new int[20002];
            this.count = 0;
        }

        public int find(int index) {
            if (id[index] == 0) {
                id[index] = index;
                count++;
            } else if (id[index] != index) {
                id[index] = find(id[index]);
            }
            return id[index];
        }

        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i != j) {
                count--;
            }
            id[j] = i;
        }
    }

}
