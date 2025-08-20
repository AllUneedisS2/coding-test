import java.util.Arrays;

public class StockPrice {

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        int[] prices = {1, 2, 3, 2, 3};
        int[] result = stockPrice.solution(prices);
        System.out.println(Arrays.toString(result));
    }

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            answer[i] = second(i, prices[i], prices);
        }
        return answer;
    }

    static int second(int idx, int target, int[] prices) {
        int until = prices[idx];
        for (int i = idx + 1; i < prices.length; i++) {
            if (prices[i] < until)
                return i - idx;
        }
        return prices.length - 1 - idx;
    }

}
