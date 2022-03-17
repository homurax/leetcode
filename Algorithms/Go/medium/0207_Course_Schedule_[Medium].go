/*
207. Course Schedule


There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


Constraints:

1. 1 <= numCourses <= 10^5
2. 0 <= prerequisites.length <= 5000
3. prerequisites[i].length == 2
4. 0 <= ai, bi < numCourses
5. All the pairs prerequisites[i] are unique.
*/

// BFS
func canFinish(numCourses int, prerequisites [][]int) bool {
	edges := make([][]int, numCourses)
	depth := make([]int, numCourses)
	for _, pair := range prerequisites {
		edges[pair[1]] = append(edges[pair[1]], pair[0])
		depth[pair[0]]++
	}
	var queue []int
	for i, d := range depth {
		if d == 0 {
			queue = append(queue, i)
		}
	}
	index := 0
	for len(queue) > 0 {
		u := queue[0]
		queue = queue[1:]
		index++
		for _, v := range edges[u] {
			depth[v]--
			if depth[v] == 0 {
				queue = append(queue, v)
			}
		}
	}
	return index == numCourses
}

// DFS
func canFinish1(numCourses int, prerequisites [][]int) bool {
	edges := make([][]int, numCourses)
	visited := make([]int, numCourses)
	cycle := false
	for _, pair := range prerequisites {
		edges[pair[1]] = append(edges[pair[1]], pair[0])
	}
	var dfs func(u int)
	dfs = func(u int) {
		visited[u] = 1
		for _, v := range edges[u] {
			if visited[v] == 0 {
				dfs(v)
				if cycle {
					return
				}
			} else if visited[v] == 1 {
				cycle = true
				return
			}
		}
		visited[u] = 2
	}
	for i := 0; i < numCourses && !cycle; i++ {
		if visited[i] == 0 {
			dfs(i)
		}
	}
	return !cycle
}
