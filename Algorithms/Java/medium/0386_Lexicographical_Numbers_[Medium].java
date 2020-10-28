/**
 * 386. Lexicographical Numbers
 *
 * Given an integer n, return 1 - n in lexicographical order.
 *
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 *
 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 */
public class LexicographicalNumbers {

    public List<Integer> lexicalOrder(int n) {
        return IntStream.rangeClosed(1, n).boxed().sorted(Comparator.comparing(Object::toString)).collect(Collectors.toList());
    }


    public List<Integer> lexicalOrder2(int n) {
        List<Integer> ans = new ArrayList<>(n);
        for (int i = 1; i <= 9; i++) {
            dfs(ans, n, i);
        }
        return ans;
    }

    private void dfs(List<Integer> ans, int n, int curr) {
        if (curr > n) {
            return;
        }
        ans.add(curr);
        for (int i = 0; i <= 9; i++) {
            dfs(ans, n, curr * 10 + i);
        }
    }
}
