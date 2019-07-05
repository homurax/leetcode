/**
 * 933. Number of Recent Calls
 *
 * Write a class RecentCounter to count recent requests.
 *
 * It has only one method: ping(int t), where t represents some time in milliseconds.
 *
 * Return the number of pings that have been made from 3000 milliseconds ago until now.
 *
 * Any ping with time in [t - 3000, t] will count, including the current ping.
 *
 * It is guaranteed that every call to ping uses a strictly larger value of t than before.
 *
 *
 *
 * Example 1:
 *
 * Input: inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
 * Output: [null,1,2,3,3]
 *
 *
 * Note:
 *
 * Each test case will have at most 10000 calls to ping.
 * Each test case will call ping with strictly increasing values of t.
 * Each call to ping will have 1 <= t <= 10^9.
 */
public class NumberOfRecentCalls {

    /**
     * 这题目的描述和举例都有点难以理解
     *
     * 题目要求可以理解为RecentCounter中需要存在一种数据结构 其中储存了符合条件的ping
     * ping方法返回的是其中符合条件的ping的数量 即数据结构的size
     */
    class RecentCounter {

        LinkedList<Integer> linkedList;

        public RecentCounter() {
            linkedList = new LinkedList<>();
        }

        public int ping(int t) {

            linkedList.add(t);
            while (linkedList.peek() < t - 3000)
                linkedList.poll();
            return linkedList.size();
        }
    }

}
