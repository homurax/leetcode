/**
 * 1410. HTML Entity Parser
 *
 *
 * HTML entity parser is the parser that takes HTML code as input and replace all the entities of the special characters by the characters itself.
 *
 * The special characters and their entities for HTML are:
 *
 * Quotation Mark: the entity is &quot; and symbol character is ".
 * Single Quote Mark: the entity is &apos; and symbol character is '.
 * Ampersand: the entity is &amp; and symbol character is &.
 * Greater Than Sign: the entity is &gt; and symbol character is >.
 * Less Than Sign: the entity is &lt; and symbol character is <.
 * Slash: the entity is &frasl; and symbol character is /.
 * Given the input text string to the HTML parser, you have to implement the entity parser.
 *
 * Return the text after replacing the entities by the special characters.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "&amp; is an HTML entity but &ambassador; is not."
 * Output: "& is an HTML entity but &ambassador; is not."
 * Explanation: The parser will replace the &amp; entity by &
 *
 *
 * Example 2:
 *
 * Input: text = "and I quote: &quot;...&quot;"
 * Output: "and I quote: \"...\""
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 10^5
 * The string may contain any possible characters out of all the 256 ASCII characters.
 */
public class HTMLEntityParser {

    public String entityParser(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");
        StringBuilder sb = new StringBuilder();
        int n = text.length();
        for (int i = 0; i < n; ) {
            if (text.charAt(i) == '&') {
                int j = i + 1;
                while (j < n && j - i < 6 && text.charAt(j) != ';') {
                    j++;
                }
                String sub = text.substring(i, Math.min(j + 1, n));
                if (map.containsKey(sub)) {
                    sb.append(map.get(sub));
                    i = j + 1;
                    continue;
                }
            }
            sb.append(text.charAt(i++));
        }
        return sb.toString();
    }

}
