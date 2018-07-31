/**
 * Given a Linked List where every node represents a linked list and contains two pointers of its type:
 * (i) a next pointer to the next node
 * (ii) a bottom pointer to a linked list where this node is head.
 *
 * You have to flatten the linked list to a single linked list which is sorted.
 */
public class Flatten2DList {

    public static class Node {
        Node right;
        Node down;

        int value;

        public Node() {}
        public Node(int v) {value = v;}
    }

    public static Node flattenList(Node list) {
        if (list.right == null) {
            return list;
        }
        Node rightList = flattenList(list.right);

        Node flattened = sortLists(list, rightList);
        Node test = flattened;
        return flattened;
    }

    private static Node sortLists(Node list1, Node list2) {
        Node sortedListHead = new Node();
        Node nextSortedNode = sortedListHead;
        while (list1 != null || list2 != null) {
            int val;
            if (list1 == null) {
                val = list2.value;
                list2 = list2.down;
            } else if (list2 == null){
                val = list1.value;
                list1 = list1.down;
            } else {
                if (list1.value < list2.value) {
                    val = list1.value;
                    list1 = list1.down;
                } else {
                    val = list2.value;
                    list2 = list2.down;
                }
            }
            nextSortedNode.value = val;
            if (list1 != null || list2 != null) {
                nextSortedNode.down = new Node();
                nextSortedNode = nextSortedNode.down;
            }
        }
        return sortedListHead;
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.down = new Node(7);
        head.down.down = new Node(8);
        head.down.down.down = new Node(30);


        head.right = new Node(10);
        head.right.down = new Node(20);


        head.right.right = new Node(19);
        head.right.right.down = new Node(22);
        head.right.right.down.down = new Node(50);


        head.right.right.right = new Node(28);
        head.right.right.right.down = new Node(35);
        head.right.right.right.down.down = new Node(40);
        head.right.right.right.down.down.down = new Node(45);

        Node sorted = flattenList(head);
        while (sorted != null) {
            System.out.print(sorted.value + " ");
            sorted = sorted.down;
        }
    }
}
