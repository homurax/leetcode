/**
 * 3433. Count Mentions Per User
 *
 *
 * You are given an integer numberOfUsers representing the total number of users and an array events of size n x 3.
 *
 * Each events[i] can be either of the following two types:
 *
 * Message Event: ["MESSAGE", "timestampi", "mentions_stringi"]
 * This event indicates that a set of users was mentioned in a message at timestampi.
 * The mentions_stringi string can contain one of the following tokens:
 * id<number>: where <number> is an integer in range [0,numberOfUsers - 1]. There can be multiple ids separated by a single whitespace and may contain duplicates. This can mention even the offline users.
 * ALL: mentions all users.
 * HERE: mentions all online users.
 * Offline Event: ["OFFLINE", "timestampi", "idi"]
 * This event indicates that the user idi had become offline at timestampi for 60 time units. The user will automatically be online again at time timestampi + 60.
 * Return an array mentions where mentions[i] represents the number of mentions the user with id i has across all MESSAGE events.
 *
 * All users are initially online, and if a user goes offline or comes back online, their status change is processed before handling any message event that occurs at the same timestamp.
 *
 * Note that a user can be mentioned multiple times in a single message event, and each mention should be counted separately.
 *
 *
 *
 * Example 1:
 *
 * Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]
 *
 * Output: [2,2]
 *
 * Explanation:
 *
 * Initially, all users are online.
 *
 * At timestamp 10, id1 and id0 are mentioned. mentions = [1,1]
 *
 * At timestamp 11, id0 goes offline.
 *
 * At timestamp 71, id0 comes back online and "HERE" is mentioned. mentions = [2,2]
 *
 *
 *
 * Example 2:
 *
 * Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","12","ALL"]]
 *
 * Output: [2,2]
 *
 * Explanation:
 *
 * Initially, all users are online.
 *
 * At timestamp 10, id1 and id0 are mentioned. mentions = [1,1]
 *
 * At timestamp 11, id0 goes offline.
 *
 * At timestamp 12, "ALL" is mentioned. This includes offline users, so both id0 and id1 are mentioned. mentions = [2,2]
 *
 *
 *
 * Example 3:
 *
 * Input: numberOfUsers = 2, events = [["OFFLINE","10","0"],["MESSAGE","12","HERE"]]
 *
 * Output: [0,1]
 *
 * Explanation:
 *
 * Initially, all users are online.
 *
 * At timestamp 10, id0 goes offline.
 *
 * At timestamp 12, "HERE" is mentioned. Because id0 is still offline, they will not be mentioned. mentions = [0,1]
 *
 *
 *
 * Constraints:
 *
 * 1 <= numberOfUsers <= 100
 * 1 <= events.length <= 100
 * events[i].length == 3
 * events[i][0] will be one of MESSAGE or OFFLINE.
 * 1 <= int(events[i][1]) <= 10^5
 * The number of id<number> mentions in any "MESSAGE" event is between 1 and 100.
 * 0 <= <number> <= numberOfUsers - 1
 * It is guaranteed that the user id referenced in the OFFLINE event is online at the time the event occurs.
 */
public class CountMentionsPerUser {

    // 模拟
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        // 时间相同先处理 OFFLINE
        events.sort((a, b) -> {
            int ta = Integer.parseInt(a.get(1));
            int tb = Integer.parseInt(b.get(1));
            return ta != tb ? ta - tb : b.get(0).charAt(0) - a.get(0).charAt(0);
        });

        int[] ans = new int[numberOfUsers];
        int[] online = new int[numberOfUsers];
        for (List<String> e : events) {
            int currTime = Integer.parseInt(e.get(1));
            String mention = e.get(2);
            if (e.get(0).charAt(0) == 'O') { // OFFLINE
                online[Integer.parseInt(mention)] = currTime + 60;
            } else if (mention.charAt(0) == 'A') { // ALL
                for (int i = 0; i < numberOfUsers; i++) {
                    ans[i]++;
                }
            } else if (mention.charAt(0) == 'H') { // HERE
                for (int i = 0; i < numberOfUsers; i++) {
                    if (online[i] <= currTime) { // ONLINE
                        ans[i]++;
                    }
                }
            } else { // id
                for (String s : mention.split(" ")) {
                    int i = Integer.parseInt(s.substring(2));
                    ans[i]++;
                }
            }
        }
        return ans;
    }

}
