/*
2512. Reward Top K Students

You are given two string arrays positive_feedback and negative_feedback, containing the words denoting positive and negative feedback, respectively. Note that no word is both positive and negative.

Initially every student has 0 points. Each positive word in a feedback report increases the points of a student by 3, whereas each negative word decreases the points by 1.

You are given n feedback reports, represented by a 0-indexed string array report and a 0-indexed integer array student_id, where student_id[i] represents the ID of the student who has received the feedback report report[i]. The ID of each student is unique.

Given an integer k, return the top k students after ranking them in non-increasing order by their points. In case more than one student has the same points, the one with the lower ID ranks higher.

Example 1:

Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is studious","the student is smart"], student_id = [1,2], k = 2
Output: [1,2]
Explanation:
Both the students have 1 positive feedback and 3 points but since student 1 has a lower ID he ranks higher.

Example 2:

Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is not studious","the student is smart"], student_id = [1,2], k = 2
Output: [2,1]
Explanation:
- The student with ID 1 has 1 positive feedback and 1 negative feedback, so he has 3-1=2 points.
- The student with ID 2 has 1 positive feedback, so he has 3 points.
Since student 2 has more points, [2,1] is returned.

Constraints:

1 <= positive_feedback.length, negative_feedback.length <= 10^4
1 <= positive_feedback[i].length, negative_feedback[j].length <= 100
Both positive_feedback[i] and negative_feedback[j] consists of lowercase English letters.
No word is present in both positive_feedback and negative_feedback.
n == report.length == student_id.length
1 <= n <= 104
report[i] consists of lowercase English letters and spaces ' '.
There is a single space between consecutive words of report[i].
1 <= report[i].length <= 100
1 <= student_id[i] <= 10^9
All the values of student_id[i] are unique.
1 <= k <= n
*/
func topStudents(positive_feedback []string, negative_feedback []string, report []string, student_id []int, k int) []int {
	score := map[string]int{}
	for _, w := range positive_feedback {
		score[w] = 3
	}
	for _, w := range negative_feedback {
		score[w] = -1
	}
	type pair struct{ score, id int }
	a := make([]pair, len(report))
	for i, r := range report {
		s := 0
		for _, w := range strings.Split(r, " ") {
			s += score[w]
		}
		a[i] = pair{s, student_id[i]}
	}
	sort.Slice(a, func(i, j int) bool {
		a, b := a[i], a[j]
		return a.score > b.score || a.score == b.score && a.id < b.id
	})
	ans := make([]int, k)
	for i, p := range a[:k] {
		ans[i] = p.id
	}
	return ans
}
