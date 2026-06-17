import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem3Prims {

    //Edge Class
    static class Edge implements Comparable<Edge> {
        int u;       // starting vertex of the edge
        int v;       // ending vertex of the edge
        int weight;  // weight

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        // used by PriorityQueue to sort edges by smallest weight first
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {

            // Number of vertices
            int n = scanner.nextInt();

            // Number of edges
            int e = scanner.nextInt();

            // Create adjacency list (each vertex has a list of edges)
            ArrayList<ArrayList<Edge>> adj = new ArrayList<>();

            // Initialize empty list for each vertex
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            // Read all edges from input
            for (int i = 0; i < e; i++) {
                int u = scanner.nextInt();       // first vertex
                int v = scanner.nextInt();       // second vertex
                int weight = scanner.nextInt();   // edge weight

                // Since underectied add edges in both directions 
                adj.get(u).add(new Edge(u, v, weight));
                adj.get(v).add(new Edge(v, u, weight));
            }

            
            findPrimMST(adj, n);

            System.out.println();
        }

        scanner.close(); 
    }

    // Function Prim's Algorithm
    private static void findPrimMST(ArrayList<ArrayList<Edge>> adj, int n) {

        // Array to keep track of nodes is already included in MST
        boolean[] inMST = new boolean[n];

        // Priority queue stores edges sorted by smallest weight first
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // Stores edges that are part of the MST
        ArrayList<Edge> mstEdges = new ArrayList<>();

        int totalMSTWeight = 0;

        // Start Prim's algorithm from vertex 0
        inMST[0] = true;

        // Add all edges from node 0 into the priority queue
        for (Edge edge : adj.get(0)) {
            pq.add(edge);
        }

        // Continue until we have enough edges or queue is empty
        while (!pq.isEmpty() && mstEdges.size() < n - 1) {

            // Get the smallest edge from the priority queue
            Edge currentEdge = pq.poll();

            int u = currentEdge.u;
            int v = currentEdge.v;

            // If the destination vertex is already in MST, skip it
            if (inMST[v]) {
                continue;
            }

            inMST[v] = true;

            // Store this edge as part of MST
            mstEdges.add(currentEdge);

            // Add weight of this edge to total MST cost
            totalMSTWeight += currentEdge.weight;

            // Add all edges from the newly added vertex into PQ
            for (Edge nextEdge : adj.get(v)) {
                if (!inMST[nextEdge.v]) {
                    pq.add(nextEdge);
                }
            }
        }

        for (Edge edge : mstEdges) {
            System.out.println("Edge " + edge.u + "-" + edge.v +
                    " has a weight of " + edge.weight);
        }

        System.out.println("MST=" + totalMSTWeight);
    }
}