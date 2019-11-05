"""
709. To Lower Case

Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.

Example 1:

Input: "Hello"
Output: "hello"

Example 2:

Input: "here"
Output: "here"

Example 3:

Input: "LOVELY"
Output: "lovely"
"""


class Solution:

    def toLowerCase(self, s: str) -> str:
        # str.lower()
        # ''.join([chr(ord(i) + 32) if i.isupper() else i for i in str])
        for i, v in enumerate(s):
            print(i, v)
            s = s[:i] + v.lower() + s[i + 1:]
        return s


F = Solution()
print(F.toLowerCase('Hikari'))
