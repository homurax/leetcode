/*
2385. Amount of Time for Binary Tree to Be Infected


You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.



Example 1:


Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.


Example 2:


Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.


Constraints:

The number of nodes in the tree is in the range [1, 10^5].
1 <= Node.val <= 10^5
Each node has a unique value.
A node with a value of start exists in the tree.
*/

func amountOfTime(root *TreeNode, start int) int {
	ans := 0
	targetLevel := -1
	var dfs func(*TreeNode, int) int
	dfs = func(node *TreeNode, level int) int {
		if node == nil {
			return 0
		}
		if node.Val == start {
			targetLevel = level
		}
		l := dfs(node.Left, level+1)
		inLeft := targetLevel != -1
		r := dfs(node.Right, level+1)
		if node.Val == start {
			ans = max(l, r)
		}
		if inLeft {
			ans = max(ans, targetLevel-level+r)
		} else {
			ans = max(ans, targetLevel-level+l)
		}
		return max(l, r) + 1
	}
	dfs(root, 0)
	return ans
}
