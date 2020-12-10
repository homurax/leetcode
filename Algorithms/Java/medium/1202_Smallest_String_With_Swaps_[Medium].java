/**
 * 1202. Smallest String With Swaps
 *
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 *
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 *
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 10^5
 * 2. 0 <= pairs.length <= 10^5
 * 3. 0 <= pairs[i][0], pairs[i][1] < s.length
 * 4. s only contains lower case English letters.
 */
public class SmallestStringWithSwaps {

    // 并查集
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        int[] swap = new int[n];
        for (int i = 0; i < n; i++) {
            swap[i] = i;
        }
        for (List<Integer> pair : pairs) {
            union(swap, pair.get(0), pair.get(1));
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(find(swap, i), unused -> new ArrayList<>()).add(i);
        }
        StringBuilder sb = new StringBuilder(s);
        for (List<Integer> indexList : map.values()) {
            int size = indexList.size();
            if (size > 1) {
                char[] temp = new char[size];
                for (int i = 0; i < size; i++) {
                    temp[i] = sb.charAt(indexList.get(i));
                }
                Arrays.sort(temp);
                for (int i = 0; i < size; i++) {
                    sb.setCharAt(indexList.get(i), temp[i]);
                }
            }
        }
        return sb.toString();
    }

    private void union(int[] swap, int p, int q) {
        int pRoot = find(swap, p);
        int qRoot = find(swap, q);
        if (pRoot != qRoot) {
            swap[pRoot] = qRoot;
        }
    }

    private int find(int[] swap, int p) {
        while (swap[p] != p) {
            swap[p] = swap[swap[p]];
            p = swap[p];
        }
        return p;
    }

}
