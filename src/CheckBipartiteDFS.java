/**
 * Given a connected graph, check if the graph is bipartite or not. A bipartite graph is possible if the graph coloring
 * is possible using two colors such that vertices in a set are colored with the same color.
 */
public class CheckBipartiteDFS {

    public static class Node {
        Node[] neighbors;
        boolean visited = false;
        boolean color;
    }

    public static boolean checkBipartite(Node current, boolean lastColor) {
        current.visited = true;
        current.color = !lastColor;

        if (current.neighbors == null) {
            return true;
        }

        for (Node adj : current.neighbors) {
            if (adj.visited) {
                if (adj.color == current.color) {
                    return false;
                }
            } else if (!checkBipartite(adj, current.color)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node graph = new Node();
        graph.neighbors = new Node[3];
        for (int i = 0; i < graph.neighbors.length; i++) {
            graph.neighbors[i] = new Node();
        }
        graph.neighbors[0].neighbors = new Node[1];
        graph.neighbors[0].neighbors[0] = graph;

        // Determines true/false
        graph.neighbors[1].neighbors = new Node[1];
        graph.neighbors[1].neighbors[0] = graph.neighbors[0];

        System.out.println(checkBipartite(graph, true));

        /**     |-------|
         *      V       |
         *      x -> y --
         *      |    ^
         *      |    |      <- If present, this edge makes bipartite false. If absent, true.
         *      |--> x
         *      |--> w
         */
    }
}
