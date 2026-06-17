import java.util.ArrayList;
import java.util.Scanner;

public class problem2 {


    private static void dfs(int v, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        
        visited[v] = true;

        for (int neighbor : graph.get(v)) {

            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    // Function to check if graph is strongly connected
    private static boolean isStronglyConnected(int n, ArrayList<ArrayList<Integer>> adj) {

        // FIND starting node that has at least one outgoing edge
        int start = 0;

        for (int i = 0; i < n; i++) {
            if (!adj.get(i).isEmpty()) {
                start = i;
                break;
            }
        }

      
        boolean[] visited = new boolean[n];

        dfs(start, adj, visited);

        for (boolean v : visited) {
            if (!v) return false;
        }

      
        //  transpose graph
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            transpose.add(new ArrayList<>());
        }

        // Reverse every edge u -> v into v -> u
        for (int i = 0; i < n; i++) {
            for (int neighbor : adj.get(i)) {
                transpose.get(neighbor).add(i);
            }
        }

        // DFS on transpose graph
        visited = new boolean[n];

        dfs(start, transpose, visited);

        for (boolean v : visited) {
            if (!v) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {

            int n = sc.nextInt();

            int e = sc.nextInt();

            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {

                int u = sc.nextInt(); 
                int v = sc.nextInt(); 

                adj.get(u).add(v);
            }

            // Check strong connectivity and print result
            if (isStronglyConnected(n, adj)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }

        sc.close();
    }
}