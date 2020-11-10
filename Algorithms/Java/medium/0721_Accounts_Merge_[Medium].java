/**
 * 721. Accounts Merge
 *
 *
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 * Input:
 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 *
 * Explanation:
 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 * The second John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Note:
 *
 * The length of accounts will be in the range [1, 1000].
 * The length of accounts[i] will be in the range [1, 10].
 * The length of accounts[i][j] will be in the range [1, 30].
 */
public class AccountsMerge {

    // 并查集
    class UnionNode {
        String name;
        List<String> emails;
        UnionNode parent;
        public UnionNode(String name) {
            this.name = name;
            this.emails = new ArrayList<>();
            this.parent = this;
        }

        public UnionNode getRoot() {
            UnionNode node = this;
            while (node.parent != node) {
                node = node.parent;
            }
            return node;
        }
    }

    List<UnionNode> nodes = new ArrayList<>();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, UnionNode> map = new HashMap<>();

        for (List<String> account : accounts) {
            UnionNode node = new UnionNode(account.get(0));
            nodes.add(node);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (map.containsKey(email)) {
                    UnionNode root = map.get(email).getRoot();
                    UnionNode currRoot = node.getRoot();
                    currRoot.parent = root;
                } else {
                    map.put(email, node);
                    node.emails.add(email);
                }
            }
        }

        for (UnionNode node : nodes) {
            if (node.parent != node) {
                UnionNode root = node.getRoot();
                root.emails.addAll(node.emails);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (UnionNode node : nodes) {
            if (node.parent == node) {
                List<String> account = new ArrayList<>();
                account.add(node.name);
                Collections.sort(node.emails);
                account.addAll(node.emails);
                ans.add(account);
            }
        }
        return ans;
    }


}
