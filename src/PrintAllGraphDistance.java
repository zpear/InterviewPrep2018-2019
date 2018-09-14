import java.util.*;

/**
 * Consider an undirected graph consisting of  nodes where each node is labeled from 1 to n and the edge between any
 * two nodes is always of length 6. We define node  to be the starting position for a BFS. Given a graph, determine
 * the distances from the start node to each of its descendants and return the list in node number order, ascending.
 * If a node is disconnected, it's distance should be -1.
 */
public class PrintAllGraphDistance {

    public static final class Edge {
        public int start;
        public int end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static final class Node {
        public int id;

        public List<Node> next;

        public Node(int id) {
            this.id = id;
            next = new LinkedList<Node>();
        }
    }

    /**
     * Assume we always start at node 0
     */
    public static int[] getAllDistances(Edge[] edges, int numNodes) {
        int[] distancesFromStart = new int[numNodes];
        Node[] nodes = new Node[numNodes];

        // Populate our graph
        for (Edge e : edges) {
            Node n = nodes[e.start];
            if (n == null) {
                n = new Node(e.start);
                nodes[e.start] = n;
            }
            if (nodes[e.end] == null) {
                nodes[e.end] = new Node(e.end);
            }
            n.next.add(nodes[e.end]);
        }

        Queue<Node> parentLayer = new LinkedList<Node>();
        Queue<Node> childLayer = new LinkedList<Node>();

        parentLayer.add(nodes[0]);
        int currentDistance = 0;
        while (!parentLayer.isEmpty()) {
            Node curr = parentLayer.poll();

            if (curr.next != null) {
                for (Node child : curr.next) {
                    childLayer.add(child);
                }
            }
            distancesFromStart[curr.id] = currentDistance;

            if (parentLayer.isEmpty()) {
                // Have expanded the current layer. Time to move to the child layer.
                parentLayer.addAll(childLayer);
                childLayer.clear();
                currentDistance += 6;
            }
        }

        for (int i = 1; i < distancesFromStart.length; i++) {
            if (distancesFromStart[i] == 0) {
                distancesFromStart[i] = -1;
            }
        }

        return distancesFromStart;
    }

    public static void main(String[] args) {
        Edge[] edges = new Edge[4];
        edges[0] = new Edge(0,1);
        edges[1] = new Edge(1,2);
        edges[2] = new Edge(2,3);
        edges[3] = new Edge(0,4);
        int numNodes = 6;
        int[] distances = getAllDistances(edges, numNodes);

        for (int x : distances) {
            System.out.println(x);
        }
    }
}
