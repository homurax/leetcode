/*
2415. Reverse Odd Levels of Binary Tree


Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.

For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
Return the root of the reversed tree.

A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.

The level of a node is the number of edges along the path between it and the root node.



Example 1:


Input: root = [2,3,5,8,13,21,34]
Output: [2,5,3,8,13,21,34]
Explanation:
The tree has only one odd level.
The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.


Example 2:


Input: root = [7,13,11]
Output: [7,11,13]
Explanation:
The nodes at level 1 are 13, 11, which are reversed and become 11, 13.


Example 3:

Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
Explanation:
The odd levels have non-zero values.
The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.


Constraints:

1. The number of nodes in the tree is in the range [1, 2^14].
2. 0 <= Node.val <= 10^5
3. root is a perfect binary tree.
*/

func reverseOddLevels(root *TreeNode) *TreeNode {
	dfs(root.Left, root.Right, true)
	return root
}

func dfs(node1, node2 *TreeNode, isOddLevel bool) {
	if node1 == nil {
		return
	}
	if isOddLevel {
		node1.Val, node2.Val = node2.Val, node1.Val
	}
	dfs(node1.Left, node2.Right, !isOddLevel)
	dfs(node1.Right, node2.Left, !isOddLevel)
}

func reverseOddLevels1(root *TreeNode) *TreeNode {
	level := []*TreeNode{root}
	for l := 0; level[0].Left != nil; l ^= 1 {
		temp := make([]*TreeNode, len(level)*2)
		for i, node := range level {
			temp[i*2] = node.Left
			temp[i*2+1] = node.Right
		}
		level = temp
		if l == 0 {
			for i, n := 0, len(temp); i < n/2; i++ {
				a, b := temp[i], temp[n-1-i]
				a.Val, b.Val = b.Val, a.Val
			}
		}
	}
	return root
}
