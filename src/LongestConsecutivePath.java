/**
 * Given a n*n matrix where all numbers are distinct, find the maximum length path (starting from any cell) such that
 * all cells along the path are in increasing order with a difference of 1.
 *
 * We can move in 4 directions from a given cell (i, j), i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or
 * (i, j-1) with the condition that the adjacent cells have a difference of 1.
 */
public class LongestConsecutivePath {

    private static int[][] vals;

    public static int findLongestPath(int[][] grid) {
        vals = new int[grid.length][grid.length];
        findLongestPath(grid, 0, 0);

        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals.length; j++) {
                if (vals[i][j] == 0) {
                    findLongestPath(grid, i, j);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < vals.length; i++) {
            for (int j = 0; j < vals.length; j++) {
                max = Math.max(max, vals[i][j]);
            }
        }
        return max+1;
    }

    private static int findLongestPath(int[][] grid, int i, int j) {

        vals[i][j] = -1; // to denote visited while we go through the recursion
        if (checkBounds(grid, i,j+1) && Math.abs(grid[i][j] - grid[i][j+1]) <= 1) {
            return vals[i][j] = 1 + findLongestPath(grid, i, j+1); // right
        }
        if (checkBounds(grid, i,j-1) && Math.abs(grid[i][j] - grid[i][j-1]) <= 1) {
            return vals[i][j] = 1 + findLongestPath(grid, i, j-1); // left
        }
        if (checkBounds(grid, i-1,j) && Math.abs(grid[i][j] - grid[i-1][j]) <= 1) {
            return vals[i][j] = 1 + findLongestPath(grid, i-1, j); // up
        }
        if (checkBounds(grid, i+1,j) && Math.abs(grid[i][j] - grid[i+1][j]) <= 1) {
            return vals[i][j] = 1 + findLongestPath(grid, i+1, j); // down
        }
        return vals[i][j] = 1;
    }

    private static boolean checkBounds(int[][] grid, int i, int j) {
        return !(i < 0 || j < 0 || i >= grid.length || j >= grid.length || vals[i][j] != 0);
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 9},
                        {5, 3, 8},
                        {4, 6, 7}};

        System.out.println(findLongestPath(grid));
    }
}
