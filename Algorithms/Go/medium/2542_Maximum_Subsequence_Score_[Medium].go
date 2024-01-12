/*
2542. Maximum Subsequence Score


You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k. You must choose a subsequence of indices from nums1 of length k.

For chosen indices i0, i1, ..., ik - 1, your score is defined as:

The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
Return the maximum possible score.

A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.



Example 1:

Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
Output: 12
Explanation:
The four possible subsequence scores are:
- We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
- We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
- We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
- We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
Therefore, we return the max score, which is 12.


Example 2:

Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
Output: 30
Explanation:
Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.


Constraints:

n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[j] <= 10^5
1 <= k <= n
*/

func maxScore(nums1 []int, nums2 []int, k int) int64 {
	type pair struct{ x, y int }
	pairs := make([]pair, len(nums1))
	for i, x := range nums1 {
		pairs[i] = pair{x, nums2[i]}
	}
	sort.Slice(pairs, func(i, j int) bool { return pairs[i].y > pairs[j].y })

	sum := 0
	h := hp2{nums2[:k]}
	for i, p := range pairs[:k] {
		sum += p.x
		h.IntSlice[i] = p.x
	}
	ans := sum * pairs[k-1].y
	heap.Init(&h)
	for _, p := range pairs[k:] {
		if p.x > h.IntSlice[0] {
			sum += p.x - h.replace(p.x)
			ans = max(ans, sum*p.y)
		}
	}
	return int64(ans)
}

type hp2 struct{ sort.IntSlice }

func (hp2) Pop() (_ interface{}) { return }
func (hp2) Push(interface{})     {}
func (h hp2) replace(v int) int {
	top := h.IntSlice[0]
	h.IntSlice[0] = v
	heap.Fix(&h, 0)
	return top
}
