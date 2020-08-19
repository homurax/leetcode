/**
 * 1282. Group the People Given the Group Size They Belong To
 *
 * There are n people, each of them has a unique ID from 0 to n - 1 and each person of them belongs to exactly one group.
 *
 * Given an integer array groupSizes which indicated that the person with ID = i belongs to a group of groupSize[i] persons.
 *
 * Return an array of the groups where ans[j] contains the IDs of the jth group. Each ID should belong to exactly one group and each ID should be present in your answer. Also if a person with ID = i belongs to group j in your answer, then ans[j].length == groupSize[i] should be true.
 *
 * If there is multiple answers, return any of them. It is guaranteed that there will be at least one valid solution for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: groupSizes = [3,3,3,3,3,1,3]
 * Output: [[5],[0,1,2],[3,4,6]]
 * Explanation:
 * Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
 *
 * Example 2:
 *
 * Input: groupSizes = [2,1,3,3,3,2]
 * Output: [[1],[0,5],[2,3,4]]
 *
 *
 * Constraints:
 *
 * groupSizes.length == n
 * 1 <= n <= 500
 * 1 <= groupSizes[i] <= n
 */
public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            if (map.containsKey(size)) {
                map.get(size).add(i);
            } else {
                List<Integer> members = new ArrayList<>();
                members.add(i);
                map.put(size, members);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        map.forEach((size, members) -> {
            if (size == members.size()) {
                ans.add(members);
            } else {
                int fromIndex = 0, toIndex = size;
                while (toIndex <= members.size()) {
                    ans.add(members.subList(fromIndex, toIndex));
                    fromIndex = toIndex;
                    toIndex += size;
                }
            }
        });
        return ans;
    }


    public List<List<Integer>> groupThePeople2(int[] groupSizes) {
        boolean[] isVisited = new boolean[groupSizes.length];
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            if (!isVisited[i])
                collect(ans, isVisited, groupSizes, i);
        }
        return ans;
    }

    public void collect(List<List<Integer>> ans, boolean[] isVisited, int[] groupSizes, int i) {
        List<Integer> list = new ArrayList<>();
        int size = groupSizes[i--];
        while (list.size() < size && ++i < groupSizes.length) {
            if (groupSizes[i] == size && !isVisited[i]) {
                isVisited[i] = true;
                list.add(i);
            }
        }
        ans.add(list);
    }

}
