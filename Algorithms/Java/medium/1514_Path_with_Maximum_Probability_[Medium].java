/**
 * 1514. Path with Maximum Probability
 *
 *
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 *
 * Example 2:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 *
 * Example 3:
 *
 *
 *
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 *
 * Constraints:
 *
 * 1. 2 <= n <= 10^4
 * 2. 0 <= start, end < n
 * 3. start != end
 * 4. 0 <= a, b < n
 * 5. a != b
 * 6. 0 <= succProb.length == edges.length <= 2*10^4
 * 7. 0 <= succProb[i] <= 1
 * 8. There is at most one edge between every two nodes.
 */
public class PathWithMaximumProbability {

    class Pair implements Comparable<Pair> {

        int node;
        double probability;

        public Pair(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }

        public int compareTo(Pair pair2) {
            if (this.probability == pair2.probability) {
                return this.node - pair2.node;
            } else {
                return this.probability - pair2.probability > 0 ? -1 : 1;
            }
        }
    }

    // 可以用 Dijkstra Pair 可以换成 double[2]
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int[] e = edges[i];
            graph.get(e[0]).add(new Pair(e[1], succProb[i]));
            graph.get(e[1]).add(new Pair(e[0], succProb[i]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        double[] probability = new double[n];

        pq.offer(new Pair(start, 1));
        probability[start] = 1;
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int node = pair.node;
            if (pair.probability < probability[node]) {
                continue;
            }
            for (Pair nextPair : graph.get(node)) {
                double nextProbability = nextPair.probability;
                int nextNode = nextPair.node;
                if (probability[nextNode] < probability[node] * nextProbability) {
                    probability[nextNode] = probability[node] * nextProbability;
                    pq.offer(new Pair(nextNode, probability[nextNode]));
                }
            }
        }
        return probability[end];
    }

    public double maxProbability1(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] probability = new double[n];
        probability[start] = 1;
        for (int i = 0; i < n; i++) {
            boolean change = false;
            for (int j = 0; j < edges.length; j++) {
                int[] e = edges[j];
                int from = e[0];
                int to = e[1];
                double prob = succProb[j];
                if (probability[from] * prob > probability[to]) {
                    probability[to] = probability[from] * prob;
                    change = true;
                }
                if (probability[to] * prob > probability[from]) {
                    probability[from] = probability[to] * prob;
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }
        return probability[end];
    }

}
