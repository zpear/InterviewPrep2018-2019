public class StockBuySell {

    public static int getMaxProfit(int[] prices) {
        int maxProfit = 0;
        int currentMin = Integer.MAX_VALUE;

        for (int price : prices) {
            int difference = price - currentMin;
            if (difference < 0) {
                currentMin = price;
            } else if (difference > maxProfit) {
                maxProfit = difference;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] test1 = {100, 180, 260, 310, 40, 535, 695};
        int[] test2 = {23,13,25,29,33,19,34,45,65,67};

        System.out.println(getMaxProfit(test1));
        System.out.println(getMaxProfit(test2));
    }
}
