/**
 * 520. Detect Capital
 *
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital if it has more than one letter, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 * Example 1:
 * Input: "USA"
 * Output: True
 * Example 2:
 * Input: "FlaG"
 * Output: False
 * Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 */
public class DetectCapital {


    /**
     * 依次检查四种情况
     */
    public boolean detectCapitalUse(String word) {

        if (word.length() == 1) {
            return true;
        }
        char a = word.charAt(0);
        char b = word.charAt(1);
        if (Character.isLowerCase(a) && Character.isUpperCase(b)) {// aB
            return false;
        }
        if (Character.isUpperCase(a) && Character.isUpperCase(b)) { // AA
            for (int i = 2; i < word.length(); i++) {
                if (Character.isLowerCase(word.charAt(i))) {
                    return false;
                }
            }
        } else { // Ab or ab
            for (int i = 2; i < word.length(); i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 先计算大写字母数量 再去判断
     */
    public boolean detectCapitalUse2(String word) {

        if (word.length() == 1) {
            return true;
        }

        int upCaseNum = 0;
        int length = word.length();
        for (char c : word.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                upCaseNum++;
            }
        }

        return upCaseNum == 0 // 全小写
                || upCaseNum == length // 全大写
                || (upCaseNum == 1 && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z'); // 首字母大写
    }


}
