/*
2001. Number of Pairs of Interchangeable Rectangles


You are given n rectangles represented by a 0-indexed 2D integer array rectangles, where rectangles[i] = [widthi, heighti] denotes the width and height of the ith rectangle.

Two rectangles i and j (i < j) are considered interchangeable if they have the same width-to-height ratio. More formally, two rectangles are interchangeable if widthi/heighti == widthj/heightj (using decimal division, not integer division).

Return the number of pairs of interchangeable rectangles in rectangles.



Example 1:

Input: rectangles = [[4,8],[3,6],[10,20],[15,30]]
Output: 6
Explanation: The following are the interchangeable pairs of rectangles by index (0-indexed):
- Rectangle 0 with rectangle 1: 4/8 == 3/6.
- Rectangle 0 with rectangle 2: 4/8 == 10/20.
- Rectangle 0 with rectangle 3: 4/8 == 15/30.
- Rectangle 1 with rectangle 2: 3/6 == 10/20.
- Rectangle 1 with rectangle 3: 3/6 == 15/30.
- Rectangle 2 with rectangle 3: 10/20 == 15/30.

Example 2:

Input: rectangles = [[4,5],[7,8]]
Output: 0
Explanation: There are no interchangeable pairs of rectangles.


Constraints:

1. n == rectangles.length
2. 1 <= n <= 10^5
3. rectangles[i].length == 2
4. 1 <= widthi, heighti <= 10^5
*/

func interchangeableRectangles(rectangles [][]int) int64 {
	ratios := make(map[float64]int)
	for _, rectangle := range rectangles {
		ratios[float64(rectangle[0])/float64(rectangle[1])]++
	}
	ans := 0
	for _, v := range ratios {
		ans += v * (v - 1) / 2
	}
	return int64(ans)
}

func interchangeableRectangles1(rectangles [][]int) int64 {
	n := len(rectangles)
	ratios := make([]float64, n)
	for i, rectangle := range rectangles {
		ratios[i] = float64(rectangle[0]) / float64(rectangle[1])
	}
	sort.Float64s(ratios)
	ans, prev := 0, 0
	for i := 1; i < n; i++ {
		if ratios[i-1] == ratios[i] {
			ans += i - prev
		} else {
			prev = i
		}
	}
	return int64(ans)
}
