/**
 * 506. Relative Ranks
 *
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
 *
 * Example 1:
 * Input: [5, 4, 3, 2, 1]
 * Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal".
 * For the left two athletes, you just need to output their relative ranks according to their scores.
 * Note:
 * N is a positive integer and won't exceed 10,000.
 * All the scores of athletes are guaranteed to be unique.
 */
public class RelativeRanks {

    private static String[] scores = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};

    public String[] findRelativeRanks(int[] nums) {

        int length = nums.length;

        List<Integer> list = new ArrayList<>(length);
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list, Comparator.reverseOrder());

        String[] ans = new String[length];
        for (int i = 0; i < length; i++) {
            int index = list.indexOf(nums[i]);
            ans[i] = index < 3 ? scores[index] : Integer.toString(index + 1);
        }

        return ans;
    }

    public String[] findRelativeRanks2(int[] nums) {

        int length = nums.length;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num >= max) max = num;
        }
        int[] temp = new int[max + 1];

        for (int i = 0; i < length; i++) {
            int num = nums[i];
            temp[num] = i + 1; // 需要通过大于0判断对应位置是否有值
        }

        int rank = 0;
        String[] ans = new String[length];
        for (int i = max; i >= 0; i--) {
            int index = temp[i];
            if (index > 0) {
                switch (++rank) {
                    case 1:
                        ans[index - 1] = "Gold Medal";
                        break;
                    case 2:
                        ans[index - 1] = "Silver Medal";
                        break;
                    case 3:
                        ans[index - 1] = "Bronze Medal";
                        break;
                    default:
                        ans[index - 1] = Integer.toString(rank);
                        break;
                }
            }
        }

        return ans;
    }

}
