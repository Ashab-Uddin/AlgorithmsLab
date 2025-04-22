import java.util.*;

class DijkstraPathCount {
    static final int V = 5;

    // Function to find the vertex with the minimum distance value
    static int minDistance(int dist[], boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // Function to print the shortest paths and their counts
    static void printSolution(int dist[], int pathCount[], int src, int dest) {
        System.out.println("Shortest distance from source to destination: " + dist[dest]);
        System.out.println("Number of shortest paths from source to destination: " + pathCount[dest]);
    }

    // Function to implement Dijkstra's algorithm and find the number of shortest paths
    static void dijkstra(int graph[][], int src, int dest) {
        int dist[] = new int[V];
        boolean sptSet[] = new boolean[V];
        int pathCount[] = new int[V]; // Stores the number of shortest paths to each vertex

        // Initialize distances and path counts
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(pathCount, 0);
        dist[src] = 0;
        pathCount[src] = 1; // There's one way to reach the source (itself)

        // Dijkstra's algorithm
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                // If there's an edge from u to v, and v is not yet processed
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE) {
                    // If a shorter path to v is found
                    if (dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                        pathCount[v] = pathCount[u]; // Update the number of paths to v
                    }
                    // If an equal length path to v is found, add the number of ways to u
                    else if (dist[u] + graph[u][v] == dist[v]) {
                        pathCount[v] += pathCount[u];
                    }
                }
            }
        }

        printSolution(dist, pathCount, src, dest);
    }

    // Driver method
    public static void main(String[] args) {
        int graph[][] = new int[][]{
            {0, 10, 5, 0, 0},
            {0, 0, 2, 1, 0},
            {0, 3, 0, 9, 2},
            {0, 0, 0, 0, 4},
            {7, 0, 0, 6, 0}
        };

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the source vertex (0 to 4): ");
        int src = scanner.nextInt();
        System.out.println("Enter the destination vertex (0 to 4): ");
        int dest = scanner.nextInt();

        dijkstra(graph, src, dest);
    }
}
