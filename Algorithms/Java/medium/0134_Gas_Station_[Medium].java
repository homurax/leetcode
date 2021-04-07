/**
 * 134. Gas Station
 *
 *
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
 *
 *
 *
 * Example 1:
 *
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 *
 * Example 2:
 *
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 *
 * Constraints:
 *
 * 1. gas.length == n
 * 2. cost.length == n
 * 3. 1 <= n <= 10^4
 * 4. 0 <= gas[i], cost[i] <= 10^4
 */
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (dfs(i, (i + 1) % gas.length, gas[i], gas, cost)) {
                return i;
            }
        }
        return -1;
    }

    private boolean dfs(int start, int next, int currGas, int[] gas, int[] cost) {
        int nextCost = cost[(next + gas.length - 1) % gas.length];
        if (currGas < nextCost) {
            return false;
        }
        if (start == next) {
            return true;
        }
        return dfs(start, (next + 1) % gas.length, currGas - nextCost + gas[next], gas, cost);
    }


    /**
     * 假设从 i 出发，最后可以到达的是 j，可以得出两个不等式
     *     无法到达 j 的下一站 -> sum(gas[i, j]) < sum(cost[i, j])
     *     可以到达 j 及 j 之前的 -> sum(gas[i, k]) >= sum(cost[i, k]), k in [i, j)
     *
     * 假设 k 为 i 和 j 之间的一站（k in [i, j]），考虑从 k 出发能否到达 j 之后的站
     *     sum(gas[k, j]) = sum(gas[i, j]) - sum(gas[i, k - 1])
     *                    < sum(cost[i, j]) - sum(gas[i, k - 1])
     *                    < sum(cost[i, j]) - sum(cost[i, k - 1])
     *                    < sum(cost[k, j])
     *
     * 即从 i 出发，最多可以到达的站为 j，则从 j + 1 （第一个无法到达的站）开始检查，不需要考虑之间的站（k in [i, j]）
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int i = 0, n = gas.length;
        while (i < n) {
            int count = 0;
            int sumGas = 0, sumCost = 0;
            while (count < n) {
                int j = (i + count) % n;
                sumGas += gas[j];
                sumCost += cost[j];
                if (sumCost > sumGas) {
                    break;
                }
                count++;
            }
            if (count == n) {
                return i;
            }
            i += count + 1;
        }
        return -1;
    }

}
