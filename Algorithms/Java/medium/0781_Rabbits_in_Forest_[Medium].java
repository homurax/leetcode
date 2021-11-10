/**
 * 781. Rabbits in Forest
 *
 *
 * There is a forest with an unknown number of rabbits. We asked n rabbits "How many rabbits have the same color as you?" and collected the answers in an integer array answers where answers[i] is the answer of the ith rabbit.
 *
 * Given the array answers, return the minimum number of rabbits that could be in the forest.
 *
 *
 *
 * Example 1:
 *
 * Input: answers = [1,1,2]
 * Output: 5
 * Explanation:
 * The two rabbits that answered "1" could both be the same color, say red.
 * The rabbit that answered "2" can't be red or the answers would be inconsistent.
 * Say the rabbit that answered "2" was blue.
 * Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
 * The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
 *
 * Example 2:
 *
 * Input: answers = [10,10,10]
 * Output: 11
 *
 *
 * Constraints:
 *
 * 1. 1 <= answers.length <= 1000
 * 2. 0 <= answers[i] < 1000
 */
public class RabbitsInForest {

    public int numRabbits1(int[] answers) {
        if (answers.length == 0) {
            return 0;
        }
        int[] buckets = new int[1000];
        for (int answer : answers) {
            buckets[answer]++;
        }
        int ans = 0;
        for (int answer = 0; answer < 1000; answer++) {
            int count = buckets[answer];
            // 回答为 answer 的颜色最多有 answer + 1 个
            if (count > 0) {
                // answer 相同的可以为不同颜色，按照最小数考虑，当作是一个颜色的
                if (count <= answer + 1) {
                    ans += answer + 1;
                } else {
                    // 如果 count 超过，则必然属于不同颜色
                    // int group = count / (answer + 1) + ((count % (answer + 1)) > 0 ? 1 : 0);
                    // int group = (count + answer) / (answer + 1);
                    ans += ((count + answer) / (answer + 1)) * (answer + 1);
                }
            }
        }
        return ans;
    }

    // 如果有 count 只兔子都回答 answer ，则至少有 count/(answer + 1) 种颜色
    public int numRabbits2(int[] answers) {
        if (answers.length == 0) {
            return 0;
        }
        int[] buckets = new int[1000];
        for (int answer : answers) {
            buckets[answer]++;
        }
        int ans = 0;
        for (int answer = 0; answer < 1000; answer++) {
            int count = buckets[answer];
            if (count > 0) {
                ans += (count + answer) / (answer + 1) * (answer + 1);
            }
        }
        return ans;
    }

    public int numRabbits(int[] answers) {
        int[] buckets = new int[1000];
        int ans = 0;
        for (int answer : answers) {
            if (buckets[answer]++ % (answer + 1) == 0) {
                ans += answer + 1;
            }
        }
        return ans;
    }
}
