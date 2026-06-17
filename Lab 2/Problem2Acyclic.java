import java.util.ArrayList;
import java.util.Scanner;

public class Problem2Acyclic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {

            // Read node
            int n = scanner.nextInt();

            // Read edge
            int e = scanner.nextInt();

            // Create an adjacency list 
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

            // Initialize the list for each vertex
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            // Read all edges and build the graph
            for (int i = 0; i < e; i++) {

                int u = scanner.nextInt();
                int v = scanner.nextInt();

                adj.get(u).add(v);
                adj.get(v).add(u);
            }

            // Check if the graph contains a cycle
            
            if (hasCycle(adj, n)) {
                System.out.println("no");  // Cycle found 
            } else {
                System.out.println("yes"); // No cycle 
            }
        }

        scanner.close();
    }

    // This function checks every part of the graph
    private static boolean hasCycle(ArrayList<ArrayList<Integer>> adj, int n) {

        // Keep track of which nodes we have already visited
        boolean[] visited = new boolean[n];

        // Go through every node in the graph
        for (int i = 0; i < n; i++) {

            // If this node has NOT been visited yet,
            if (!visited[i]) {

                // If DFS finds a cycle
                if (dfsCheckCycle(i, -1, visited, adj)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfsCheckCycle(
            int node,              
            int parent,            
            boolean[] visited,     
            ArrayList<ArrayList<Integer>> adj 
    ) {

        visited[node] = true;

        // Look at all neighbors (connected nodes)
        for (int neighbor : adj.get(node)) {

            // If neighbor has NOT been visited yet,
            // we go deeper into that node 
            if (!visited[neighbor]) {

                // If deeper call finds a cycle 
                if (dfsCheckCycle(neighbor, node, visited, adj)) {
                    return true;
                }

            }
            // If neighbor WAS visited AND it's NOT the parent... cycle
            else if (neighbor != parent) {
                return true;
            }
        }

        // If no cycle found from this path
        return false;
    }
}