import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, zig-zag print the nodes.
 *         1
 *        / \
 *       2 3
 *     / \ / \
 *    7 6 5 4
 *
 * Prints 1, 2, 3, 4, 5, 6, 7
 */

public class ZigZagBST {

    public static class Node {
        Node left;
        Node right;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void zigZagPrint(Node root) {
        // Keep track of current level and add to next level
        // Keep track of odd or even level, determine whether to pop from front or back of queue
        LinkedList<Node> currentLevel = new LinkedList<Node>();
        LinkedList<Node> nextLevel = new LinkedList<Node>();
        int levelCount = 0;

        currentLevel.add(root);
        while (!currentLevel.isEmpty()) {
            Node current;

            // Alternate popping based on even/odd for zigzag
            if (levelCount % 2 != 0) {
                current = currentLevel.removeFirst();
            } else {
                current = currentLevel.removeLast();
            }
            System.out.print(current.val + " ");

            if (current.left != null) {
                nextLevel.add(current.left);
            }
            if (current.right != null) {
                nextLevel.add(current.right);
            }

            if (currentLevel.isEmpty()) {
                // Have finished moving through this level. Reset for next
                currentLevel = nextLevel;
                nextLevel = new LinkedList<Node>();
                levelCount++;
            }

        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.right = new Node(4);
        root.right.left = new Node(5);
        root.left.right = new Node(6);
        root.left.left = new Node(7);

        zigZagPrint(root);
    }
}
