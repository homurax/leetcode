/*
385. Mini Parser

Given a string s represents the serialization of a nested list, implement a parser to deserialize it and return the deserialized NestedInteger.

Each element is either an integer or a list whose elements may also be integers or other lists.

Example 1:

Input: s = "324"
Output: 324
Explanation: You should return a NestedInteger object which contains a single integer 324.

Example 2:

Input: s = "[123,[456,[789]]]"
Output: [123,[456,[789]]]
Explanation: Return a NestedInteger object containing a nested list with 2 elements:
 1. An integer containing value 123.
 2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
    a. An integer containing value 789

Constraints:

1 <= s.length <= 5 * 10^4
s consists of digits, square brackets "[]", negative sign '-', and commas ','.
s is the serialization of valid NestedInteger.
All the values in the input are in the range [-10^6, 10^6].
*/

type NestedInteger struct {
}

func (n NestedInteger) IsInteger() bool {
	return false
}

func (n NestedInteger) GetInteger() int {
	return 0
}

func (n *NestedInteger) SetInteger(value int) {}

func (n *NestedInteger) Add(elem NestedInteger) {}

func (n NestedInteger) GetList() []*NestedInteger {
	return nil
}

func deserialize(s string) *NestedInteger {
	// 标识符
	mark := &NestedInteger{}
	stack := []*NestedInteger{}
	n := len(s)
	for i := 0; i < n; {
		if s[i] == ',' {
			i++
			continue
		}
		// unicode.IsDigit(rune(s[i]))
		if s[i] == '-' || s[i] >= '0' && s[i] <= '9' {
			j := i
			if s[i] == '-' {
				j++
			}
			num := 0
			for j < n && s[j] >= '0' && s[j] <= '9' {
				num = num*10 + int(s[j]-'0')
				j++
			}
			if s[i] == '-' {
				num = -num
			}
			t := &NestedInteger{}
			t.SetInteger(num)
			stack = append(stack, t)
			i = j
		} else if s[i] == '[' {
			stack = append(stack, &NestedInteger{})
			stack = append(stack, mark)
			i++
		} else {
			temp := []*NestedInteger{}
			for len(stack) > 0 {
				poll := stack[len(stack)-1]
				stack = stack[:len(stack)-1]
				if poll == mark {
					break
				}
				temp = append(temp, poll)
			}
			last := stack[len(stack)-1]
			for j := len(temp) - 1; j >= 0; j-- {
				last.Add(*temp[j])
			}
			i++
		}
	}
	return stack[len(stack)-1]
}
