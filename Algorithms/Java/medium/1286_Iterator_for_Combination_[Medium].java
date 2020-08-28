/**
 * 1286. Iterator for Combination
 *
 * Design an Iterator class, which has:
 *
 * A constructor that takes a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * A function next() that returns the next combination of length combinationLength in lexicographical order.
 * A function hasNext() that returns True if and only if there exists a next combination.
 *
 *
 * Example:
 *
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.
 *
 * iterator.next(); // returns "ab"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "ac"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "bc"
 * iterator.hasNext(); // returns false
 *
 *
 * Constraints:
 *
 * 1 <= combinationLength <= characters.length <= 15
 * There will be at most 10^4 function calls per test.
 * It's guaranteed that all calls of the function next are valid.
 */
public class IteratorForCombination {

    class CombinationIterator {

        private char[] chars;
        private int[] idxArr;

        public CombinationIterator(String characters, int combinationLength) {
            this.chars = characters.toCharArray();
            this.idxArr = new int[combinationLength];
            for (int i = 0; i < combinationLength; i++) {
                this.idxArr[i] = i;
            }
        }

        public String next() {
            char[] rst = new char[idxArr.length];
            for (int i = 0; i < idxArr.length; i++) {
                rst[i] = chars[idxArr[i]];
            }
            increase(idxArr.length - 1, false);
            return new String(rst);
        }

        public boolean hasNext() {
            return idxArr[0] != -1;
        }

        private void increase(int currIndex, boolean reset) {
            if (chars.length - idxArr.length == idxArr[0]) {
                idxArr[0] = -1;
                return;
            }
            if (idxArr[currIndex] < chars.length - 1 - (idxArr.length - 1 - currIndex)) {
                idxArr[currIndex]++;
                if (reset) {
                    for (int idx = currIndex + 1; idx < idxArr.length; idx++) {
                        idxArr[idx] = idxArr[currIndex] + idx - currIndex;
                    }
                }
                return;
            }
            increase(currIndex - 1, true);
        }
    }

}
