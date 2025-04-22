
import java.util.*;

public class DynamicBFSC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        sc.nextLine(); // consume newline

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("Enter edges (like A B):");
        for (int i = 0; i < e; i++) {
            String[] edge = sc.nextLine().split(" ");
            int u = edge[0].charAt(0) - 'A';
            int w = edge[1].charAt(0) - 'A';
            adj.get(u).add(w);
            adj.get(w).add(u); // remove for directed graph
        }

        System.out.print("Enter starting node (like A): ");
        char startChar = sc.nextLine().charAt(0);
        int start = startChar - 'A';

        boolean[] visited = new boolean[v];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        System.out.print("BFS Traversal: ");
        while (!q.isEmpty()) {
            int curr = q.poll();
            System.out.print((char) (curr + 'A') + " ");
            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    q.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        sc.close();
    }
}

