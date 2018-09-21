/**
 *
 */
public class OverlappingRegions {

    /**
     * How to track whether regions are equivalent, and then how to distinguish/find separate regions...
     *  Could have some encoding for up/down/left/right and recur over the grid, then return the encoding for a region
     *  Always hit the same cell in both grids at the same time
     *  Each cell we go into, mark as visited in a separate array
     *      Once we return the encoding for that region for both grids, we compare encodings
     *          If equal, we increment the number of shared regions
     *      Then iterate through the visited grid until we find a cell that is unvisited
     *          If the cell is a 1 in both picture grids, then perform the recursion again
     *  repeat
     *
     *  return the total number of overlapping regions
     *
     *
     *  Now the question is, will the encodings always be unique if they are matching, or will it be possible to get
     *  false matches? My hunch is with this encoding, false matches are possible...Probably not if we add another
     *  character for when we reach the end of a certain branch, though...
     *      So encoding will be up/down/left/right, done in prefix notation
     */

    public static boolean[][] visited;

//    public static int getNumOverlappingRegions(int[][] img1, int[][] img2) {
//
//    }

    /**
     * Searches through a region of 1's in the given matrix. Registers each 1 it hits in the visited and specific
     * visited matrices, and
     * returns a String encoding of the path it takes to recognize the region.
     */
    public static String identifyRegion(int[][] img, boolean[][] specVisited, int i, int j, String dir) {
        if (!(checkBounds(img, i, j, specVisited) && img[i][j] == 1)) {
            if (checkBounds(img,i,j, specVisited)) {
                visited[i][j] = specVisited[i][j] = true;
                return "e"; // end
            } else {
                return "";
            }
        }
        visited[i][j] = specVisited[i][j] = true;
        String thisCell = dir;

        // up
        thisCell += identifyRegion(img, specVisited, i-1, j, "u");

        // down
        thisCell += identifyRegion(img, specVisited, i+1, j, "d");

        // left
        thisCell += identifyRegion(img, specVisited, i, j-1, "l");

        // right
        thisCell += identifyRegion(img, specVisited, i, j+1, "r");

        return thisCell;
    }

    private static boolean checkBounds(int[][] img, int i, int j, boolean[][] specVisited) {
//        System.out.println(i < img.length && j < img.length && i >= 0 && j >= 0 && !specVisited[i][j]);
        return (i < img.length && j < img.length && i >= 0 && j >= 0 && !specVisited[i][j]);
    }

    public static void main(String[] args) {
        int[][] test1 = {{1,1,1,0},
                         {1,1,0,0},
                         {1,0,0,0},
                         {0,0,0,0}};
        int[][] test2 = {{1,1,1,0},
                {1,1,0,0},
                {1,0,0,0},
                {0,0,0,0}};
        visited = new boolean[test1.length][test1.length];
        System.out.println(identifyRegion(test1, new boolean[test1.length][test1.length],0,0,"s"));
        System.out.println(identifyRegion(test2, new boolean[test1.length][test1.length],0,0,"s"));
    }
}
