/**
 * 208. Implement Trie (Prefix Tree)
 *
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class ImplementTrie {

    class Trie {

        class TrieNode {
            boolean existed;
            TrieNode[] nodes;
        }

        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (node.nodes == null) {
                    node.nodes = new TrieNode[26];
                }
                if (node.nodes[c - 'a'] == null) {
                    node.nodes[c - 'a'] = new TrieNode();
                }
                node = node.nodes[c - 'a'];
            }
            node.existed = true;
        }

        public boolean search(String word) {
            TrieNode node = find(word);
            return node != null && node.existed;
        }

        public boolean startsWith(String prefix) {
            return find(prefix) != null;
        }

        private TrieNode find(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (node.nodes == null || node.nodes[c - 'a'] == null) {
                    return null;
                }
                node = node.nodes[c - 'a'];
            }
            return node;
        }
    }
}
