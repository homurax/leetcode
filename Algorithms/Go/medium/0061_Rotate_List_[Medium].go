/*
61. Rotate List


Given the head of a linked list, rotate the list to the right by k places.



Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]


Example 2:


Input: head = [0,1,2], k = 4
Output: [2,0,1]


Constraints:

1. The number of nodes in the list is in the range [0, 500].
2. -100 <= Node.val <= 100
3. 0 <= k <= 2 * 10^9
*/

type ListNode struct {
	Val  int
	Next *ListNode
}

func rotateRight(head *ListNode, k int) *ListNode {
	if head == nil {
		return nil
	}
	l := 0
	temp := head
	tail := head
	for temp != nil {
		tail = temp
		temp = temp.Next
		l++
	}
	k = k % l
	if l == 1 || k == 0 {
		return head
	}
	target := l - k
	newTail := head
	for i := 1; i < target; i++ {
		newTail = newTail.Next
	}
	newHead := newTail.Next
	newTail.Next = nil
	tail.Next = head
	return newHead
}
