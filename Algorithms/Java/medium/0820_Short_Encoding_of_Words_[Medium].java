/**
 * 820. Short Encoding of Words
 *
 * Given a list of words, we may encode it by writing a reference string S and a list of indexes A.
 *
 * For example, if the list of words is ["time", "me", "bell"], we can write it as S = "time#bell#" and indexes = [0, 2, 5].
 *
 * Then for each index, we will recover the word by reading from the reference string from that index until we reach a "#" character.
 *
 * What is the length of the shortest reference string S possible that encodes the given words?
 *
 * Example:
 *
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: S = "time#bell#" and indexes = [0, 2, 5].
 *
 *
 * Note:
 *
 * 1 <= words.length <= 2000.
 * 1 <= words[i].length <= 7.
 * Each word has only lowercase letters.
 */
public class ShortEncodingOfWords {

    class TrieNode {
        int count;
        TrieNode[] children;
        TrieNode() {
            this.count = 0;
            this.children = new TrieNode[26];
        }
        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                count++;
            }
            return children[c - 'a'];
        }
    }

    public int minimumLengthEncoding(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode wordHead = trie;
            for (int j = word.length() - 1; j >= 0; j--) {
                wordHead = wordHead.get(word.charAt(j));
            }
            map.put(wordHead, i);
        }

        int ans = 0;
        for (TrieNode wordHead : map.keySet()) {
            if (wordHead.count == 0) {
                ans += words[map.get(wordHead)].length() + 1;
            }
        }
        return ans;
    }




    public int minimumLengthEncoding2(String[] words) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, words);
        for (String word: words) {
            for (int k = 1; k < word.length(); ++k) {
                set.remove(word.substring(k));
            }
        }
        int ans = 0;
        for (String word: set) {
            ans += word.length() + 1;
        }
        return ans;
    }

}
