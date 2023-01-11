/*
2271. Maximum White Tiles Covered by a Carpet


You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.

You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.

Return the maximum number of white tiles that can be covered by the carpet.



Example 1:


Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
Output: 9
Explanation: Place the carpet starting on tile 10.
It covers 9 white tiles, so we return 9.
Note that there may be other places where the carpet covers 9 white tiles.
It can be shown that the carpet cannot cover more than 9 white tiles.


Example 2:


Input: tiles = [[10,11],[1,1]], carpetLen = 2
Output: 2
Explanation: Place the carpet starting on tile 10.
It covers 2 white tiles, so we return 2.


Constraints:

1. 1 <= tiles.length <= 5 * 10^4
2. tiles[i].length == 2
3. 1 <= li <= ri <= 10^9
4. 1 <= carpetLen <= 10^9
5. The tiles are non-overlapping.
*/

func maximumWhiteTiles(tiles [][]int, carpetLen int) int {
	sort.Slice(tiles, func(i, j int) bool {
		return tiles[i][0] < tiles[j][0]
	})
	ans, cover, left := 0, 0, 0
	for _, tile := range tiles {
		l, r := tile[0], tile[1]
		cover += r - l + 1
		for tiles[left][1]+carpetLen-1 < r {
			cover -= tiles[left][1] - tiles[left][0] + 1
			left++
		}
		ans = max(ans, cover-max(r-carpetLen+1-tiles[left][0], 0))
	}
	return ans
}
