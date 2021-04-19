/**
 * 211. Design Add and Search Words Data Structure
 *
 *
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * Constraints:
 *
 * 1. 1 <= word.length <= 500
 * 2. word in addWord consists lower-case English letters.
 * 3. word in search consist of  '.' or lower-case English letters.
 * 4. At most 50000 calls will be made to addWord and search.
 */
public class DesignAddAndSearchWordsDataStructure {

    /*class Node {
        Node[] nodes;
        boolean endFlag = false;
        public Node() {
            this.nodes = new Node[26];
        }
    }

    class WordDictionary {
        Node[] root;

        public WordDictionary() {
            this.root = new Node[26];
        }

        public void addWord(String word) {
            Node[] curr = root;
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                if (curr[letter - 'a'] == null) {
                    curr[letter - 'a'] = new Node();
                }
                if (i == word.length() - 1) {
                    curr[letter - 'a'].endFlag = true;
                }
                curr = curr[letter - 'a'].nodes;
            }
        }

        public boolean search(String word) {
            return search(word, 0, root, false);
        }

        private boolean search(String target, int index, Node[] curr, boolean prevEndFlag) {
            if (index == target.length()) {
                return prevEndFlag;
            }
            char letter = target.charAt(index);
            if (letter == '.') {
                for (Node node : curr) {
                    if (node == null) {
                        continue;
                    }
                    if (search(target, index + 1, node.nodes, node.endFlag)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (curr[letter - 'a'] == null) {
                    return false;
                } else {
                    return search(target, index + 1, curr[letter - 'a'].nodes, curr[letter - 'a'].endFlag);
                }
            }
        }
    }*/

    class Node {
        boolean word;
        Node[] nodes = new Node[26];
    }

    class WordDictionary {
        Node dummyHead;

        public WordDictionary() {
            this.dummyHead = new Node();
        }

        public void addWord(String word) {
            Node curr = dummyHead;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (curr.nodes[idx] == null) {
                    curr.nodes[idx] = new Node();
                }
                curr = curr.nodes[idx];
                if (i == word.length() - 1) {
                    curr.word = true;
                }
            }
        }

        public boolean search(String word) {
            return search(word, 0, dummyHead);
        }

        private boolean search(String word, int index, Node curr) {
            if (curr == null) {
                return false;
            }
            for (int i = index; i < word.length(); i++) {
                char letter = word.charAt(i);
                if (letter == '.') {
                    if (i == word.length() - 1) {
                        for (Node n : curr.nodes) {
                            if (n != null && n.word) {
                                return true;
                            }
                        }
                        return false;
                    }
                    for (Node n : curr.nodes) {
                        if (search(word, i + 1, n)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    if (curr.nodes[letter - 'a'] == null) {
                        return false;
                    }
                    curr = curr.nodes[letter - 'a'];
                }
                if (i == word.length() - 1) {
                    return curr.word;
                }
            }
            return false;
        }
    }

}
