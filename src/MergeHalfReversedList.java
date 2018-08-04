/**
 * Given a linked list, the task is to rearrange the linked list in the following manner:
 *
 * Reverse the second half of given linked list.
 * Rearrange the linked list such that:
 * First element of the linked list is the first element of first half.
 * Second element of the linked list is the first element of second half.
 * Similarly, arrange all elements in the alternate manner i.e. take 3rd element of the linked list from first half
 * and 4th element of the linked list from second half.
 */

public class MergeHalfReversedList {

    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node merge2ndHalfReversed(Node list) {
        Node firstHead = list;
        Node middle = list;
        Node secondHead = list;

        int size = 0;
        while (list != null) {
            size++;
            secondHead = list;
            list = list.next;
        }
        Node temp = middle;
        for (int i = 0; i < size/2; i++) {
            if (i < size/2 - 1)
                temp = temp.next;
            middle = middle.next;
        }
        // separate the lists
        temp.next = null;

        // Reverse the list, formerly last element is now the head of this half
        Node end = reverseList(middle);
        end.next = null;
        // Alternate merge
        Node mergedList = firstHead;
        for (int i = 0; i < size/2; i++) {
            Node firstNext = firstHead.next;
            Node secondNext = secondHead.next;

            firstHead.next = secondHead;
            // if list lengths are uneven we need different end behavior
            secondHead.next = (firstNext != null) ? firstNext : secondNext;

            firstHead = firstNext;
            secondHead = secondNext;
        }

        return mergedList;
    }


    public static Node reverseList(Node head) {
        if (head.next == null) {
            return head;
        }

        Node lastNodeInReversedList = reverseList(head.next);
        lastNodeInReversedList.next = head;
        return head;
    }

    public static void main(String[] args) {
        Node test1 = new Node(1);
        test1.next = new Node(2);
        test1.next.next = new Node(3);
        test1.next.next.next = new Node(4);
        test1.next.next.next.next = new Node(5);
        test1.next.next.next.next.next = new Node(6);

        Node test2 = new Node(1);
        test2.next = new Node(2);
        test2.next.next = new Node(3);
        test2.next.next.next = new Node(4);
        test2.next.next.next.next = new Node(5);
        test2.next.next.next.next.next = new Node(6);
        test2.next.next.next.next.next.next = new Node(7);

        merge2ndHalfReversed(test1);
        merge2ndHalfReversed(test2);

        while (test1 != null) {
            System.out.print (test1.val + " ");
            test1 = test1.next;
        }
        System.out.println();
        while (test2 != null) {
            System.out.print (test2.val + " ");
            test2 = test2.next;
        }
    }
}
