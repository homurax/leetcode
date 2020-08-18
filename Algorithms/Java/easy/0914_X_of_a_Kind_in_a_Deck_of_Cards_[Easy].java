/**
 * 914. X of a Kind in a Deck of Cards
 *
 *
 * In a deck of cards, each card has an integer written on it.
 *
 * Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:
 *
 * Each group has exactly X cards.
 * All the cards in each group have the same integer.
 *
 *
 * Example 1:
 *
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 * Example 2:
 *
 * Input: deck = [1,1,1,2,2,2,3,3]
 * Output: false´
 * Explanation: No possible partition.
 * Example 3:
 *
 * Input: deck = [1]
 * Output: false
 * Explanation: No possible partition.
 * Example 4:
 *
 * Input: deck = [1,1]
 * Output: true
 * Explanation: Possible partition [1,1].
 * Example 5:
 *
 * Input: deck = [1,1,2,2,2,2]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[2,2].
 *
 *
 * Constraints:
 *
 * 1 <= deck.length <= 10^4
 * 0 <= deck[i] < 10^4
 */
public class XOfAKindInADeckOfCards {

    public boolean hasGroupsSizeX(int[] deck) {
        int[] count = new int[10000];
        for (int c : deck) {
            count[c]++;
        }
        int X = -1;
        for (int i = 0; i < 10000; ++i)
            if (count[i] > 0) {
                if (X == -1)
                    X = count[i];
                else
                    X = gcd(X, count[i]);
            }
        return X >= 2;
    }

    public int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }

}
