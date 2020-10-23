/**
 * 981. Time Based Key-Value Store
 *
 *
 * Create a timebased key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 *
 * Example 1:
 *
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 *
 *
 * Example 2:
 *
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 *
 *
 * Note:
 *
 * All key/value strings are lowercase.
 * All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing.
 * 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 */
public class TimeBasedKeyValueStore {

    class TimeMap {

        private final Map<String, List<Integer>> keyMap;
        private final Map<String, List<String>> valueMap;

        public TimeMap() {
            this.keyMap =  new HashMap<>();
            this.valueMap =  new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!keyMap.containsKey(key)) {
                keyMap.put(key, new LinkedList<>());
                valueMap.put(key, new LinkedList<>());
            }
            keyMap.get(key).add(timestamp);
            valueMap.get(key).add(value);
        }

        public String get(String key, int timestamp) {
            if (!keyMap.containsKey(key)) {
                return "";
            }
            List<Integer> timestamps = keyMap.get(key);
            for (int i = timestamps.size() - 1; i >= 0; i--) {
                if (timestamps.get(i) <= timestamp) {
                    return valueMap.get(key).get(i);
                }
            }
            return "";
        }
    }



    class TimeMap2 {

        private final Map<String, List<Pair<Integer, String>>> map;

        public TimeMap2() {
            this.map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(new Pair<>(timestamp, value));
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }

            List<Pair<Integer, String>> pairList = map.get(key);

            int i = Collections.binarySearch(pairList, new Pair<>(timestamp, "{"), Comparator.comparingInt(Pair::getKey));

            if (i >= 0) {
                return pairList.get(i).getValue();
            } else if (i == -1) {
                return "";
            } else {
                // binarySearch return 的是 i == -(low + 1)
                // - i - 1 == low 为应插入位置 第一个比 timestamp 值大的数
                // 所以取 - i - 1 - 1
                return pairList.get(- i - 2).getValue();
            }
        }
    }


    class TimeMap3 {
        private final Map<String, TreeMap<Integer, String>> map;

        public TimeMap3() {
            this.map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }
            map.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }
            TreeMap<Integer, String> treeMap = map.get(key);
            // 找到小于等于给定 key
            Integer targetKey = treeMap.floorKey(timestamp);
            return targetKey != null ? treeMap.get(targetKey) : "";
        }
    }


    class TimeMap4 {

        private class TimeNode {
            int timestamp;
            String value;
            TimeNode next;

            private TimeNode(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }
        }

        private final Map<String, TimeNode> cache;

        public TimeMap4() {
            this.cache = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            TimeNode headNode = cache.get(key);
            TimeNode curNode = new TimeNode(timestamp, value);
            curNode.next = headNode;
            cache.put(key, curNode);
        }

        public String get(String key, int timestamp) {
            TimeNode node = cache.get(key);
            if (node == null) {
                return "";
            }
            while (node != null && node.timestamp > timestamp) {
                node = node.next;
            }
            return node == null ? "" : node.value;
        }
    }

}
