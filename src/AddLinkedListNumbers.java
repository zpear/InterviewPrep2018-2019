/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 **/
public class AddLinkedListNumbers {

    public static class Node {
        public int val;
        public Node next;

        public Node(int v) {
            val = v;
        }
        public Node() { }
    }

    public static Node addLists(Node list1, Node list2) {
        Node sumList = new Node();
        Node sumHead = sumList;
        int carryOver = 0;

        while (list1 != null || list2 != null) {
            int val = carryOver;

            if (list1 == null) {
                val += list2.val;
                list2 = list2.next;
            } else if (list2 == null) {
                val += list1.val;
                list1 = list1.next;
            } else {
                val += list1.val + list2.val;
                list1 = list1.next;
                list2 = list2.next;
            }

            carryOver = val/10;
            val %= 10;

            sumList.val = val;

            if (list1 != null || list2 != null) {
                sumList.next = new Node();
            } else if (carryOver > 0) {
                sumList.next = new Node(carryOver);
            }
            sumList = sumList.next;
        }

        return sumHead;
    }

    public static void main(String[] args) {
        Node list1 = new Node(2);
        list1.next = new Node(4);
        list1.next.next = new Node(3);

        Node list2 = new Node(5);
        list2.next = new Node(6);
        list2.next.next = new Node(4);

        Node sum = addLists(list1, list2);

        while (sum != null) {
            System.out.print(sum.val + " ");
            sum = sum.next;
        }
        System.out.println();

        list1.next.next.val = 5;

        sum = addLists(list1, list2);

        while (sum != null) {
            System.out.print(sum.val + " ");
            sum = sum.next;
        }
        System.out.println();
    }


}
