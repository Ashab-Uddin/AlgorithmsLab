
import java.util.*;

class ShortestPath {
    // A utility function to find the vertex with the minimum distance value
    // from the set of vertices not yet included in the shortest path tree
    static final int V = 5;

    // Function to find the vertex with the minimum distance value
    // from the set of vertices not yet included in shortest path tree
    int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // A utility function to print the constructed distance array
    void printSolution(int dist[]) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t " + dist[i]);
        }
    }

    // Function that implements Dijkstra’s single-source shortest path
    // algorithm for a graph represented using an adjacency matrix
    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V];  // The output array. dist[i] holds the shortest distance from src to i
        Boolean sptSet[] = new Boolean[V]; // sptSet[i] will be true if vertex i is included in the shortest path tree

        // Initialize all distances as INFINITE and sptSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of the source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in the first iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist[] values of the adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                // Update dist[v] only if v is not in sptSet, there is an edge from u to v,
                // and the total weight of the path from src to v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print the constructed distance array
        printSolution(dist);
    }

    // Driver method
    public static void main(String[] args) {
        /* Let us create the example graph discussed above:
            10       5
        (0)------(1)------(2)
        |        |        |
        7        2        9
        |        |        |
        (4)------(3)------(4)
                4
        */
        int graph[][] = new int[][] {
            // {0, 10, 5, 0, 0},
            // {0, 0, 2, 1, 0},
            // {0, 3, 0, 9, 2},
            // {0, 0, 0, 0, 4},
            // {7, 0, 0, 6, 0}
            {0, 6, 0, 1, 0},
            {6, 0, 5, 2, 2},
            {0, 5, 0, 0, 5},
            {1, 2, 0, 0, 1},
            {0, 2, 5, 1, 0}
        };

        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, 0); // Find shortest paths from vertex 0
    }
}

