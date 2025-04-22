import java.util.*;

public class BFSCharDynamic {
    static Map<Character, List<Character>> graph = new HashMap<>();
    static Set<Character> visited = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices and edges
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        System.out.print("Enter the vertex labels (like A B C ...): ");
        char[] nodes = new char[v];
        for (int i = 0; i < v; i++) {
            nodes[i] = sc.next().toUpperCase().charAt(0);
            graph.put(nodes[i], new ArrayList<>());
        }

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        System.out.println("Enter the edges (like A B):");
        for (int i = 0; i < e; i++) {
            char u = sc.next().toUpperCase().charAt(0);
            char vtx = sc.next().toUpperCase().charAt(0);
            graph.get(u).add(vtx);
            graph.get(vtx).add(u); // Undirected
        }

        // Input starting node
        System.out.print("Enter starting node (like A): ");
        char start = sc.next().toUpperCase().charAt(0);

        // BFS
        Queue<Character> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        System.out.println("BFS Traversal:");
        while (!queue.isEmpty()) {
            char current = queue.poll();
            System.out.print(current + " ");

            for (char neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}