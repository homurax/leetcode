/*
1311. Get Watched Videos by Your Friends

There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for the person with id = i.

Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, the level k of videos are all watched videos by people with the shortest path exactly equal to k with you. Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.

Example 1:

Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
Output: ["B","C"]
Explanation:
You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
Person with id = 1 -> watchedVideos = ["C"]
Person with id = 2 -> watchedVideos = ["B","C"]
The frequencies of watchedVideos by your friends are:
B -> 1
C -> 2

Example 2:

Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
Output: ["D"]
Explanation:
You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).

Constraints:

n == watchedVideos.length == friends.length
2 <= n <= 100
1 <= watchedVideos[i].length <= 100
1 <= watchedVideos[i][j].length <= 8
0 <= friends[i].length < n
0 <= friends[i][j] < n
0 <= id < n
1 <= level < n
if friends[i] contains j, then friends[j] contains i
*/

// 1. 找到 level 层好友
// 2. 统计视频观看次数
// 3. 排序
func watchedVideosByFriends(watchedVideos [][]string, friends [][]int, id int, level int) []string {
	n := len(friends)
	visited := make([]bool, n)
	visited[id] = true
	q := []int{id}
	for range level {
		var p []int
		for _, from := range q {
			for _, to := range friends[from] {
				if !visited[to] {
					p = append(p, to)
					visited[to] = true
				}
			}
		}
		q = p
	}
	cnt := make(map[string]int)
	for _, i := range q {
		for _, video := range watchedVideos[i] {
			cnt[video]++
		}
	}
	var ans []string
	for k := range cnt {
		ans = append(ans, k)
	}
	sort.Slice(ans, func(i, j int) bool {
		video1, video2 := ans[i], ans[j]
		cnt1, cnt2 := cnt[video1], cnt[video2]
		if cnt1 == cnt2 {
			return video1 < video2
		}
		return cnt1 < cnt2
	})
	return ans
}
