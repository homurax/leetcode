/**
 * 1452. People Whose List of Favorite Companies Is Not a Subset of Another List
 *
 *
 * Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies for the ith person (indexed from 0).
 *
 * Return the indices of people whose list of favorite companies is not a subset of any other list of favorites companies. You must return the indices in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
 * Output: [0,1,4]
 * Explanation:
 * Person with index=2 has favoriteCompanies[2]=["google","facebook"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] corresponding to the person with index 0.
 * Person with index=3 has favoriteCompanies[3]=["google"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] and favoriteCompanies[1]=["google","microsoft"].
 * Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
 *
 * Example 2:
 *
 * Input: favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
 * Output: [0,1]
 * Explanation: In this case favoriteCompanies[2]=["facebook","google"] is a subset of favoriteCompanies[0]=["leetcode","google","facebook"], therefore, the answer is [0,1].
 *
 * Example 3:
 *
 * Input: favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
 * Output: [0,1,2,3]
 *
 *
 * Constraints:
 *
 * 1. 1 <= favoriteCompanies.length <= 100
 * 2. 1 <= favoriteCompanies[i].length <= 500
 * 3. 1 <= favoriteCompanies[i][j].length <= 20
 * 4. All strings in favoriteCompanies[i] are distinct.
 * 5. All lists of favorite companies are distinct, that is, If we sort alphabetically each list then favoriteCompanies[i] != favoriteCompanies[j].
 * 6. All strings consist of lowercase English letters only.
 */
public class PeopleWhoseListOfFavoriteCompaniesIsNotASubsetOfAnotherList {

    /**
     * 用 BitSet 标识一个 company 在那些 list 中出现过
     * 一个 list 中的 company 进行 and 后如果 cardinality > 1，就说明这组组合出现过多次
     *
     *                1 1 1 1 1
     * leetcode     - 1 0 0 0 0
     * google       - 1 1 1 1 0
     * facebook     - 1 0 1 0 0
     * microsoft    - 0 1 0 0 0
     * amazon       - 0 0 0 0 1
     */
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        Map<String, BitSet> map = new HashMap<>();
        int n = favoriteCompanies.size();
        for (int i = 0; i < n; i++) {
            for (String company : favoriteCompanies.get(i)) {
                map.computeIfAbsent(company, k -> new BitSet(100)).set(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            BitSet bs = new BitSet(100);
            bs.set(0, 100);
            for (String company : favoriteCompanies.get(i)) {
                bs.and(map.get(company));
            }
            if (bs.cardinality() == 1) {
                ans.add(i);
            }
        }
        return ans;
    }


    public List<Integer> peopleIndexes2(List<List<String>> favoriteCompanies) {
        int n = favoriteCompanies.size();
        Set<String>[] setArr = new Set[n];
        for (int i = 0; i < n; i++) {
            setArr[i] = new HashSet<>(favoriteCompanies.get(i));
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (setArr[j].containsAll(setArr[i])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans.add(i);
            }
        }
        return ans;
    }

}
