/**
 * 406. Queue Reconstruction by Height
 *
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionByHeight {


    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                if (p1[0] == p2[0])
                    return Integer.compare(p1[1], p2[1]);
                return Integer.compare(p2[0], p1[0]);
            }
        });
        LinkedList<int[]> linkedList = new LinkedList<>();
        for (int[] p : people) {
            linkedList.add(p[1], p);
        }
        return linkedList.toArray(people);
    }


    /**
     * 自己写sort快点
     */
    public int[][] reconstructQueue2(int[][] people) {

        sort(people, 0, people.length - 1);
        LinkedList<int[]> linkedList = new LinkedList<>();
        for (int[] p : people) {
            linkedList.add(p[1], p);
        }
        return linkedList.toArray(people);
    }

    private void sort(int[][] people, int low, int high) {
        if (low >= high) return;
        int i = low, j = high;
        int[] mid = people[low + (high - low) / 2];
        while (i < j) {
            while (mid[0] < people[i][0] || (mid[0] == people[i][0] && mid[1] > people[i][1])) {
                i++;
            }
            while (mid[0] > people[j][0] || (mid[0] == people[j][0] && mid[1] < people[j][1])) {
                j--;
            }
            if (i <= j) {
                int[] tmp = people[i];
                people[i] = people[j];
                people[j] = tmp;
                i++;
                j--;
            }
        }
        sort(people, low, j);
        sort(people, i, high);
    }

}
