import java.util.*;

public class BFSCharNodesSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int MAX = 26; // Maximum nodes (A-Z)
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < MAX; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.println("Enter edges (like A B):");
        for (int i = 0; i < edges; i++) {
            String[] parts = sc.nextLine().split(" ");
            int u = parts[0].charAt(0) - 'A'; // Convert char to index
            int v = parts[1].charAt(0) - 'A';
            graph.get(u).add(v);
            graph.get(v).add(u); // Undirected graph
        }

        System.out.print("Enter source node (like A): ");
        char srcChar = sc.nextLine().charAt(0);
        int src = srcChar - 'A';

        int[] level = new int[MAX];
        Arrays.fill(level, -1); // -1 means unvisited

        Queue<Integer> queue = new LinkedList<>();
        level[src] = 0;
        queue.add(src);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (level[neighbor] == -1) {
                    level[neighbor] = level[current] + 1;
                    queue.add(neighbor);
                }
            }
        }

        // Print only visited nodes
        System.out.println("Level of each node from source " + srcChar + ":");
        for (int i = 0; i < MAX; i++) {
            if (level[i] != -1) {
                System.out.println("Node " + (char)(i + 'A') + " is at level " + level[i]);
            }
        }

        sc.close();
    }
}
