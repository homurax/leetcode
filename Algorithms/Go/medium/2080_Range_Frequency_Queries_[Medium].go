/*
2080. Range Frequency Queries


Design a data structure to find the frequency of a given value in a given subarray.

The frequency of a value in a subarray is the number of occurrences of that value in the subarray.

Implement the RangeFreqQuery class:

RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).



Example 1:

Input
["RangeFreqQuery", "query", "query"]
[[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
Output
[null, 1, 2]

Explanation
RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the subarray [33, 4]
rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in the whole array.


Constraints:

1. 1 <= arr.length <= 10^5
2. 1 <= arr[i], value <= 10^4
3. 0 <= left <= right < arr.length
4. At most 10^5 calls will be made to query
*/

type RangeFreqQuery struct {
	arr  []int
	freq []int
}

func Constructor1(arr []int) RangeFreqQuery {
	freq := make([]int, len(arr))
	cnt := make([]int, 10001)
	for i, v := range arr {
		cnt[v]++
		freq[i] = cnt[v]
	}
	return RangeFreqQuery{arr, freq}
}

func (rfq *RangeFreqQuery) Query(left int, right int, value int) int {
	l, r := right, left
	for i := left; i <= right; i++ {
		if rfq.arr[i] == value {
			l = i
			break
		}
	}
	for i := right; i >= l; i-- {
		if rfq.arr[i] == value {
			r = i
			break
		}
	}
	if rfq.arr[l] != value {
		return 0
	}
	return rfq.freq[r] - rfq.freq[l] + 1
}
