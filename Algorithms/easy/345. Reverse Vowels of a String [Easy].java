/**
 * 345. Reverse Vowels of a String
 *
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * Example 1:
 *
 * Input: "hello"
 * Output: "holle"
 * Example 2:
 *
 * Input: "leetcode"
 * Output: "leotcede"
 * Note:
 * The vowels does not include the letter "y".
 */
public class ReverseVowelsOfAString {


    /**
     * String#indexOf 可以优化为int数组 对aeiouAEIOU打表
     * 判断效率会提升
     */
    public String reverseVowels(String s) {

        String vowels = "aeiouAEIOU";
        int length = s.length(), low = 0, high = length - 1;
        char[] chars = s.toCharArray();

        while (low < high) {

            while (low < high && vowels.indexOf(chars[high]) == -1) {
                low++;
            }
            while (low < high && vowels.indexOf(chars[high]) == -1) {
                high--;
            }
            char temp = chars[low];
            chars[low++] = chars[high];
            chars[high--] = temp;
        }

        return new String(chars);
    }
}
