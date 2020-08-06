/**
 * 299. Bulls and Cows
 *
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
 *
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
 *
 * Please note that both secret number and friend's guess may contain duplicate digits.
 *
 * Example 1:
 *
 * Input: secret = "1807", guess = "7810"
 *
 * Output: "1A3B"
 *
 * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 *
 *
 * Example 2:
 *
 * Input: secret = "1123", guess = "0111"
 *
 * Output: "1A1B"
 *
 * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 * Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 */
public class BullsAndCows {

    public String getHint(String secret, String guess) {
        int a = 0, b = 0, len = secret.length();
        int[] buckets = new int[10];
        char[] arr1 = secret.toCharArray();
        char[] arr2 = guess.toCharArray();
        for (char c : arr1) {
            buckets[c - '0']++;
        }
        for (int i = 0; i < len; i++) {
            if (arr1[i] == arr2[i]) {
                buckets[arr1[i] - '0']--;
                a++;
            }
        }
        for (int i = 0; i < len; i++) {
            if (arr1[i] != arr2[i] && buckets[arr2[i] - '0'] > 0) {
                buckets[arr2[i] - '0']--;
                b++;
            }
        }
        return a + "A" + b + "B";
    }

    public String getHint2(String secret, String guess) {
        int a = 0, b = 0;
        int[] arr1 = new int[10];
        int[] arr2 = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g) {
                a++;
            } else {
                arr1[s]++;
                arr2[g]++;
            }
        }
        for (int i = 0; i < 10; i++) {
            b += Math.min(arr1[i], arr2[i]);
        }
        return a + "A" + b + "B";
    }

}
