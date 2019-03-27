/**
 * 557. Reverse Words in a String III
 *
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 * Example 1:
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */
public class ReverseWordsInAStringIII {


    /**
     * 流式计算
     */
    public String reverseWords(String s) {

        return Arrays.stream(s.split(" "))
                .map(x -> new StringBuilder(x).reverse().toString())
                .collect(Collectors.joining(" "));
    }

    /**
     * 太耗时耗空间
     * 拆分多个数组 -> 每个数组内重排序 -> 转换为String
     */
    public String reverseWords1(String s) {

        String result = "";
        String[] split = s.split("\\s+");
        for (int i = 0, len = split.length; i < len; i++) {

            char[] chars = split[i].toCharArray();
            int length = chars.length;

            for (int j = 0, mid = length/2; j < mid; j++) {
                char temp = chars[j];
                chars[j] = chars[length - j - 1];
                chars[length - j - 1] = temp;
            }
            result += new String(chars) + (i == len - 1 ? "" : " ");
        }
        return result;
    }


    /**
     * 对整个字符串的char数组排序即可
     * 遇到空格时对fromIndex - index中排序即可
     * index为空格对应位置 遇到结尾则不需要减1
     */
    public String reverseWords2(String s) {

        int fromIndex = 0;
        int length = s.length();
        char[] chars = s.toCharArray();

        for (int i = 0; i < length; i++) {

            boolean end = i == length - 1;
            if (chars[i] == ' ' || end) {

                int index = end ? i : i - 1;
                int mid = (fromIndex + index) >>> 1;
                for (int j = fromIndex, k = 0; j <= mid; j++) {
                    int swap = index - k++;
                    char temp = chars[j];
                    chars[j] = chars[swap];
                    chars[swap] = temp;
                }
                fromIndex = i + 1;
            }
        }

        return new String(chars);
    }

    public String reverseWords3(String s) {

        final StringBuilder result = new StringBuilder();
        final StringBuilder word = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                word.append(s.charAt(i));
            } else {
                result.append(word.reverse());
                result.append(" ");
                word.setLength(0);
            }
        }

        result.append(word.reverse());
        return result.toString();
    }


    public String reverseWords4(String s) {

        int start = 0, end = 0;
        char[] chars = s.toCharArray();
        int len = s.length();

        while (end < len) {
            while (end < len && chars[end] != ' ') {
                end++;
            }
            reverse(chars, start, end - 1);
            start = end + 1;
            end = start;
        }

        s = String.valueOf(chars);
        return s;
    }

    public void reverse(char[] chars, int start, int end) {

        while (start < end) {
            char ch = chars[start];
            chars[start] = chars[end];
            chars[end] = ch;
            start++;
            end--;
        }
    }

}
