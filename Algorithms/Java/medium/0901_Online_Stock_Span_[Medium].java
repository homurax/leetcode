/**
 * 901. Online Stock Span
 *
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
 *
 *
 *
 * Example 1:
 *
 * Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
 * Output: [null,1,1,1,2,1,4,6]
 * Explanation:
 * First, S = StockSpanner() is initialized.  Then:
 * S.next(100) is called and returns 1,
 * S.next(80) is called and returns 1,
 * S.next(60) is called and returns 1,
 * S.next(70) is called and returns 2,
 * S.next(60) is called and returns 1,
 * S.next(75) is called and returns 4,
 * S.next(85) is called and returns 6.
 *
 * Note that (for example) S.next(75) returned 4, because the last 4 prices
 * (including today's price of 75) were less than or equal to today's price.
 *
 *
 * Note:
 *
 * Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
 * There will be at most 10000 calls to StockSpanner.next per test case.
 * There will be at most 150000 calls to StockSpanner.next across all test cases.
 * The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.
 */
public class OnlineStockSpan {

    class StockSpanner {

        final private int[] stocks;
        final private int[] days;
        private int cur = -1;

        public StockSpanner() {
            this.stocks = new int[10000];
            this.days = new int[10000];
        }

        public int next(int price) {
            stocks[++cur] = price;
            if (cur == 0 || price < stocks[cur - 1]) {
                days[cur] = 1;
            } else {
                int pre = cur - 1;
                int day = 1;
                while (pre >= 0 && stocks[pre] <= price) {
                    day += days[pre];
                    pre -= days[pre];
                }
                days[cur] = day;
            }
            return days[cur];
        }
    }

    // 单调栈
    class StockSpanner2 {

        final private int[] stocks;
        final private int[] days;
        private int stockIdx = -1;
        private int dayIdx = -1;

        public StockSpanner2() {
            this.stocks = new int[10000];
            this.days = new int[10000];
        }

        public int next(int price) {
            int day = 1;
            while (stockIdx >= 0 && stocks[stockIdx] <= price) {
                stockIdx--;
                day += days[dayIdx--];
            }
            stocks[++stockIdx] = price;
            days[++dayIdx] = day;
            return day;
        }
    }

    class StockSpanner3 {

        final private int[] stack;
        private int top = 0;

        public StockSpanner3() {
            this.stack = new int[10000];
        }

        public int next(int price) {
            if (top == 0 || stack[top - 1] >>> 14 > price) {
                stack[top++] = (price << 14) + 1;
                return 1;
            }

            int count = 1;
            while (top != 0 && stack[top - 1] >>> 14 <= price) {
                count += (stack[--top] & 0x3fff);
            }

            stack[top++] = (price << 14) + count;
            return count;
        }
    }

}
