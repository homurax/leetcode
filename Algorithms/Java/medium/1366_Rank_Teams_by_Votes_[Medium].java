/**
 * 1366. Rank Teams by Votes
 *
 *
 * In a special ranking system, each voter gives a rank from highest to lowest to all teams participated in the competition.
 *
 * The ordering of teams is decided by who received the most position-one votes. If two or more teams tie in the first position, we consider the second position to resolve the conflict, if they tie again, we continue this process until the ties are resolved. If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.
 *
 * Given an array of strings votes which is the votes of all voters in the ranking systems. Sort all teams according to the ranking system described above.
 *
 * Return a string of all teams sorted by the ranking system.
 *
 *
 *
 * Example 1:
 *
 * Input: votes = ["ABC","ACB","ABC","ACB","ACB"]
 * Output: "ACB"
 * Explanation: Team A was ranked first place by 5 voters. No other team was voted as first place so team A is the first team.
 * Team B was ranked second by 2 voters and was ranked third by 3 voters.
 * Team C was ranked second by 3 voters and was ranked third by 2 voters.
 * As most of the voters ranked C second, team C is the second team and team B is the third.
 *
 * Example 2:
 *
 * Input: votes = ["WXYZ","XYZW"]
 * Output: "XWYZ"
 * Explanation: X is the winner due to tie-breaking rule. X has same votes as W for the first position but X has one vote as second position while W doesn't have any votes as second position.
 *
 * Example 3:
 *
 * Input: votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]
 * Output: "ZMNAGUEDSJYLBOPHRQICWFXTVK"
 * Explanation: Only one voter so his votes are used for the ranking.
 *
 * Example 4:
 *
 * Input: votes = ["BCA","CAB","CBA","ABC","ACB","BAC"]
 * Output: "ABC"
 * Explanation:
 * Team A was ranked first by 2 voters, second by 2 voters and third by 2 voters.
 * Team B was ranked first by 2 voters, second by 2 voters and third by 2 voters.
 * Team C was ranked first by 2 voters, second by 2 voters and third by 2 voters.
 * There is a tie and we rank teams ascending by their IDs.
 *
 * Example 5:
 *
 * Input: votes = ["M","M","M","M"]
 * Output: "M"
 * Explanation: Only team M in the competition so it has the first rank.
 *
 *
 * Constraints:
 *
 * 1. 1 <= votes.length <= 1000
 * 2. 1 <= votes[i].length <= 26
 * 3. votes[i].length == votes[j].length for 0 <= i, j < votes.length.
 * 4. votes[i][j] is an English upper-case letter.
 * 5. All characters of votes[i] are unique.
 * 6. All the characters that occur in votes[0] also occur in votes[j] where 1 <= j < votes.length.
 */
public class RankTeamsByVotes {

    // key 范围优先 可以 Map 换成二维数组
    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        if (votes.length == 1 || n == 1) {
            return votes[0];
        }
        Map<Character, int[]> rankMap = new HashMap<>();
        for (String vote : votes) {
            for (int i = 0; i < n; i++) {
                rankMap.computeIfAbsent(vote.charAt(i), k -> new int[26])[i]++;
            }
        }
        return rankMap.entrySet().stream().sorted((a, b) -> {
            int[] ranks1 = a.getValue();
            int[] ranks2 = b.getValue();
            for (int i = 0; i < 26; i++) {
                if (ranks1[i] != ranks2[i]) {
                    return ranks2[i] - ranks1[i];
                }
            }
            return a.getKey() - b.getKey();
        }).map(Map.Entry::getKey).map(String::valueOf).collect(Collectors.joining());
    }

}
