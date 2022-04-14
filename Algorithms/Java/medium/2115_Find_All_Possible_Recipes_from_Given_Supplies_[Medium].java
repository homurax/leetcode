/**
 * 2115. Find All Possible Recipes from Given Supplies
 *
 *
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 *
 * Note that two recipes may contain each other in their ingredients.
 *
 *
 *
 * Example 1:
 *
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 *
 * Example 2:
 *
 * Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 *
 * Example 3:
 *
 * Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 *
 *
 * Constraints:
 *
 * 1. n == recipes.length == ingredients.length
 * 2. 1 <= n <= 100
 * 3. 1 <= ingredients[i].length, supplies.length <= 100
 * 4. 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 * 5. recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 * 6. All the values of recipes and supplies combined are unique.
 * 7. Each ingredients[i] does not contain any duplicate values.
 */
public class FindAllPossibleRecipesFromGivenSupplies {

    // BFS 进行拓扑排序
    // 入度为 0 即为可以制作
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> depend = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            List<String> ingredient = ingredients.get(i);
            for (String in : ingredient) {
                depend.computeIfAbsent(in, key -> new ArrayList<>()).add(recipes[i]);
            }
            count.put(recipes[i], ingredient.size());
        }
        List<String> ans = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Collections.addAll(queue, supplies);
        while (!queue.isEmpty()) {
            String supply = queue.poll();
            if (!depend.containsKey(supply)) {
                continue;
            }
            for (String recipe : depend.get(supply)) {
                if (count.merge(recipe, -1, Integer::sum) == 0) {
                    ans.add(recipe);
                    queue.add(recipe);
                }
            }
        }
        return ans;
    }


    public List<String> findAllRecipes1(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Collections.addAll(set, supplies);
        boolean[] visited = new boolean[recipes.length];
        boolean find = true;
        while (find) {
            find = false;
            for (int i = 0; i < recipes.length; i++) {
                if (visited[i]) {
                    continue;
                }
                if (create(ingredients.get(i), set)) {
                    set.add(recipes[i]);
                    ans.add(recipes[i]);
                    visited[i] = true;
                    find = true;
                }
            }
        }
        return ans;
    }

    private boolean create(List<String> ingredients, Set<String> set) {
        for (String ingredient : ingredients) {
            if (!set.contains(ingredient)) {
                return false;
            }
        }
        return true;
    }

}
