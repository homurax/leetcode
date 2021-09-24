/**
 * 1734. Decode XORed Permutation
 *
 *
 * There is an integer array perm that is a permutation of the first n positive integers, where n is always odd.
 *
 * It was encoded into another integer array encoded of length n - 1, such that encoded[i] = perm[i] XOR perm[i + 1]. For example, if perm = [1,3,2], then encoded = [2,1].
 *
 * Given the encoded array, return the original array perm. It is guaranteed that the answer exists and is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: encoded = [3,1]
 * Output: [1,2,3]
 * Explanation: If perm = [1,2,3], then encoded = [1 XOR 2,2 XOR 3] = [3,1]
 *
 * Example 2:
 *
 * Input: encoded = [6,5,4,6]
 * Output: [2,4,1,5,3]
 *
 *
 * Constraints:
 *
 * 1. 3 <= n < 10 ^ 5
 * 2. n is odd.
 * 3. encoded.length == n - 1
 */
public class DecodeXORedPermutation {

    /**
     * perm[0] = xor(encoded[i]), (i is odd)
     * perm[i + 1] = encoded[i] ^ perm[i];
     */
    public int[] decode(int[] encoded) {
        int xor = 0;
        for (int i = 1; i <= encoded.length + 1; i++) {
            xor ^= i;
        }
        for (int i = 1; i < encoded.length; i += 2) {
            xor ^= encoded[i];
        }
        int[] perm = new int[encoded.length + 1];
        perm[0] = xor;
        for (int i = 0; i < encoded.length; i++) {
            perm[i + 1] = encoded[i] ^ perm[i];
        }
        return perm;
    }

}
