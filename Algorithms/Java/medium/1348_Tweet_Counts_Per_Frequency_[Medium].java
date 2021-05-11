/**
 * 1348. Tweet Counts Per Frequency
 *
 *
 * A social media company is trying to monitor activity on their site by analyzing the number of tweets that occur in select periods of time. These periods can be partitioned into smaller time chunks based on a certain frequency (every minute, hour, or day).
 *
 * For example, the period [10, 10000] (in seconds) would be partitioned into the following time chunks with these frequencies:
 *
 * Every minute (60-second chunks): [10,69], [70,129], [130,189], ..., [9970,10000]
 * Every hour (3600-second chunks): [10,3609], [3610,7209], [7210,10000]
 * Every day (86400-second chunks): [10,10000]
 * Notice that the last chunk may be shorter than the specified frequency's chunk size and will always end with the end time of the period (10000 in the above example).
 *
 * Design and implement an API to help the company with their analysis.
 *
 * Implement the TweetCounts class:
 *
 * TweetCounts() Initializes the TweetCounts object.
 * void recordTweet(String tweetName, int time) Stores the tweetName at the recorded time (in seconds).
 * List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) Returns a list of integers representing the number of tweets with tweetName in each time chunk for the given period of time [startTime, endTime] (in seconds) and frequency freq.
 * freq is one of "minute", "hour", or "day" representing a frequency of every minute, hour, or day respectively.
 *
 *
 * Example:
 *
 * Input
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 *
 * Output
 * [null,null,null,null,[2],[2,1],null,[4]]
 *
 * Explanation
 * TweetCounts tweetCounts = new TweetCounts();
 * tweetCounts.recordTweet("tweet3", 0);                              // New tweet "tweet3" at time 0
 * tweetCounts.recordTweet("tweet3", 60);                             // New tweet "tweet3" at time 60
 * tweetCounts.recordTweet("tweet3", 10);                             // New tweet "tweet3" at time 10
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]; chunk [0,59] had 2 tweets
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2,1]; chunk [0,59] had 2 tweets, chunk [60,60] had 1 tweet
 * tweetCounts.recordTweet("tweet3", 120);                            // New tweet "tweet3" at time 120
 * tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]; chunk [0,210] had 4 tweets
 *
 *
 * Constraints:
 *
 * 1. 0 <= time, startTime, endTime <= 10^9
 * 2. 0 <= endTime - startTime <= 10^4
 * 3. There will be at most 10^4 calls in total to recordTweet and getTweetCountsPerFrequency.
 */
public class TweetCountsPerFrequency {


    /*class TweetCounts {

        private Map<String, TreeSet<Integer>> userMap;

        public TweetCounts() {
            this.userMap = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {
            userMap.computeIfAbsent(tweetName, k -> new TreeSet<>()).add(time);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            List<Integer> result = new ArrayList<>();
            TreeSet<Integer> set = userMap.get(tweetName);
            SortedSet<Integer> sortedSet = set.subSet(startTime, endTime + 1);

            int period = 60;
            if ("hour".equals(freq)) {
                period = period * 60;
            } else if ("day".equals(freq)) {
                period = period * 60 * 24;
            }

            int count = 0;
            for (int tweetTime : sortedSet) {
                int pos = (tweetTime - startTime) / period;
                // 该累加下个区间了，记录当前区间统计结果，count 置 0
                while (result.size() < pos) {
                    result.add(count);
                    count = 0;
                }
                count += 1;
            }
            result.add(count);

            int pos = (endTime - startTime) / period;
            while (result.size() < pos + 1) {
                result.add(0);
            }
            return result;
        }
    }*/

    class TweetCounts {

        private Map<String, List<Integer>> map;

        public TweetCounts() {
            this.map = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {
            List<Integer> times = map.computeIfAbsent(tweetName, k -> new ArrayList<>());
            int index = Collections.binarySearch(times, time);
            if (index < 0) {
                index = -index - 1;
            }
            times.add(index, time);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            int period = 60;
            if (freq.equals("hour")) {
                period = period * 60;
            } else if (freq.equals("day")) {
                period = period * 60 * 12;
            }

            List<Integer> result = new ArrayList<>();
            int start = startTime;
            while (start <= endTime) {
                result.add(0);
                start += period;
            }
            if (!map.containsKey(tweetName)) {
                return result;
            }

            List<Integer> times = map.get(tweetName);
            int lowIndex = Collections.binarySearch(times, startTime);
            lowIndex = lowIndex < 0 ? -(lowIndex + 1) : lowIndex;

            int highIndex = Collections.binarySearch(times, endTime);
            highIndex = highIndex < 0 ? -(highIndex + 1) : highIndex;

            for (int i = lowIndex; i <= highIndex; i++) {
                if (i >= times.size() || times.get(i) < startTime || times.get(i) > endTime) {
                    continue;
                }
                int index = (times.get(i) - startTime) / period;
                result.set(index, result.get(index) + 1);
            }
            return result;
        }
    }

}
