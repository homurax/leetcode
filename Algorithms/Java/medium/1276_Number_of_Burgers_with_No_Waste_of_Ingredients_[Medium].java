/**
 * 1276. Number of Burgers with No Waste of Ingredients
 *
 *
 * Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
 *
 * Jumbo Burger: 4 tomato slices and 1 cheese slice.
 * Small Burger: 2 Tomato slices and 1 cheese slice.
 * Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the number of remaining cheeseSlices equal to 0. If it is not possible to make the remaining tomatoSlices and cheeseSlices equal to 0 return [].
 *
 *
 *
 * Example 1:
 *
 * Input: tomatoSlices = 16, cheeseSlices = 7
 * Output: [1,6]
 * Explantion: To make one jumbo burger and 6 small burgers we need 4*1 + 2*6 = 16 tomato and 1 + 6 = 7 cheese.
 * There will be no remaining ingredients.
 *
 * Example 2:
 *
 * Input: tomatoSlices = 17, cheeseSlices = 4
 * Output: []
 * Explantion: There will be no way to use all ingredients to make small and jumbo burgers.
 *
 * Example 3:
 *
 * Input: tomatoSlices = 4, cheeseSlices = 17
 * Output: []
 * Explantion: Making 1 jumbo burger there will be 16 cheese remaining and making 2 small burgers there will be 15 cheese remaining.
 *
 *
 * Constraints:
 *
 * 1. 0 <= tomatoSlices, cheeseSlices <= 10^7
 */
public class NumberOfBurgersWithNoWasteOfIngredients {

    /**
     * 4x + 2y = tomatoSlices           x = (tomatoSlices - 2 * cheeseSlices) / 2
     * x + y = cheeseSlices      =>     y = (4 * cheeseSlices - tomatoSlices) / 2
     *
     * x >= 0       2 * cheeseSlices <= tomatoSlices
     * y >= 0   =>  tomatoSlices <= 4 * cheeseSlices
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices % 2 != 0 || tomatoSlices < cheeseSlices * 2 || cheeseSlices * 4 < tomatoSlices) {
            return Collections.emptyList();
        }
        return Arrays.asList(tomatoSlices / 2 - cheeseSlices, cheeseSlices * 2 - tomatoSlices / 2);
    }


    /**
     * 4x + 2y = tomatoSlices           x = (tomatoSlices - 2*cheeseSlices) / 2
     * x + y = cheeseSlices      =>     y = cheeseSlices - x
     *
     * x <= cheeseSlices => tomatoSlices <= 4 * cheeseSlices
     */
    public List<Integer> numOfBurgers1(int tomatoSlices, int cheeseSlices) {
        if (tomatoSlices <= 4 * cheeseSlices) {
            int temp = tomatoSlices - 2 * cheeseSlices;
            if (temp >= 0 && (temp & 1) == 0) {
                int jumbo = temp >> 1;
                return Arrays.asList(jumbo, cheeseSlices - jumbo);
            }
        }
        return Collections.emptyList();
    }

}
