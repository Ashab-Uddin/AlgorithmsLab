
import java.util.*;

class Graph {

    // A class to represent a graph edge
    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // Comparator function used for sorting edges based on their weight
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    // A class to represent a subset for union-find
    class Subset {
        int parent, rank;
    }

    int V, E; // V -> number of vertices, E -> number of edges
    Edge[] edge; // collection of all edges

    // Constructor to create a graph with V vertices and E edges
    Graph(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    // A utility function to find set of an element i (uses path compression technique)
    int find(Subset[] subsets, int i) {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // A function that does union of two sets of x and y (uses union by rank)
    void Union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // The main function to construct MST using Kruskal's algorithm
    void KruskalMST() {
        // This will store the resultant MST
        Edge[] result = new Edge[V];
        int e = 0; // An index variable used for result[]
        int i = 0; // An index variable used for sorted edges

        // Step 1: Sort all the edges in non-decreasing order of their weight.
        Arrays.sort(edge);

        // Allocate memory for creating V subsets
        Subset[] subsets = new Subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new Subset();

        // Create V subsets with single elements
        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0; // Index used to pick the next edge
        // Number of edges to be taken is equal to V-1
        while (e < V - 1) {
            // Step 2: Pick the smallest edge. And increment the index for next iteration
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does not cause a cycle, include it in the result and increment the index of result
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }

        // Print the contents of result[] to display the built MST
        System.out.println("Following are the edges in the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree " + minimumCost);
    }

    // Driver Code
    public static void main(String[] args) {
        /* Let us create the following weighted graph
            10
        0 ------- 1
        | \     |
        6|  5\  |15
        |    \ |
        2 ------- 3
            4
        */
        int V = 6; // Number of vertices in graph
        int E = 8; // Number of edges in graph
        Graph graph = new Graph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 5;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 8;

        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 4;

        graph.edge[3].src = 0;
        graph.edge[3].dest = 4;
        graph.edge[3].weight = 7;

        // add edge 0-2
        graph.edge[4].src = 1;
        graph.edge[4].dest = 5;
        graph.edge[4].weight = 2;

        // add edge 0-3
        graph.edge[5].src = 5;
        graph.edge[5].dest = 3;
        graph.edge[5].weight = 3;

        // add edge 1-3
        graph.edge[6].src = 3;
        graph.edge[6].dest = 2;
        graph.edge[6].weight = 2;

        // add edge 2-3
        graph.edge[7].src = 2;
        graph.edge[7].dest = 4;
        graph.edge[7].weight = 3;

        // Function call
        graph.KruskalMST();
    }
}

