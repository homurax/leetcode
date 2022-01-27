/**
 * 2126. Destroying Asteroids
 *
 *
 * You are given an integer mass, which represents the original mass of a planet. You are further given an integer array asteroids, where asteroids[i] is the mass of the ith asteroid.
 *
 * You can arrange for the planet to collide with the asteroids in any arbitrary order. If the mass of the planet is greater than or equal to the mass of the asteroid, the asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the planet is destroyed.
 *
 * Return true if all asteroids can be destroyed. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: mass = 10, asteroids = [3,9,19,5,21]
 * Output: true
 * Explanation: One way to order the asteroids is [9,19,5,3,21]:
 * - The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
 * - The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
 * - The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
 * - The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
 * - The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
 * All asteroids are destroyed.
 *
 * Example 2:
 *
 * Input: mass = 5, asteroids = [4,9,23,4]
 * Output: false
 * Explanation:
 * The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
 * After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
 * This is less than 23, so a collision would not destroy the last asteroid.
 *
 *
 * Constraints:
 *
 * 1. 1 <= mass <= 10^5
 * 2. 1 <= asteroids.length <= 10^5
 * 3. 1 <= asteroids[i] <= 10^5
 */
public class DestroyingAsteroids {


    // 如果大于 100000 可以直接返回
    // 将暂时无法摧毁的小行星重写到数组开头
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        long planet = mass;
        int len = asteroids.length;
        int min;
        do {
            min = Integer.MAX_VALUE;
            int pos = 0;
            for (int i = 0; i < len; i++) {
                int asteroid = asteroids[i];
                if (planet >= asteroid) {
                    planet += asteroid;
                    if (planet >= 100000) {
                        return true;
                    }
                } else {
                    asteroids[pos++] = asteroid;
                    min = Math.min(min, asteroid);
                }
            }
            len = pos;
        } while (len > 0 && planet >= min);
        return len == 0;
    }


    public boolean asteroidsDestroyed1(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long temp = mass;
        for (int asteroid : asteroids) {
            if (temp < asteroid) {
                return false;
            }
            temp += asteroid;
        }
        return true;
    }

}
