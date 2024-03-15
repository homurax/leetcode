/*
2013. Detect Squares


You are given a stream of points on the X-Y plane. Design an algorithm that:

Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
Given a query point, counts the number of ways to choose three points from the data structure such that the three points and the query point form an axis-aligned square with positive area.
An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.

Implement the DetectSquares class:

DetectSquares() Initializes the object with an empty data structure.
void add(int[] point) Adds a new point point = [x, y] to the data structure.
int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.


Example 1:


Input
["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
Output
[null, null, null, null, 1, 0, null, 2]

Explanation
DetectSquares detectSquares = new DetectSquares();
detectSquares.add([3, 10]);
detectSquares.add([11, 2]);
detectSquares.add([3, 2]);
detectSquares.count([11, 10]); // return 1. You can choose:
                               //   - The first, second, and third points
detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
detectSquares.count([11, 10]); // return 2. You can choose:
                               //   - The first, second, and third points
                               //   - The first, third, and fourth points


Constraints:

point.length == 2
0 <= x, y <= 1000
At most 3000 calls in total will be made to add and count.
*/

type DetectSquares struct {
	x2y map[int]map[int]int
}

func Constructor14() DetectSquares {
	return DetectSquares{x2y: make(map[int]map[int]int)}
}

func (d *DetectSquares) Add(point []int) {
	x, y := point[0], point[1]
	if y2Cnt, ok := d.x2y[x]; ok {
		y2Cnt[y]++
	} else {
		d.x2y[x] = map[int]int{y: 1}
	}
}

func (d *DetectSquares) Count(point []int) int {
	x, y := point[0], point[1]
	ans := 0
	y2Cnt := d.x2y[x]
	for ny, c1 := range y2Cnt {
		if ny == y {
			continue
		}
		l := y - ny
		for _, nx := range []int{x + l, x - l} {
			temp := d.x2y[nx]
			ans += c1 * temp[y] * temp[ny]
		}
	}
	return ans
}
