/*
593. Valid Square

Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.

The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.

A valid square has four equal sides with positive length and four equal angles (90-degree angles).

Example 1:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: true
Example 2:

Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
Output: false
Example 3:

Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
Output: true

Constraints:

p1.length == p2.length == p3.length == p4.length == 2
-10^4 <= xi, yi <= 10^4
*/
func validSquare(p1 []int, p2 []int, p3 []int, p4 []int) bool {
	var check func(a, b, c []int) bool
	check = func(a, b, c []int) bool {
		l1 := (a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1])
		l2 := (a[0]-c[0])*(a[0]-c[0]) + (a[1]-c[1])*(a[1]-c[1])
		l3 := (b[0]-c[0])*(b[0]-c[0]) + (b[1]-c[1])*(b[1]-c[1])
		ok := (l1 == l2 && l1+l2 == l3) || (l1 == l3 && l1+l3 == l2) || (l2 == l3 && l2+l3 == l1)
		if l1 == 0 || l2 == 0 || l3 == 0 {
			return false
		}
		return ok
	}
	return check(p1, p2, p3) && check(p2, p3, p4) && check(p1, p2, p4)
}
