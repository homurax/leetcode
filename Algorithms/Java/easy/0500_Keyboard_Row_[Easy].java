/**
 * 500. Keyboard Row
 *
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
 *
 * Example:
 *
 * Input: ["Hello", "Alaska", "Dad", "Peace"]
 * Output: ["Alaska", "Dad"]
 *
 *
 * Note:
 *
 * You may use one character in the keyboard more than once.
 * You may assume the input string will only contain letters of alphabet.
 */
public class KeyboardRow {

    private static final String[] lines = {"qwertyuiopQWERTYUIOP", "asdfghjklASDFGHJKL", "zxcvbnmZXCVBNM"};

    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (test(lines[0], word) || test(lines[1], word) || test(lines[2], word)) {
                list.add(word);
            }
        }
        return list.toArray(new String[0]);
    }

    private boolean test(String line, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (line.indexOf(word.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

}
