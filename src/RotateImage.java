/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate
 * the image by 90 degrees. Can you do this in place?
 */
public class RotateImage {

    public static void rotateImage(int[][] image) {
        int loops = image.length / 2;
        int lastIndex = image.length-1;

        Print p = new Print();
        for (int loop = 0; loop < loops; loop++) {
            for (int currentPlace = loop; currentPlace < lastIndex-loop; currentPlace++) {
                int top = image[loop][currentPlace];
                int right = image[currentPlace][lastIndex - loop];
                int bottom = image[lastIndex-loop][lastIndex-currentPlace];
                int left = image[lastIndex-currentPlace][loop];

                // right = top
                image[currentPlace][lastIndex-loop] = top;
                // bottom = right
                image[lastIndex-loop][lastIndex-currentPlace] = right;
                // left = bottom
                image[lastIndex-currentPlace][loop] = bottom;
                // top = left
                image[loop][currentPlace] = left;
            }
        }
    }

    public static void main(String[] args) {
        int[][] test1 = {{1,1,1,1},
                        {2,2,2,2},
                        {3,3,3,3},
                        {4,4,4,4}};

        Print p = new Print();
        p.print2DArr(test1);
        System.out.println();
        rotateImage(test1);
        p.print2DArr(test1);
    }
}
