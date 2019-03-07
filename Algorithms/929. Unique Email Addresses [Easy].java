/**
 * 929. Unique Email Addresses
 * 
 * Every email consists of a local name and a domain name, separated by the @ sign.
 * 
 * For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.
 * 
 * Besides lowercase letters, these emails may contain '.'s or '+'s.
 * 
 * If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)
 * 
 * If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)
 * 
 * It is possible to use both of these rules at the same time.
 * 
 * Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * Output: 2
 * Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 * 
 * 
 * Note:
 * 
 * 1 <= emails[i].length <= 100
 * 1 <= emails.length <= 100
 * Each emails[i] contains exactly one '@' character.
 */
public class Solution {

    /**
     * 先拆分出 local 和 rest 部分
     * local部分再进行 substring 和 replaceAll
     * 重新组合后放入set
     */
    public int numUniqueEmails(String[] emails) {

        Set<String> set = new HashSet<>();

        for (String email : emails) {

            int i = email.indexOf("@");
            String local = email.substring(0, i).replaceAll("\\.", "");
            String rest = email.substring(i);
            if (local.contains("+")) {
                local = local.substring(0, local.indexOf("+"));
            }
            set.add(local + rest);
        }
        return set.size();
    }
}

public class Solution {

    /**
     * 使用流式运算与正则表达式
     * while 的判断部分需要优化 需要替换 . 为空串的为 @ 前的部分 即 local部分
     * 这里可能循环多次
     */
    public int numUniqueEmails(String[] emails) {

        return (int) Arrays.stream(emails).map(s -> {
            s = s.replaceAll("\\+.*@", "@");
            while (s.matches(".*\\..*@.*"))
                s = s.replaceFirst("\\.", "");
            return s;
        }).distinct().count();
    }
}