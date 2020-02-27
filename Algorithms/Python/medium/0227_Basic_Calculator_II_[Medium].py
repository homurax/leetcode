"""
227. Basic Calculator II

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5

Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
"""


class Solution:
    def calculate(self, s: str) -> int:
        num = 0
        operator = '+'
        stack = list()
        for i, c in enumerate(s):
            if c.isnumeric():
                num = num * 10 + int(c)
            if c in '+-*/' or i == len(s) - 1:
                if operator == '+':
                    stack.append(num)
                if operator == '-':
                    stack.append(-num)
                if operator == '*':
                    stack.append(stack.pop() * num)
                if operator == '/':
                    stack.append(int(stack.pop() / num))
                operator = c
                num = 0
        return sum(stack)


print(Solution().calculate('14-3/2'))
