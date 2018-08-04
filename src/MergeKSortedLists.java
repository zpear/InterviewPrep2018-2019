/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {

    public static class Node {
        int val;
        Node next;

        public Node(int val) { this.val = val; }
        public Node() {}
    }

    /**
     * Merges k lists in O(k*n) time where n is the length of the longest list
     */
    public static Node mergeKSortedLists(Node[] lists) {
        Node mergedListHead = new Node();
        Node mergedListPtr = mergedListHead;

        int totalLength = getNumNodes(lists);
        for (int i = 0; i < totalLength; i++) {
            int min = 0;
            int minIndex = -1;
            for (int j = 0; j < lists.length; j++) {
                if (lists[j] != null) {
                    if (lists[j].val <= min || minIndex == -1) {
                        min = lists[j].val;
                        minIndex = j;
                    }
                }
            }
            mergedListPtr.val = min;
            mergedListPtr.next = (i < totalLength - 1) ? new Node() : null;
            mergedListPtr = mergedListPtr.next;
            lists[minIndex] = lists[minIndex].next;
        }
        return mergedListHead;
    }

    public static int getNumNodes(Node[] lists) {
        int num = 0;
        for (Node n : lists) {
            while (n != null) {
                num++;
                n = n.next;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Node list1 = new Node(1);
        list1.next = new Node(3);
        list1.next.next = new Node(6);

        Node list2 = new Node(1);
        list2.next = new Node(2);

        Node list3 = new Node(5);
        list3.next = new Node(7);
        list3.next.next = new Node(8);

        Node[] lists = {list1, list2, list3};
        Node merged = mergeKSortedLists(lists);

        while (merged != null) {
            System.out.print(merged.val + " ");
            merged = merged.next;
        }
    }
}
