import java.util.*;

public class BFSCharInput {

    static final int MAX = 26; // Supports A-Z
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited = new boolean[MAX];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize adjacency list
        for (int i = 0; i < MAX; i++) {
            graph.add(new ArrayList<>());
        }

        // Input number of edges
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter edges (e.g., A B):");
        for (int i = 0; i < e; i++) {
            char u = sc.next().toUpperCase().charAt(0);
            char v = sc.next().toUpperCase().charAt(0);
            int from = u - 'A';
            int to = v - 'A';
            graph.get(from).add(to);
            graph.get(to).add(from); // Undirected graph
        }

        // Input starting node
        System.out.print("Enter starting node (e.g., A): ");
        char startChar = sc.next().toUpperCase().charAt(0);
        int start = startChar - 'A';

        // BFS
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        System.out.println("BFS Traversal:");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print((char)(node + 'A') + " ");

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}
