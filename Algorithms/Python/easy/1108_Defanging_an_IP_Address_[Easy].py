"""
1108. Defanging an IP Address

Given a valid (IPv4) IP address, return a defanged version of that IP address.

A defanged IP address replaces every period "." with "[.]".



Example 1:

Input: address = "1.1.1.1"
Output: "1[.]1[.]1[.]1"
Example 2:

Input: address = "255.100.50.0"
Output: "255[.]100[.]50[.]0"


Constraints:

The given address is a valid IPv4 address.
"""


class Solution:
    def defangIPaddr(self, address: str) -> str:
        return address.replace('.', '[.]')

    def defangIPaddr2(self, address: str) -> str:
        split = address.split('.')
        return '[.]'.join(split)

    def defangIPaddr3(self, address: str) -> str:
        split = address.split('.')
        ans = ''
        for s in split:
            ans += s + '[.]'
        return ans[:-3]


F = Solution()
print(F.defangIPaddr('255.100.50.0'))
print(F.defangIPaddr2('255.100.50.0'))
print(F.defangIPaddr3('255.100.50.0'))
