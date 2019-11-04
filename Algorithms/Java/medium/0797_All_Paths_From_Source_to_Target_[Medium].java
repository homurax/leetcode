/**
 * 797. All Paths From Source to Target
 *
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 *
 * The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.
 *
 * Example:
 * Input: [[1,2], [3], [3], []]
 * Output: [[0,1,3],[0,2,3]]
 *
 * Explanation: The graph looks like this:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 *
 * Note:
 *
 * The number of nodes in the graph will be in the range [2, 15].
 * You can print different paths in any order, but you should keep the order of nodes inside one path.
 */
public class AllPathsFromSourceToTarget {

    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        help(new ArrayList<>(), graph, 0);
        return ans;
    }

    private void help(List<Integer> path, int[][] graph, int prev) {

        if (graph[prev].length == 0) {
            ans.add(path);
            return;
        }

        for (int i = 0; i < graph[prev].length; i++) {
            List<Integer> temp = new ArrayList<>(path);
            if (prev == 0) {
                temp.add(0);
            }
            temp.add(graph[prev][i]);
            help(temp, graph, graph[prev][i]);
        }
    }


    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        findPath(graph, ans, path, 0);
        return ans;
    }

    private void findPath(int[][] graph, List<List<Integer>> ans, List<Integer> path, int node) {
        path.add(node);
        if (node == graph.length - 1) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < graph[node].length; i++) {
            findPath(graph, ans, path, graph[node][i]);
            path.remove(path.size() - 1);
        }
    }

}
