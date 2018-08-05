/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ArrToBST {

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node makeBST(int[] arr) {
        return makeBST(arr, 0, arr.length-1);
    }

    private static Node makeBST(int[] arr, int start, int end) {
        Node p;
        if (end - start == 1) {
            p = new Node(arr[end]);
            p.left = new Node(arr[start]);
        } else if (end - start == 0) {
            p = new Node(arr[end]);
        } else {
            int middle = (start + end) / 2;
            p = new Node(arr[middle]);
            p.left = makeBST(arr, start, middle - 1);
            p.right = makeBST(arr, middle + 1, end);
        }
        return p;
    }

    public static void printTree(Node tree) {
        if (tree == null) {
            return;
        }
        System.out.print(tree.val + " ");
        printTree(tree.left);
        printTree(tree.right);
    }

    public static void main(String[] args) {
        int[] test1 = {1,2,3,4,5};
        int[] test2 = {1,2,3,4,5,6};

        Node tree1 = makeBST(test1);
        Node tree2 = makeBST(test2);

        printTree(tree1);
        System.out.println();
        printTree(tree2);
    }
}
