/**
 * 470. Implement Rand10() Using Rand7()
 *
 * Given the API rand7() that generates a uniform random integer in the range [1, 7], write a function rand10() that generates a uniform random integer in the range [1, 10]. You can only call the API rand7(), and you shouldn't call any other API. Please do not use a language's built-in random API.
 *
 * Each test case will have one internal argument n, the number of times that your implemented function rand10() will be called while testing. Note that this is not an argument passed to rand10().
 *
 * Follow up:
 *
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: [2]
 *
 * Example 2:
 *
 * Input: n = 2
 * Output: [2,8]
 *
 * Example 3:
 *
 * Input: n = 3
 * Output: [3,8,10]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 */
public class ImplementRand10UsingRand7 {

    class SolBase {
        public int rand7() {
            return ThreadLocalRandom.current().nextInt(1, 8);
        }
    }

    class Solution extends SolBase {
        // 拒绝量 -> 7 * 7 % 10 = 9
        // 当被拒绝时才计算随机数 即 拒绝采样
        public int rand10() {
            int index;
            do {
                int row = rand7();
                int col = rand7();
                index = col + (row - 1) * 7;
            } while (index > 40);
            return 1 + (index - 1) % 10;
        }

    }

}
