/**
 * 535. Encode and Decode TinyURL
 *
 *
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 *
 * There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 * Implement the Solution class:
 *
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.
 *
 *
 * Example 1:
 *
 * Input: url = "https://leetcode.com/problems/design-tinyurl"
 * Output: "https://leetcode.com/problems/design-tinyurl"
 *
 * Explanation:
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // returns the encoded tiny url.
 * string ans = obj.decode(tiny); // returns the original url after decoding it.
 *
 *
 * Constraints:
 *
 * 1. 1 <= url.length <= 10^4
 * 2. url is guranteed to be a valid URL.
 */
public class EncodeAndDecodeTinyURL {

    private String prefix = "https://tinyurl.com/";
    private Map<String, String> origin2Tiny = new HashMap<>();
    private Map<String, String> tiny2Origin = new HashMap<>();
    private String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private Random random = new Random();

    public String encode(String longUrl) {
        // return longUrl;
        while (!origin2Tiny.containsKey(longUrl)) {
            char[] cs = new char[6];
            for (int i = 0; i < 6; i++) {
                cs[i] = str.charAt(random.nextInt(str.length()));
            }
            String cur = prefix + String.valueOf(cs);
            if (tiny2Origin.containsKey(cur)) {
                continue;
            }
            tiny2Origin.put(cur, longUrl);
            origin2Tiny.put(longUrl, cur);
        }
        return origin2Tiny.get(longUrl);
    }

    public String decode(String shortUrl) {
        // return shortUrl;
        return tiny2Origin.get(shortUrl);
    }

}
