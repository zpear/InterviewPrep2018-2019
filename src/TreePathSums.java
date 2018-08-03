import java.util.*;

/**
 * You are given a binary tree in which each node contains a value. Design an algorithm to print all paths
 * which sum up to that value. Note that it can be any path in the tree - it does not have to start at the root.
 */
public class TreePathSums {

    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void printSumPaths(Node root, int sum) {
        printSumPathsHelper(root, sum, new HashMap<>(), new HashMap<>());
    }

    private static void printSumPathsHelper(Node root, int sum,
                                     Map<Integer, List<List<Node>>> sumToList, Map<List<Node>, Integer> listToSum) {
        if (root == null) {
            return;
        }
        if (root.val == sum) {
            System.out.println(root.val);
        }

        int operand = sum - root.val;
        if (sumToList.get(operand) != null) {
            // there is at least one path we can print that equals the sum
            for (List<Node> prefix : sumToList.get(operand)) {
                for (Node n : prefix) {
                    System.out.print(n.val + ", ");
                }
                System.out.println(root.val);
            }
        }

        // Now create new maps updated with root to pass to its children
        Map<Integer, List<List<Node>>> updSumToList = new HashMap<>();
        Map<List<Node>, Integer> updListToSum = new HashMap<>();

        for (List<List<Node>> parentList : sumToList.values()) {
            int pathSum = root.val;
            List<List<Node>> updatedParentList = new LinkedList<>();
            for (List<Node> nestedList : parentList) {
                // update the sum
                Integer oldPathSum = listToSum.get(nestedList);
                pathSum = (oldPathSum == null) ? pathSum : pathSum + oldPathSum;
                // update the nested list
                List<Node> updatedNestedList = new LinkedList<>();
                for (Node n : nestedList) {
                    updatedNestedList.add(n);
                }
                updatedNestedList.add(root);

                updatedParentList.add(updatedNestedList);

                // update the list to sum map
                updListToSum.put(updatedNestedList, pathSum);
            }
            // update the sum to list map
            updSumToList.put(pathSum, updatedParentList);
        }

        // add this node to each map
        List<Node> self = new LinkedList<>();
        self.add(root);
        List<List<Node>> pSelf = new LinkedList<>();
        pSelf.add(self);

        // Don't overwrite existing updates
        List<List<Node>> temp = updSumToList.get(root.val);
        if (temp != null) {
            pSelf.addAll(temp);
        }
        updSumToList.put(root.val, pSelf);
        updListToSum.put(self, root.val);

        printSumPathsHelper(root.left, sum, updSumToList, updListToSum);
        printSumPathsHelper(root.right, sum, updSumToList, updListToSum);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(3);
        root.right = new Node(-1);

        root.left.left = new Node(2);
        root.left.right = new Node(1);

        root.right.left = new Node(4);
        root.right.right = new Node(5);

        root.left.right.left = new Node(1);

        root.right.left.left = new Node(1);
        root.right.left.right = new Node(2);

        root.right.right.right = new Node(6);

        printSumPaths(root, 5);
    }
}
