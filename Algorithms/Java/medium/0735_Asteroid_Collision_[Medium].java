/**
 * 735. Asteroid Collision
 *
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 *
 *
 * Example 1:
 *
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * Example 2:
 *
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Example 3:
 *
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 * Example 4:
 *
 * Input: asteroids = [-2,-1,1,2]
 * Output: [-2,-1,1,2]
 * Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right. Asteroids moving the same direction never meet, so no asteroids will meet each other.
 *
 *
 * Constraints:
 *
 * 1. 2 <= asteroids.length <= 10^4
 * 2. -1000 <= asteroids[i] <= 1000
 * 3. asteroids[i] != 0
 */
public class AsteroidCollision {


    // 正数 -> <- 负数
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int curr : asteroids) {
            collision:
            {
                while (!stack.isEmpty() && curr < 0 && stack.peek() > 0) {
                    if (stack.peek() < -curr) {
                        stack.pop();
                        continue;
                    } else if (stack.peek() == -curr) {
                        stack.pop();
                    }
                    break collision;
                }
                stack.push(curr);
            }
        }
        int[] ans = new int[stack.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    public int[] asteroidCollision2(int[] asteroids) {
        int[] stack = new int[asteroids.length];
        int top = -1;
        for (int curr : asteroids) {
            collision:
            {
                while (top >= 0 && curr < 0 && stack[top] > 0) {
                    if (stack[top] < -curr) {
                        top--;
                        continue;
                    } else if (stack[top] == -curr) {
                        top--;
                    }
                    break collision;
                }
                stack[++top] = curr;
            }
        }
        int[] ans = new int[top + 1];
        System.arraycopy(stack, 0, ans, 0, top + 1);
        return ans;
    }

}
