import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int e = scanner.nextInt();

        Graph g = new Graph(n);

        for (int i = 0; i < e; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            g.addEdge(a, b);
        }

        System.out.println("BFS Traversal:");
        g.BFS();

        System.out.println("\nDFS Traversal:");
        g.DFS(0);

        scanner.close();
    }
}