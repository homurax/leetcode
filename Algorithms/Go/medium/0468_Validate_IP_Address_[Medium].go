/*
468. Validate IP Address

Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.

A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00", and "192.168@1.1" are invalid IPv4 addresses.

A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:

1 <= xi.length <= 4
xi is a hexadecimal string which may contain digits, lowercase English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
Leading zeros are allowed in xi.
For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.

Example 1:

Input: queryIP = "172.16.254.1"
Output: "IPv4"
Explanation: This is a valid IPv4 address, return "IPv4".

Example 2:

Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
Output: "IPv6"
Explanation: This is a valid IPv6 address, return "IPv6".

Example 3:

Input: queryIP = "256.256.256.256"
Output: "Neither"
Explanation: This is neither a IPv4 address nor a IPv6 address.

Constraints:

queryIP consists only of English letters, digits and the characters '.' and ':'.
*/
func validIPAddress(queryIP string) string {
	if strings.Contains(queryIP, ".") && check4(queryIP) {
		return "IPv4"
	}
	if strings.Contains(queryIP, ":") && check6(queryIP) {
		return "IPv6"
	}
	return "Neither"
}

func check4(ip string) bool {
	ipArr := strings.Split(ip, ".")
	if len(ipArr) != 4 {
		return false
	}
	for _, item := range ipArr {
		n := len(item)
		if n == 0 || n > 3 {
			return false
		}
		if n > 1 && item[0] == '0' {
			return false
		}
		num := 0
		for _, c := range item {
			if c < '0' || c > '9' {
				return false
			}
			num = num*10 + int(c-'0')
		}
		if num > 255 {
			return false
		}
	}
	return true
}

func check6(ip string) bool {
	ipArr := strings.Split(ip, ":")
	if len(ipArr) != 8 {
		return false
	}

	for _, item := range ipArr {
		n := len(item)
		if n == 0 || n > 4 {
			return false
		}
		for _, c := range item {
			if !unicode.IsDigit(c) && !('a' <= unicode.ToLower(c) && unicode.ToLower(c) <= 'f') {
				return false
			}
		}
	}
	return true
}
