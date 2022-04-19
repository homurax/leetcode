/*
2121. Intervals Between Identical Elements


You are given a 0-indexed array of n integers arr.

The interval between two elements in arr is defined as the absolute difference between their indices. More formally, the interval between arr[i] and arr[j] is |i - j|.

Return an array intervals of length n where intervals[i] is the sum of intervals between arr[i] and each element in arr with the same value as arr[i].

Note: |x| is the absolute value of x.



Example 1:

Input: arr = [2,1,3,1,2,3,3]
Output: [4,2,7,2,4,4,5]
Explanation:
- Index 0: Another 2 is found at index 4. |0 - 4| = 4
- Index 1: Another 1 is found at index 3. |1 - 3| = 2
- Index 2: Two more 3s are found at indices 5 and 6. |2 - 5| + |2 - 6| = 7
- Index 3: Another 1 is found at index 1. |3 - 1| = 2
- Index 4: Another 2 is found at index 0. |4 - 0| = 4
- Index 5: Two more 3s are found at indices 2 and 6. |5 - 2| + |5 - 6| = 4
- Index 6: Two more 3s are found at indices 2 and 5. |6 - 2| + |6 - 5| = 5

Example 2:

Input: arr = [10,5,10,10]
Output: [5,0,3,4]
Explanation:
- Index 0: Two more 10s are found at indices 2 and 3. |0 - 2| + |0 - 3| = 5
- Index 1: There is only one 5 in the array, so its sum of intervals to identical elements is 0.
- Index 2: Two more 10s are found at indices 0 and 3. |2 - 0| + |2 - 3| = 3
- Index 3: Two more 10s are found at indices 0 and 2. |3 - 0| + |3 - 2| = 4


Constraints:

1. n == arr.length
2. 1 <= n <= 10^5
3. 1 <= arr[i] <= 10^5
*/

func getDistances(arr []int) []int64 {
	n := len(arr)
	buckets := make([][]int, 100001)
	for i, v := range arr {
		buckets[v] = append(buckets[v], i)
	}
	temp := make([]int, n)
	for _, indexes := range buckets {
		for _, index := range indexes {
			temp[indexes[0]] += index - indexes[0]
		}
		l := len(indexes)
		for i := 1; i < l; i++ {
			temp[indexes[i]] = temp[indexes[i-1]] + (2*i-l)*(indexes[i]-indexes[i-1])
		}
	}
	ans := make([]int64, n)
	for i, v := range temp {
		ans[i] = int64(v)
	}
	return ans
}
