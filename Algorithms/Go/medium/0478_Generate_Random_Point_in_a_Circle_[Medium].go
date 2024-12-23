/*
478. Generate Random Point in a Circle

Given the radius and the position of the center of a circle, implement the function randPoint which generates a uniform random point inside the circle.

Implement the Solution class:

Solution(double radius, double x_center, double y_center) initializes the object with the radius of the circle radius and the position of the center (x_center, y_center).
randPoint() returns a random point inside the circle. A point on the circumference of the circle is considered to be in the circle. The answer is returned as an array [x, y].

Example 1:

Input
["Solution", "randPoint", "randPoint", "randPoint"]
[[1.0, 0.0, 0.0], [], [], []]
Output
[null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]

Explanation
Solution solution = new Solution(1.0, 0.0, 0.0);
solution.randPoint(); // return [-0.02493, -0.38077]
solution.randPoint(); // return [0.82314, 0.38945]
solution.randPoint(); // return [0.36572, 0.17248]

Constraints:

0 < radius <= 108
-10^7 <= x_center, y_center <= 10^7
At most 3 * 10^4 calls will be made to randPoint.
*/
type Solution struct {
	radius, xCenter, yCenter float64
}

func Constructor(radius float64, x_center float64, y_center float64) Solution {
	return Solution{radius, x_center, y_center}
}

func (s *Solution) RandPoint() []float64 {
	r := math.Sqrt(rand.Float64())
	sin, cos := math.Sincos(rand.Float64() * 2 * math.Pi)
	return []float64{s.xCenter + r*cos*s.radius, s.yCenter + r*sin*s.radius}
}
