/**
 * 703. Kth Largest Element in a Stream
 *
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 */
public class KthLargestElementInAStream {

    class KthLargest {

        /*private final BST bst = new BST();
        private final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int num : nums) {
                bst.add(num);
            }
        }

        public int add(int val) {
            bst.add(val);
            return bst.search(k).val;
        }


        private class BST {

            private TreeNode root;

            private class TreeNode {
                private final int val;
                private int count = 1;
                private TreeNode left;
                private TreeNode right;
                TreeNode(int val) {
                    this.val = val;
                }
            }

            public void add(int val) {
                this.root = add(this.root, val);
            }

            private TreeNode add(TreeNode node, int val) {
                if (node == null)
                    return new TreeNode(val);
                if (node.val > val) {
                    node.left = add(node.left, val);
                } else if (node.val < val) {
                    node.right = add(node.right, val);
                }
                node.count++;
                return node;
            }

            public TreeNode search(int k) {
                return search(root, k);
            }

            private TreeNode search(TreeNode node, int k) {
                if (node == null)
                    return null;
                int leftCount = node.left != null ? node.left.count : 0;
                int rightCount = node.right != null ? node.right.count : 0;
                int currCount = node.count - leftCount - rightCount;
                if (k > currCount + rightCount) {
                    return search(node.left, k - currCount - rightCount);
                } else if (k <= rightCount) {
                    return search(node.right, k);
                } else {
                    return node;
                }
            }
        }*/


        private final int[] arr;
        private final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.arr = new int[k];
            Arrays.fill(this.arr, Integer.MIN_VALUE);
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (val > arr[0]) {
                arr[0] = val;
                heapify(k, 0);
            }
            return arr[0];
        }

        public void heapify(int size, int parent) {
            int leftChild = parent * 2 + 1;
            int rightChild = parent * 2 + 2;
            int next = parent;
            if (leftChild < size && arr[leftChild] <= arr[next]) {
                next = leftChild;
            }
            if (rightChild < size && arr[rightChild] <= arr[next]) {
                next = rightChild;
            }
            if (next != parent) {
                int temp = arr[next];
                arr[next] = arr[parent];
                arr[parent] = temp;
                heapify(size, next);
            }
        }

    }
}
