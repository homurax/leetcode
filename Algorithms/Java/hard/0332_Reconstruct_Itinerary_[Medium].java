/**
 * 332. Reconstruct Itinerary
 *
 *
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Example 2:
 *
 *
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 *
 * Constraints:
 *
 * 1. 1 <= tickets.length <= 300
 * 2. tickets[i].length == 2
 * 3. fromi.length == 3
 * 4. toi.length == 3
 * 5. fromi and toi consist of uppercase English letters.
 * 6. fromi != toi
 */
public class ReconstructItinerary {

    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private LinkedList<String> itinerary = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), from -> new PriorityQueue<>()).add(ticket.get(1));
        }
        dfs("JFK");
        return itinerary;
    }

    public void dfs(String curr) {
        while (graph.containsKey(curr) && graph.get(curr).size() > 0) {
            // 贪心选择最小的可能进入死胡同 每个节点最多只有一个死胡同
            dfs(graph.get(curr).poll());
        }
        // 遍历完一个节点所连的所有节点后，才将该节点入栈，逆序入栈
        // 对于当前节点而言，从它的每一个非「死胡同」分支出发进行深度优先搜索，都将会搜回到当前节点
        // 从它的「死胡同」分支出发进行深度优先搜索将不会搜回到当前节点。也就是说当前节点的死胡同分支将会优先于其他非「死胡同」分支入栈
        itinerary.addFirst(curr);
    }

}
