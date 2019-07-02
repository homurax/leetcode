/**
 * 1104. Path In Zigzag Labelled Binary Tree
 *
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 *
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
 *
 *                      1
 *              3               2
 *          4       5       6       7
 *
 *      15   14  13   12  11  10  9   8
 *
 * Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.
 *
 *
 * Example 1:
 *
 * Input: label = 14
 * Output: [1,3,4,14]
 *
 * Example 2:
 *
 * Input: label = 26
 * Output: [1,2,6,10,26]
 *
 *
 * Constraints:
 *
 * 1 <= label <= 10^6
 */
public class PathInZigzagLabelledBinaryTree {


    public List<Integer> pathInZigZagTree(int label) {

        LinkedList<Integer> ans = new LinkedList<>();
        int parent = label;
        ans.push(parent);

        while (parent != 1) {
            int d = (int) (Math.log(parent) / Math.log(2));
            int offset = (int) Math.pow(2, d + 1) - 1 - parent;
            parent = ((int) Math.pow(2, d) + offset) / 2;
            ans.push(parent);
        }
        return ans;
    }


    public List<Integer> pathInZigZagTree2(int label) {

        int depth = 0, temp = label;
        while ((temp >> 1) > 0) {
            temp >>= 1;
            depth++;
        }

        int[] travel = new int[depth + 1];
        travel[depth] = label;
        for (int i = depth - 1; i >= 0; i--) {
            travel[i] = (1 << i) + (1 << (i + 1)) - label / 2 - 1;
            label = travel[i];
        }

        List<Integer> ans = new ArrayList<>(depth + 1);
        for (int i : travel) {
            ans.add(i);
        }
        return ans;
    }

}
