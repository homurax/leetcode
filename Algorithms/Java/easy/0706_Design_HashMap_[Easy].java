/**
 * 706. Design HashMap
 *
 * Design a HashMap without using any built-in hash table libraries.
 *
 * To be specific, your design should include these functions:
 *
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 * Example:
 *
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);
 * hashMap.put(2, 2);
 * hashMap.get(1);            // returns 1
 * hashMap.get(3);            // returns -1 (not found)
 * hashMap.put(2, 1);          // update the existing value
 * hashMap.get(2);            // returns 1
 * hashMap.remove(2);          // remove the mapping for 2
 * hashMap.get(2);            // returns -1 (not found)
 *
 * Note:
 *
 * All keys and values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashMap library.
 */
public class DesignHashMap {

    /**
     * 随便写的话性能果然很差
     */
    static class MyHashMap {

        List<Integer> dataList;

        /** Initialize your data structure here. */
        public MyHashMap() {
            dataList = new ArrayList<>(1000000);
            for (int i = 0; i < 1000000; i++) {
                dataList.add(i, null);
            }
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            dataList.set(key, value);
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            Integer value = dataList.get(key);
            return value == null ? -1 : value;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            dataList.set(key, null);
        }
    }

    static class MyHashMap2 {

        private static class Node {
            int key;
            int val;
            Node next;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }


        Node[] nodes = new Node[10000];

        private int hash(int key) {
            return key % nodes.length;
        }

        private Node find(int index, int key) {

            if (nodes[index] == null) {
                return nodes[index] = new Node(-1, -1);
            }
            Node prev = nodes[index];
            while(prev.next != null && prev.next.key != key) {
                prev = prev.next;
            }
            return prev;
        }

        public void put(int key, int value) {

            Node prev = find(hash(key), key);

            if (prev.next == null) {
                prev.next = new Node(key, value);
            } else {
                prev.next.val = value;
            }
        }

        public int get(int key) {

            Node prev = find(hash(key), key);
            return prev.next == null ? -1 : prev.next.val;
        }

        public void remove(int key) {

            Node prev = find(hash(key), key);
            if(prev.next != null) {
                prev.next = prev.next.next;
            }
        }
    }

}
