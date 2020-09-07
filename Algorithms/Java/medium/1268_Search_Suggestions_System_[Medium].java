/**
 * 1268. Search Suggestions System
 *
 * Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 *
 * Return list of lists of the suggested products after each character of searchWord is typed.
 *
 *
 *
 * Example 1:
 *
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 *
 * Example 2:
 *
 * Input: products = ["havana"], searchWord = "havana"
 * Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 *
 * Example 3:
 *
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 * Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 *
 * Example 4:
 *
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 *
 *
 * Constraints:
 *
 * 1 <= products.length <= 1000
 * There are no repeated elements in products.
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 */
public class SearchSuggestionsSystem {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> ans = new ArrayList<>(searchWord.length());
        char[] letters = searchWord.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            char letter = letters[i];
            List<String> rst = new ArrayList<>(3);
            ans.add(rst);
            for (int j = 0; j < products.length; j++) {
                if (products[j] != null) {
                    if (i >= products[j].length() || products[j].charAt(i) != letter) {
                        products[j] = null;
                        continue;
                    }
                    if (rst.size() < 3) {
                        rst.add(products[j]);
                    }
                }
            }
        }
        return ans;
    }

    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        int len = searchWord.length();
        int left = 0, right = products.length - 1;
        Arrays.sort(products);
        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            while (left <= right && (products[left].length() <= i || products[left].charAt(i) != searchWord.charAt(i))) {
                left++;
            }
            while (left <= right && (products[right].length() <= i || products[right].charAt(i) != searchWord.charAt(i))) {
                right--;
            }
            List<String> list = new ArrayList<>(Arrays.asList(products).subList(left, Math.min(left + 3, right + 1)));
            ans.add(list);
        }
        return ans;
    }

}
