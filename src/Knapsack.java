public class Knapsack {

    public static class Item {
        public int value;
        public int weight;

        public Item(int v, int w) {
            value = v;
            weight = w;
        }
    }

    public static int findMaxFillable(Item[] items, int knapWeight) {
       int[][] grid = new int[items.length][knapWeight];

       for (int row = 0; row < grid.length; row++) {
           for (int col = 0; col < grid[0].length; col++) {
               int itemWeight = items[row].weight;
               int itemValue = (col >= itemWeight) ? items[row].value : 0;
               int addToValue = (row >= 1 && col>= itemWeight) ? grid[row-1][col-itemWeight] : 0;

               int currentMaxValue = (row >= 1) ? grid[row-1][col] : 0;

               grid[row][col] = Math.max(currentMaxValue, itemValue + addToValue);
           }
       }

       return grid[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
        Item[] test1 = {new Item(2,1),
                        new Item(4,12),
                        new Item(2,2),
                        new Item(1,1),
                        new Item(10,4)};
        System.out.println(findMaxFillable(test1, 15));
    }
}
