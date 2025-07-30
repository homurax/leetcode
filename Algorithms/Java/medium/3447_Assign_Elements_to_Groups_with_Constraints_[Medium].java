/**
 * 3447. Assign Elements to Groups with Constraints
 *
 *
 * You are given an integer array groups, where groups[i] represents the size of the ith group. You are also given an integer array elements.
 *
 * Your task is to assign one element to each group based on the following rules:
 *
 * An element at index j can be assigned to a group i if groups[i] is divisible by elements[j].
 * If there are multiple elements that can be assigned, assign the element with the smallest index j.
 * If no element satisfies the condition for a group, assign -1 to that group.
 * Return an integer array assigned, where assigned[i] is the index of the element chosen for group i, or -1 if no suitable element exists.
 *
 * Note: An element may be assigned to more than one group.
 *
 *
 *
 * Example 1:
 *
 * Input: groups = [8,4,3,2,4], elements = [4,2]
 *
 * Output: [0,0,-1,1,0]
 *
 * Explanation:
 *
 * elements[0] = 4 is assigned to groups 0, 1, and 4.
 * elements[1] = 2 is assigned to group 3.
 * Group 2 cannot be assigned any element.
 *
 *
 * Example 2:
 *
 * Input: groups = [2,3,5,7], elements = [5,3,3]
 *
 * Output: [-1,1,0,-1]
 *
 * Explanation:
 *
 * elements[1] = 3 is assigned to group 1.
 * elements[0] = 5 is assigned to group 2.
 * Groups 0 and 3 cannot be assigned any element.
 * Example 3:
 *
 * Input: groups = [10,21,30,41], elements = [2,1]
 *
 * Output: [0,1,0,1]
 *
 * Explanation:
 *
 * elements[0] = 2 is assigned to the groups with even values, and elements[1] = 1 is assigned to the groups with odd values.
 *
 *
 *
 * Constraints:
 *
 * 1 <= groups.length <= 10^5
 * 1 <= elements.length <= 10^5
 * 1 <= groups[i] <= 10^5
 * 1 <= elements[i] <= 10^5
 */
public class AssignElementsToGroupsWithConstraints {

    // 预处理 elements[i] 倍数
    public int[] assignElements(int[] groups, int[] elements) {
        int max = 0;
        for (int size : groups) {
            max = Math.max(max, size);
        }
        int[] target = new int[max + 1];
        Arrays.fill(target, -1);

        for (int i = 0; i < elements.length; i++) {
            int e = elements[i];
            if (e > max || target[e] >= 0) {
                continue;
            }
            for (int x = e; x <= max; x += e) {
                if (target[x] < 0) {
                    target[x] = i;
                }
            }
        }
        for (int i = 0; i < groups.length; i++) {
            groups[i] = target[groups[i]];
        }
        return groups;
    }

}
