/**
 * 763. Partition Labels
 *
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 *
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {

        int i = 0, len = S.length();
        List<Integer> ans = new ArrayList<>();
        Set<Character> seenSet = new HashSet<>();

        while (i < len) {
            char c = S.charAt(i);
            seenSet.add(c);
            int lastIndex = S.lastIndexOf(c);
            if (i == lastIndex) {
                ans.add(1);
            } else {
                for (int j = i + 1; j < lastIndex; j++) {
                    char among = S.charAt(j);
                    if (!seenSet.contains(among)) {
                        seenSet.add(among);
                        int amongLastIndex = S.lastIndexOf(among);
                        if (amongLastIndex > lastIndex) {
                            lastIndex = amongLastIndex;
                        }
                    }
                }
                ans.add(lastIndex - i + 1);
            }
            i = lastIndex + 1;
        }

        return ans;
    }


    public List<Integer> partitionLabels2(String S) {

        char[] arr = S.toCharArray();
        int[] lastIndexArr = new int[26];
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            lastIndexArr[arr[i] - 'a'] = i;
        }

        int start = 0, end = 0;
        for (int i = 0; i < arr.length; i++) {
            if (end < lastIndexArr[arr[i] - 'a']) {
                end = lastIndexArr[arr[i] - 'a'];
            }
            if (i == end) {
                ans.add(end - start + 1);
                start = i + 1;
            }
        }
        return ans;
    }




}
