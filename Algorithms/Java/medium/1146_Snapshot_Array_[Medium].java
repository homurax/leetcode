/**
 * 1146. Snapshot Array
 *
 *
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 *
 * Example 1:
 *
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 *
 *
 * Constraints:
 *
 * 1. 1 <= length <= 50000
 * 2. At most 50000 calls will be made to set, snap, and get.
 * 3. 0 <= index < length
 * 4. 0 <= snap_id < (the total number of times we call snap())
 * 5. 0 <= val <= 10^9
 */
public class SnapshotArray {

    private final int[] data;
    private int snapId;
    private final List<Map<Integer, Integer>> snaps;

    public SnapshotArray(int length) {
        this.data = new int[length];
        this.snapId = -1;
        this.snaps = new ArrayList<>();
    }

    public void set(int index, int val) {
        int snapVal = data[index];
        data[index] = val;
        if (snapId >= 0) {
            // snapId -> index <-> snap_val
            Map<Integer, Integer> snap = snaps.get(snaps.size() - 1);
            if (snap == null) {
                snap = new HashMap<>();
                snaps.set(snaps.size() - 1, snap);
            }
            if (!snap.containsKey(index)) {
                snap.put(index, snapVal);
            }
        }
    }

    public int snap() {
        snaps.add(null);
        return ++snapId;
    }

    public int get(int index, int snap_id) {
        for (int i = snap_id; i < snaps.size(); i++) {
            Map<Integer, Integer> snap = snaps.get(i);
            if (snap != null && snap.containsKey(index)) {
                return snap.get(index);
            }
        }
        return data[index];
    }

}
