/*
1361. Validate Binary Tree Nodes


You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.



Example 1:


Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true

Example 2:


Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false

Example 3:


Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false


Constraints:

1. n == leftChild.length == rightChild.length
2. 1 <= n <= 10^4
3. -1 <= leftChild[i], rightChild[i] <= n - 1
*/

func validateBinaryTreeNodes(n int, leftChild []int, rightChild []int) bool {
	roots := make([]int, n)
	for i := 0; i < n; i++ {
		roots[i] = -1
	}
	for i := 0; i < n; i++ {
		if leftChild[i] != -1 {
			roots[leftChild[i]] = i
		}
		if rightChild[i] != -1 {
			roots[rightChild[i]] = i
		}
	}
	root := -1
	for i, v := range roots {
		if v == -1 {
			if root != -1 {
				return false
			}
			root = i
		}
	}
	if root == -1 {
		return false
	}
	count := 0
	visited := make([]bool, n)
	var dfs func(node int) bool
	dfs = func(node int) bool {
		if node == -1 {
			return true
		}
		if visited[node] {
			return false
		}
		visited[node] = true
		count++
		return dfs(leftChild[node]) && dfs(rightChild[node])
	}
	return dfs(root) && count == n
}
