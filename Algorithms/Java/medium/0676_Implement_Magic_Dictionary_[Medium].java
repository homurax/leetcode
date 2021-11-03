/**
 * 676. Implement Magic Dictionary
 *
 *
 * Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.
 *
 * Implement the MagicDictionary class:
 *
 * MagicDictionary() Initializes the object.
 * void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
 * bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
 *
 *
 * Example 1:
 *
 * Input
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * Output
 * [null, null, false, true, false, false]
 *
 * Explanation
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // return False
 * magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
 * magicDictionary.search("hell"); // return False
 * magicDictionary.search("leetcoded"); // return False
 *
 *
 * Constraints:
 *
 * 1. 1 <= dictionary.length <= 100
 * 2. 1 <= dictionary[i].length <= 100
 * 3. dictionary[i] consists of only lower-case English letters.
 * 4. All the strings in dictionary are distinct.
 * 5. 1 <= searchWord.length <= 100
 * 6. searchWord consists of only lower-case English letters.
 * 7. buildDict will be called only once before search.
 * 8. At most 100 calls will be made to search.
 */
public class ImplementMagicDictionary {

    class MagicDictionary {

        private List<String>[] arr;

        public MagicDictionary() {
            this.arr = new List[101];
        }

        public void buildDict(String[] dictionary) {
            for (String word : dictionary) {
                int len = word.length();
                if (arr[len] == null) {
                    arr[len] = new ArrayList<>();
                }
                arr[len].add(word);
            }
        }

        public boolean search(String searchWord) {
            int len = searchWord.length();
            if (arr[len] == null) {
                return false;
            }
            for (String word : arr[len]) {
                int diff = 0;
                for (int i = 0; i < len; i++) {
                    if (word.charAt(i) != searchWord.charAt(i)) {
                        if (++diff > 1) {
                            break;
                        }
                    }
                }
                if (diff == 1) {
                    return true;
                }
            }
            return false;
        }
    }


}
