import java.util.*;

public class Main {

    static int totalNodes;

    // BFS to find augmenting path, returns true if sink is reachable
    static boolean bfs(int[][] capacity, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[totalNodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < totalNodes; v++) {
                if (!visited[v] && capacity[u][v] > 0) {
                    visited[v] = true;
                    parent[v] = u;
                    if (v == sink) return true;
                    queue.add(v);
                }
            }
        }
        return false;
    }

    static int fordFulkerson(int[][] capacity, int source, int sink) {
        int[] parent = new int[totalNodes];
        int maxFlow = 0;

        while (bfs(capacity, source, sink, parent)) {
            // Find min capacity along the augmenting path
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, capacity[u][v]);
            }
            // Update capacities along the path
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                capacity[u][v] -= pathFlow;
                capacity[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int applicants = sc.nextInt();
        int jobs = sc.nextInt();

        // source=0, applicants=1..applicants, jobs=applicants+1..applicants+jobs, sink=last
        int source = 0;
        int sink = applicants + jobs + 1;
        totalNodes = sink + 1;

        int[][] capacity = new int[totalNodes][totalNodes];

        // Source -> each applicant (capacity 1)
        for (int i = 1; i <= applicants; i++) {
            capacity[source][i] = 1;
        }

        // Read adjacency matrix and add applicant -> job edges
        for (int i = 1; i <= applicants; i++) {
            for (int j = 1; j <= jobs; j++) {
                int val = sc.nextInt();
                if (val == 1) {
                    capacity[i][applicants + j] = 1;
                }
            }
        }

        // Each job -> sink (capacity 1)
        for (int j = 1; j <= jobs; j++) {
            capacity[applicants + j][sink] = 1;
        }

        int answer = fordFulkerson(capacity, source, sink);
        System.out.println("The maximum number of applicants matching for the jobs is " + answer);
        sc.close();
    }
}



