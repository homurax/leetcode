/**
 * 365. Water and Jug Problem
 *
 *
 * You are given two jugs with capacities x liters and y liters. You have an infinite water supply. Return whether the total amount of water in both jugs may reach target using the following operations:
 *
 * Fill either jug completely with water.
 * Completely empty either jug.
 * Pour water from one jug into another until the receiving jug is full, or the transferring jug is empty.
 *
 *
 * Example 1:
 *
 * Input: x = 3, y = 5, target = 4
 *
 * Output: true
 *
 * Explanation:
 *
 * Follow these steps to reach a total of 4 liters:
 *
 * Fill the 5-liter jug (0, 5).
 * Pour from the 5-liter jug into the 3-liter jug, leaving 2 liters (3, 2).
 * Empty the 3-liter jug (0, 2).
 * Transfer the 2 liters from the 5-liter jug to the 3-liter jug (2, 0).
 * Fill the 5-liter jug again (2, 5).
 * Pour from the 5-liter jug into the 3-liter jug until the 3-liter jug is full. This leaves 4 liters in the 5-liter jug (3, 4).
 * Empty the 3-liter jug. Now, you have exactly 4 liters in the 5-liter jug (0, 4).
 * Reference: The Die Hard example.
 *
 *
 * Example 2:
 *
 * Input: x = 2, y = 6, target = 5
 *
 * Output: false
 *
 *
 * Example 3:
 *
 * Input: x = 1, y = 2, target = 3
 *
 * Output: true
 *
 * Explanation: Fill both jugs. The total amount of water in both jugs is equal to 3 now.
 *
 *
 *
 * Constraints:
 *
 * 1 <= x, y, target <= 10^3
 */
public class WaterAndJugProblem {


    // 可以理解为操作只会让水总量 +a +b -a -b, 两个水桶不可能同时有水且不满
    // 对一个不满的桶加水没有意义：如果另一个桶是空的, 等价于直接从初始状态给这个桶加满水; 如果另一个桶是满的, 等价于从初始状态给两个桶加满
    // 不满的桶里面的水倒掉没有意义：如果另一个桶是空的, 等价于回到初始状态; 如果另一个桶是满的, 则价于从初始状态直接给另一个桶倒满

    // 即需要找到整数解 x, y -> xa + yb = target
    // 裴蜀定理：对任何整数 a, b, d = gcd(a, b), 对于任意的整数 x, y, xa + yb 都一定是 d 的倍数;
    // 特别地, 一定存在整数 x, y, 使 xa + yb = d 成立

    // 只会 x >= 0 && y >= 0 或者 x, y 之一 < 0
    // 对于 x < 0 的情况(y < 0 同理), 可以理解为:
    // 1. 往 b 壶倒水
    // 2. 把 b 壶的水倒入 a 壶
    // 3. 如果 b 壶不为空，那么 a 壶肯定是满的，把 a 壶倒空，然后再把 b 壶的水倒入 a 壶
    // 重复以上操作直至某一步时 a 壶进行了 x 次倒空操作，b 壶进行了 y 次倒水操作
    public boolean canMeasureWater(int a, int b, int target) {
        if (a + b < target) {
            return false;
        }
        /*if (a == 0 || b == 0) {
            return target == 0 || a + b == target;
        }*/
        return target % gcd(a, b) == 0;
    }

    private int gcd(int a, int b) {
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

}
