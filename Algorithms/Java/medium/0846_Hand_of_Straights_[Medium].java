/**
 * 846. Hand of Straights
 *
 *
 * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
 *
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 *
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], groupSize = 4
 * Output: false
 * Explanation: Alice's hand can not be rearranged into groups of 4.
 *
 *
 *
 * Constraints:
 *
 * 1. 1 <= hand.length <= 10^4
 * 2. 0 <= hand[i] <= 10^9
 * 3. 1 <= groupSize <= hand.length
 *
 *
 * Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */
public class HandOfStraights {

    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W > 0) {
            return false;
        }
        Arrays.sort(hand);
        int[] buckets = new int[W];
        for (int i = 0; i < hand.length; i++) {
            if (i % W != 0 && (hand[i] - hand[i - 1]) > 1) {
                return false;
            }
            buckets[hand[i] % W]++;
        }
        for (int num : buckets) {
            if (num != buckets[0]) {
                return false;
            }
        }
        return true;
    }

}
