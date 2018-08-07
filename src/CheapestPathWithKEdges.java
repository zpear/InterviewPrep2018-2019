import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the cheapest path between Nodes A and B in k or fewer steps
 */
public class CheapestPathWithKEdges {

    public static class Node {
        int id;
        Edge[] neighbors;
        int[] pathCost;
        boolean visited;

        public Node(int id, int k) {
            this.id = id;
            pathCost = new int[k+1];
            for (int i = 0; i < k+1; i++) {
                pathCost[i] = -1;
            }
            visited = false;
        }
    }

    public static class Edge {
        int cost;
        Node start;
        Node end;

        public Edge(int cost, Node start, Node end) {
            this.cost = cost;
            this.start = start;
            this.end = end;
        }
    }

    public static int findMinCost(Node start, Node end, int k) {
        // Assume there is some path from start to end in k or fewer transfers

        // set start costs to 0
        for (int i = 0; i < k+1; i++) {
            start.pathCost[i] = 0;
        }

        // Go through a variant of BFS
        Queue<Edge> q = new LinkedList<>();
        for (Edge e : start.neighbors) {
            q.add(e);
        }
        // go through all edges for each node
        while(!q.isEmpty()) {
            Edge currentEdge = q.poll();
            Node current = currentEdge.end;
            Node last = currentEdge.start;
            int cost = currentEdge.cost;

            boolean revisit = false;
            // compare paths in last to 1+ paths in current
            for (int i = 0; i < k; i++) {
                if (last.pathCost[i] >= 0) {
                    // value will be -1 if there is no path there in i steps, or if the path hasn't been found yet
                    if (current.pathCost[i+1] < 0) {
                        current.pathCost[i+1] = last.pathCost[i] + cost;
                    } else if (last.pathCost[i] + cost < current.pathCost[i+1]) {
                        revisit = true;
                        current.pathCost[i+1] = last.pathCost[i] + cost;
                    }
                }
            }

            // need to  reevaluate if we have found a cheaper path
            if ((!current.visited || revisit) && current.neighbors != null) {
                for (Edge e : current.neighbors) {
                    q.add(e);
                }
            }
            end.visited = true;
        }
        int minCost = Integer.MAX_VALUE;
        for (int cost : end.pathCost) {
            if (cost != -1) {
                minCost = Math.min(cost, minCost);
            }
        }
        return minCost;
    }

    public static void main(String[] args) {
        Node start = new Node(1,2);
        Node middle = new Node(2, 2);
        Node end = new Node(3,2);

        start.neighbors = new Edge[2];
        middle.neighbors = new Edge[1];

        Edge oneTwo = new Edge(1, start, middle);
        Edge twoThree = new Edge(1, middle, end);
        Edge oneThree = new Edge(3, start, end);

        start.neighbors[0] = oneTwo;
        start.neighbors[1] = oneThree;
        middle.neighbors[0] = twoThree;
        /**
         *         (3)
         *      1 ---> 3
         *      |       ^
         *      V (1)   | (1)
         *      2-------
         */

        System.out.println(findMinCost(start, end, 2));
        int k = 1;
        start = new Node(1,k);
        middle = new Node(2, k);
        end = new Node(3,k);

        start.neighbors = new Edge[2];
        middle.neighbors = new Edge[1];

        oneTwo = new Edge(1, start, middle);
        twoThree = new Edge(1, middle, end);
        oneThree = new Edge(3, start, end);

        start.neighbors[0] = oneTwo;
        start.neighbors[1] = oneThree;
        middle.neighbors[0] = twoThree;
        /**
         *         (3)
         *      1 ---> 3
         *      |       ^
         *      V (1)   | (1)
         *      2-------
         */

        System.out.println(findMinCost(start, end, k));

    }
}
