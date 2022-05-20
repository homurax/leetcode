/**
 * 1361. Validate Binary Tree Nodes
 *
 *
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
 *
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 *
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * Output: true
 *
 * Example 2:
 *
 *
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * Output: false
 *
 * Example 3:
 *
 *
 * Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1. n == leftChild.length == rightChild.length
 * 2. 1 <= n <= 10^4
 * 3. -1 <= leftChild[i], rightChild[i] <= n - 1
 */
public class ValidateBinaryTreeNodes {

    private boolean[] visited;
    private int count;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // 找根节点
        int[] root = new int[n];
        Arrays.fill(root, -1);
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                root[leftChild[i]] = i;
            }
            if (rightChild[i] != -1) {
                root[rightChild[i]] = i;
            }
        }
        int rootNode = -1;
        for (int i = 0; i < n; i++) {
            if (root[i] == -1) {
                // 根节点大于一个
                if (rootNode > -1) {
                    return false;
                }
                rootNode = i;
            }
        }
        // 没有合法根节点
        if (rootNode == -1) {
            return false;
        }
        visited = new boolean[n];
        count = 0;
        return dfs(rootNode, leftChild, rightChild) && count == n;
    }

    private boolean dfs(int node, int[] leftChild, int[] rightChild) {
        if (node == -1) {
            return true;
        }
        if (visited[node]) {
            return false;
        }
        visited[node] = true;
        count++;
        return dfs(leftChild[node], leftChild, rightChild) && dfs(rightChild[node], leftChild, rightChild);
    }

}
