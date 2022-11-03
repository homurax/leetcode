/**
 * 2353. Design a Food Rating System
 *
 *
 * Design a food rating system that can do the following:
 *
 * Modify the rating of a food item listed in the system.
 * Return the highest-rated food item for a type of cuisine in the system.
 * Implement the FoodRatings class:
 *
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by foods, cuisines and ratings, all of which have a length of n.
 * foods[i] is the name of the ith food,
 * cuisines[i] is the type of cuisine of the ith food, and
 * ratings[i] is the initial rating of the ith food.
 * void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
 * String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
 * [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
 * Output
 * [null, "kimchi", "ramen", null, "sushi", null, "ramen"]
 *
 * Explanation
 * FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
 * foodRatings.highestRated("korean"); // return "kimchi"
 *                                     // "kimchi" is the highest rated korean food with a rating of 9.
 * foodRatings.highestRated("japanese"); // return "ramen"
 *                                       // "ramen" is the highest rated japanese food with a rating of 14.
 * foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
 * foodRatings.highestRated("japanese"); // return "sushi"
 *                                       // "sushi" is the highest rated japanese food with a rating of 16.
 * foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
 * foodRatings.highestRated("japanese"); // return "ramen"
 *                                       // Both "sushi" and "ramen" have a rating of 16.
 *                                       // However, "ramen" is lexicographically smaller than "sushi".
 *
 *
 * Constraints:
 *
 * 1. 1 <= n <= 2 * 10^4
 * 2. n == foods.length == cuisines.length == ratings.length
 * 3. 1 <= foods[i].length, cuisines[i].length <= 10
 * 4. foods[i], cuisines[i] consist of lowercase English letters.
 * 5. 1 <= ratings[i] <= 10^8
 * 6. All the strings in foods are distinct.
 * 7. food will be the name of a food item in the system across all calls to changeRating.
 * 8. cuisine will be a type of cuisine of at least one food item in the system across all calls to highestRated.
 * 9. At most 2 * 10^4 calls in total will be made to changeRating and highestRated.
 */
public class DesignAFoodRatingSystem {


    // TLE
    class FoodRatings {

        // food -> <rating, cuisine>
        Map<String, Pair<Integer, String>> foodMap = new HashMap<>();
        // cuisine ->  cuisine -> <rating, food>
        Map<String, TreeSet<Pair<Integer, String>>> cuisineMap = new HashMap<>();

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            for (int i = 0; i < foods.length; i++) {
                String food = foods[i];
                String cuisine = cuisines[i];
                int rating = ratings[i];
                foodMap.put(food, new Pair<>(rating, cuisine));
                cuisineMap.computeIfAbsent(cuisine, k -> new TreeSet<>((a, b) -> !Objects.equals(a.getKey(), b.getKey()) ? b.getKey() - a.getKey() : a.getValue().compareTo(b.getValue()))).add(new Pair<>(rating, food));
            }
        }

        public void changeRating(String food, int newRating) {
            Pair<Integer, String> p = foodMap.get(food);
            Integer rating = p.getKey();
            String cuisine = p.getValue();
            TreeSet<Pair<Integer, String>> treeSet = cuisineMap.get(cuisine);
            treeSet.remove(new Pair<>(rating, food));
            treeSet.add(new Pair<>(newRating, food));
            foodMap.put(food, new Pair<>(newRating, cuisine));
        }

        public String highestRated(String cuisine) {
            return cuisineMap.get(cuisine).first().getValue();
        }
    }


}
