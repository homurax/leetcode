/*
3413. Maximum Coins From K Consecutive Bags

There are an infinite amount of bags on a number line, one bag for each coordinate. Some of these bags contain coins.

You are given a 2D array coins, where coins[i] = [li, ri, ci] denotes that every bag from li to ri contains ci coins.

The segments that coins contain are non-overlapping.

You are also given an integer k.

Return the maximum amount of coins you can obtain by collecting k consecutive bags.

Example 1:

Input: coins = [[8,10,1],[1,3,2],[5,6,4]], k = 4

Output: 10

Explanation:

Selecting bags at positions [3, 4, 5, 6] gives the maximum number of coins: 2 + 0 + 4 + 4 = 10.

Example 2:

Input: coins = [[1,10,3]], k = 2

Output: 6

Explanation:

Selecting bags at positions [1, 2] gives the maximum number of coins: 3 + 3 = 6.

Constraints:

1 <= coins.length <= 10^5
1 <= k <= 10^9
coins[i] == [li, ri, ci]
1 <= li <= ri <= 10^9
1 <= ci <= 1000
The given segments are non-overlapping.
*/
func maximumWhiteTiles(tiles [][]int, carpetLen int) (ans int) {
	cover, left := 0, 0
	for _, tile := range tiles {
		tl, tr, c := tile[0], tile[1], tile[2]
		cover += (tr - tl + 1) * c
		carpetLeft := tr - carpetLen + 1
		for tiles[left][1] < carpetLeft {
			cover -= (tiles[left][1] - tiles[left][0] + 1) * tiles[left][2]
			left++
		}
		uncover := max((carpetLeft-tiles[left][0])*tiles[left][2], 0)
		ans = max(ans, cover-uncover)
	}
	return
}

func maximumCoins(coins [][]int, k int) int64 {
	slices.SortFunc(coins, func(a, b []int) int { return a[0] - b[0] })
	ans := maximumWhiteTiles(coins, k)

	slices.Reverse(coins)
	for _, t := range coins {
		t[0], t[1] = -t[1], -t[0]
	}
	return int64(max(ans, maximumWhiteTiles(coins, k)))
}
