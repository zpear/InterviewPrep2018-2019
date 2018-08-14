import java.util.LinkedList;
import java.util.Queue;

/**
 * Print a binary tree using breadth first search, i.e. print all numbers level by level on a tree.
 * Add a line break between each level.
 */
public class PrintLayersOfBST {

    public static class Node {
        Node left;
        Node right;
        int val;

        public Node(int v) {
            val = v;
        }
    }

    public static void printLayers(Node root) {
        Queue<Node> current = new LinkedList<>();
        Queue<Node> next = new LinkedList<>();

        current.add(root);

        while (!current.isEmpty()) {
            Node c = current.poll();
            if (c != null) {
                System.out.print(c.val + " ");
                next.add(c.left);
                next.add(c.right);
            }
            if(current.isEmpty()) {
                current.addAll(next);
                next.clear();
                System.out.println();
            }
        }

    }

    public static void main(String[] arg) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.left.left = new Node(5);
        root.left.left.right = new Node(6);

        /**
         *              1
         *            2   3
         *          4
         *        5   6
         */
        printLayers(root);
    }
}
