/**
 * 692. Top K Frequent Words
 *
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * Follow up:
 * Try to solve it in O(n log k) time and O(n) extra space.
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.merge(word, 1, Integer::sum);
        }
        // (w1, w2) -> count.get(w1).equals(count.get(w2)) ? w2.compareTo(w1) : count.get(w1) - count.get(w2)
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(k + 1,
                Comparator.comparingInt(map::get).thenComparing(Comparator.comparing(Object::toString).reversed()));
        for (String word : map.keySet()) {
            priorityQueue.add(word);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        List<String> ans = new ArrayList<>(k);
        while (!priorityQueue.isEmpty()) {
            ans.add(priorityQueue.poll());
        }
        Collections.reverse(ans);
        return ans;
    }

    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.merge(word, 1, Integer::sum);
        }
        List<String> candidates = new ArrayList<>(count.keySet());
        candidates.sort((w1, w2) -> count.get(w1).equals(count.get(w2)) ? w1.compareTo(w2) : count.get(w2) - count.get(w1));
        return candidates.subList(0, k);
    }

}
