/**
 * 748. Shortest Completing Word
 *
 * Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate
 *
 * Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.
 *
 * It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.
 *
 * The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.
 *
 * Example 1:
 * Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
 * Output: "steps"
 * Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
 * Note that the answer is not "step", because the letter "s" must occur in the word twice.
 * Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
 *
 * Example 2:
 * Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
 * Output: "pest"
 * Explanation: There are 3 smallest length words that contains the letters "s".
 * We return the one that occurred first.
 *
 * Note:
 * licensePlate will be a string with length in range [1, 7].
 * licensePlate will contain digits, spaces, or letters (uppercase or lowercase).
 * words will have a length in the range [10, 1000].
 * Every words[i] will consist of lowercase letters, and have length in range [1, 15].
 */
public class ShortestCompletingWord {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] target = count(licensePlate);
        String ans = "";
        for (String word : words) {
            if ((ans.length() == 0 || word.length() < ans.length()) && dominates(count(word.toLowerCase()), target)) {
                ans = word;
            }
        }
        return ans;
    }

    public boolean dominates(int[] count, int[] target) {
        for (int i = 0; i < 26; i++) {
            if (count[i] < target[i])
                return false;
        }
        return true;
    }

    public int[] count(String word) {
        int[] arr = new int[26];
        for (char letter : word.toCharArray()) {
            int index = Character.toLowerCase(letter) - 'a';
            if (0 <= index && index < 26)
                arr[index]++;
        }
        return arr;
    }


    // 通过质数性质来优化
    private static final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

    public String shortestCompletingWord2(String licensePlate, String[] words) {
        String ans = "aaaaaaaaaaaaaaaaaaaa";
        long charPrime = getCharPrimeSum(licensePlate.toLowerCase());
        for (String word : words) {
            if (word.length() < ans.length() && getCharPrimeSum(word) % charPrime == 0) {
                ans = word;
            }
        }
        return ans;
    }

    private long getCharPrimeSum(String word) {
        long charPrime = 1L;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (0 <= index && index < 26) {
                charPrime *= primes[index];
            }
        }
        return charPrime;
    }
}
