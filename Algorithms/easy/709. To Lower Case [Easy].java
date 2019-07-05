/**
 * 709. To Lower Case
 * 
 * Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
 * 
 * Example 1:
 * Input: "Hello"
 * Output: "hello"
 * 
 * Example 2:
 * Input: "here"
 * Output: "here"
 * 
 * Example 3:
 * Input: "LOVELY"
 * Output: "lovely"
 */
public class Solution {

    /**
     * 显然这里需要自己实现 不可以直接使用 {@link String#toLowerCase() }
     * 转换为字符数组后做判断运算即可
     * 字母ASCII值大小写相差32
     */
    public String toLowerCase(String str) {

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] += 32;
            }
        }
        return new String(chars);
    }
}
