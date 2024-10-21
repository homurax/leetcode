/**
 * 355. Design Twitter
 *
 *
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 *
 * Twitter() Initializes your twitter object.
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
 * void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
 * void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
 *
 *
 * Example 1:
 *
 * Input
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * Output
 * [null, null, [5], null, null, [6, 5], null, [5]]
 *
 * Explanation
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
 * twitter.follow(1, 2);    // User 1 follows user 2.
 * twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.unfollow(1, 2);  // User 1 unfollows user 2.
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
 *
 *
 * Constraints:
 *
 * 1 <= userId, followerId, followeeId <= 500
 * 0 <= tweetId <= 10^4
 * All the tweets have unique IDs.
 * At most 3 * 10^4 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
 */
public class DesignTwitter {

    // user 和 List<tweetId> 的关系
    // user 和 List<followeeId> 的关系
    // tweetId 和 time 的关系
    // 信息流: 讲自己的 tweetId 和 followee 的 tweetId 线性合并
    class Twitter {

        private class Node {

            Set<Integer> followee;
            LinkedList<Integer> tweet;

            public Node() {
                followee = new HashSet<>();
                tweet = new LinkedList<>();
            }
        }

        private int limit, time;
        private final Map<Integer, Node> user;
        private final Map<Integer, Integer> tweetTime;

        public Twitter() {
            limit = 10;
            time = 0;
            user = new HashMap<>();
            tweetTime = new HashMap<>();
        }

        private void init(int userId) {
            user.put(userId, new Node());
        }

        public void postTweet(int userId, int tweetId) {
            if (!user.containsKey(userId)) {
                init(userId);
            }
            // 只需要保留最新的
            if (user.get(userId).tweet.size() == limit) {
                user.get(userId).tweet.remove(limit - 1);
            }
            user.get(userId).tweet.addFirst(tweetId);
            tweetTime.put(tweetId, ++time);
        }

        public List<Integer> getNewsFeed(int userId) {
            if (!user.containsKey(userId)) {
                return Collections.emptyList();
            }
            LinkedList<Integer> ans = new LinkedList<>(user.get(userId).tweet);
            for (int followeeId : user.get(userId).followee) {
                if (followeeId == userId) {
                    continue;
                }
                // 合并
                LinkedList<Integer> temp = new LinkedList<>();
                int size = user.get(followeeId).tweet.size();
                Iterator<Integer> it = user.get(followeeId).tweet.iterator();
                int i = 0, j = 0, curr = -1;
                if (j < size) {
                    curr = it.next();
                    while (i < ans.size() && j < size) {
                        if (tweetTime.get(curr) > tweetTime.get(ans.get(i))) {
                            temp.addLast(curr);
                            j++;
                            if (it.hasNext()) {
                                curr = it.next();
                            }
                        } else {
                            temp.addLast(ans.get(i));
                            i++;
                        }
                        if (temp.size() == limit) {
                            break;
                        }
                    }
                }
                // 可能剩余自己的
                for (; i < ans.size() && temp.size() < limit; i++) {
                    temp.addLast(ans.get(i));
                }
                // 可能剩余关注的
                if (j < size && temp.size() < limit) {
                    temp.addLast(curr);
                    while (it.hasNext() && temp.size() < limit) {
                        temp.addLast(it.next());
                    }
                }
                ans = new LinkedList<>(temp);
            }
            return ans;
        }

        public void follow(int followerId, int followeeId) {
            if (!user.containsKey(followerId)) {
                init(followerId);
            }
            if (!user.containsKey(followeeId)) {
                init(followeeId);
            }
            user.get(followerId).followee.add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (user.containsKey(followerId)) {
                user.get(followerId).followee.remove(followeeId);
            }
        }
    }

}
