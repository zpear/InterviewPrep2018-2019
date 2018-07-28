import java.util.HashMap;
import java.util.HashSet;

/**
 * There are two singly linked lists in a system. By some programming error, the end node of one of the linked list
 * got linked to the second list, forming an inverted Y shaped list. Write a program to get the point where two
 * linked list merge.
 */
public class IntersectedLists {

    public static class Node {
        public int value;
        public Node next;

        public Node(int v) {
            value = v;
        }
    }

    public static Node findIntersection(Node l1, Node l2) {
        HashSet<Node> l1Nodes = new HashSet<Node>();
        while (l1 != null) {
            l1Nodes.add(l1);
            l1 = l1.next;
        }

        while (l2 != null) {
            if (l1Nodes.contains(l2)) {
                return l2;
            }
            l2 = l2.next;
        }
        return null;
    }

    public static void main(String[] args) {
        Node l1 = new Node(1);
        Node l2 = new Node(2);
        Node l3 = new Node(3);
        Node l4 = new Node(4);
        Node l5 = new Node(5);
        Node l6 = new Node(6);
        Node l7 = new Node(7);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        l5.next = l6;
        l6.next = l7;
        l7.next = l2;

        Node intersect = findIntersection(l1, l5);
        System.out.println(intersect.value); // 2
    }
}
