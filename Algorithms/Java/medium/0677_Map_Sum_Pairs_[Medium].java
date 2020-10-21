/**
 * 677. Map Sum Pairs
 *
 *
 * Implement a MapSum class with insert, and sum methods.
 *
 * For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
 *
 * For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.
 *
 * Example 1:
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 */
public class MapSumPairs {

    class MapSum {

        class Node {
            int val;
            Node[] children;
            Node() {
                this.children = new Node[26];
            }
        }

        private final Node root;

        public MapSum() {
            this.root = new Node();
        }

        public void insert(String key, int val) {
            Node cur = root;
            char[] chars = key.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (cur.children[chars[i] - 'a'] == null) {
                    cur.children[chars[i] - 'a'] = new Node();
                }
                cur = cur.children[chars[i] - 'a'];
                if (i == chars.length - 1) {
                    cur.val = val;
                }
            }
        }

        public int sum(String prefix) {
            Node cur = root;
            for (char letter : prefix.toCharArray()) {
                if (cur.children[letter - 'a'] == null) {
                    return 0;
                } else {
                    cur = cur.children[letter - 'a'];
                }
            }
            return sum(cur);
        }

        private int sum(Node node) {
            int sum = node.val;
            for (Node child : node.children) {
                if (child != null) {
                    sum += sum(child);
                }
            }
            return sum;
        }
    }


}
