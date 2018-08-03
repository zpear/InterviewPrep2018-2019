/**
 * Write a function that returns true if the given Binary Tree is SumTree else false. A SumTree is a Binary Tree where
 * value of every node x is equal to sum of nodes present in its left subtree and right subtree of x. An empty tree is
 * SumTree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.
 */
public class SumTree {

    public static class Tuple {
        public int val;
        public boolean isSumTree;

        public Tuple(int v, boolean st) {
            val = v;
            isSumTree = st;
        }
    }

    public static class Node {
        Node left;
        Node right;
        int val;

        public Node(int v) {
            val = v;
        }
    }

    public static boolean isSumTree(Node root) {
        return isSumTreeHelper(root).isSumTree;
    }

    private static Tuple isSumTreeHelper(Node root) {
        if (root.left == null && root.right == null) {
            return new Tuple(root.val, true);
        }
        if (root == null) {
            return new Tuple(0, true);
        }

        Tuple left = isSumTreeHelper(root.left);
        Tuple right = isSumTreeHelper(root.right);

        int subSum = left.val + right.val;
        boolean subSumTree = left.isSumTree && right.isSumTree;
        boolean thisSumTree = subSumTree && (root.val == subSum);

        return new Tuple(subSum + root.val, thisSumTree);
    }

    public static void main(String[] args) {
        Node root = new Node(26);

        root.left = new Node(10);
        root.left.left = new Node(4);
        root.left.right = new Node(6);

        root.right = new Node(3);
        root.right.left = new Node(1);
        root.right.right = new Node(2);

        System.out.println(isSumTree(root)); // true

        root.val = 25;

        System.out.println(isSumTree(root)); // false
    }
}
