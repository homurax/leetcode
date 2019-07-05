/**
 * 14. Longest Common Prefix
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {

    /**
     * 连续比较
     */
    public String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) { return  ""; }

        String ans = strs[0];
        for (int index = 1; index < strs.length; index++) {
            ans = commonPrefix(ans, strs[index]);
            if (ans.isEmpty()) {
                return ans;
            }
        }

        return ans;
    }

    private String commonPrefix(String str1, String str2) {

        if (str1 == null || str2 == null || str1.isEmpty() || str2.isEmpty()) {
            return "";
        }
        int i = 0;
        while (i < str1.length() && i < str2.length() && str1.charAt(i) == str2.charAt(i)) {
            i++;
        }
        return str1.substring(0, i);
    }

    /**
     * 水平扫描一个思路 更简洁
     */
    public String longestCommonPrefix2(String[] strs) {

        if (strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        return prefix;
    }


    /**
     * 两两比较 => 分治处理
     */
    public String longestCommonPrefix3(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0 , strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        } else {
            int mid = (l + r) / 2;
            String lcpLeft = longestCommonPrefix(strs, l, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix2(lcpLeft, lcpRight);
        }
    }

    private String commonPrefix2(String left,String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i))
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }


    /**
     * 二分查找
     */
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int min = Integer.MAX_VALUE;
        for (String str : strs) {
            min = Math.min(min, str.length());
        }
        int low = 1;
        int high = min;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }

}
