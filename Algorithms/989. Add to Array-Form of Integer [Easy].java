/**
 * 989. Add to Array-Form of Integer
 *
 * For a non-negative integer X, the array-form of X is an array of its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].
 * 
 * Given the array-form A of a non-negative integer X, return the array-form of the integer X+K.
 * 
 *
 * Example 1:
 * 
 * Input: A = [1,2,0,0], K = 34
 * Output: [1,2,3,4]
 * Explanation: 1200 + 34 = 1234
 * 
 * Example 2:
 * 
 * Input: A = [2,7,4], K = 181
 * Output: [4,5,5]
 * Explanation: 274 + 181 = 455
 * 
 * Example 3:
 * 
 * Input: A = [2,1,5], K = 806
 * Output: [1,0,2,1]
 * Explanation: 215 + 806 = 1021
 * 
 * Example 4:
 * 
 * Input: A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * Output: [1,0,0,0,0,0,0,0,0,0,0]
 * Explanation: 9999999999 + 1 = 10000000000
 * 
 * 
 * Note：
 * 
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * If A.length > 1, then A[0] != 0
 */
public class AddToArrayFormOfInteger {


    public List<Integer> addToArrayForm(int[] A, int K) {

        List<Integer> ans = new ArrayList<>();
        int index = A.length;

        while (--index >= 0 || K > 0) {
            if (index >= 0) K += A[index];
            ans.add(K % 10);
            K /= 10;
        }

        Collections.reverse(ans);
        return ans;
    }

    public List<Integer> addToArrayForm2(int[] A, int K) {

        int length = A.length;
        List<Integer> ans = new ArrayList<>(length + 1);

        for (int i = length - 1; i >= 0 && K > 0; i--) {
            int sum = A[i] + K;
            A[i] = sum % 10;
            K = sum / 10;
        }

        while (K > 0) {
            ans.add(0, K % 10);
            K = K / 10;
        }

        for (int i : A) {
            ans.add(i);
        }
        return ans;
    }

}
