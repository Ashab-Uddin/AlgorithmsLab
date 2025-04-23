import java.lang.Character.Subset;
import java.util.*;

class NumDistinct {

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
    NumDistinct(int v, int e) {
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

    // The main function to construct MST using Kruskal's algorithm and count distinct MSTs
    int countDistinctMSTs() {
        // Sort all edges in non-decreasing order of their weight.
        Arrays.sort(edge);

        // Allocate memory for creating V subsets
        Subset[] subsets = new Subset[V];
        for (int i = 0; i < V; ++i)
            subsets[i] = new Subset();

        // Create V subsets with single elements
        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        int mstWeight = 0;
        int edgesInMST = 0;
        int numDistinctMSTs = 1;  // Start with 1 possible MST

        // Traverse through the sorted edges
        for (int i = 0; i < E; i++) {
            Edge next_edge = edge[i];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does not cause a cycle, include it in the MST
            if (x != y) {
                mstWeight += next_edge.weight;
                edgesInMST++;
                Union(subsets, x, y);
            } else {
                // If the edge causes a cycle, it may still be part of a distinct MST.
                // Check if there are multiple edges with the same weight.
                int weight = next_edge.weight;
                int count = 0;

                // Count edges with the same weight
                while (i + count < E && edge[i + count].weight == weight) {
                    count++;
                }

                // If there are multiple edges with the same weight, we have multiple options
                if (count > 1) {
                    numDistinctMSTs *= count;  // Multiply the number of ways to choose the edges
                }

                i += count - 1;  // Skip all edges with the same weight
            }

            // If the MST has exactly V-1 edges, stop.
            if (edgesInMST == V - 1)
                break;
        }

        // If we couldn't include exactly V-1 edges, it means no MST exists.
        if (edgesInMST != V - 1)
            return 0;

        return numDistinctMSTs;
    }

    // Driver Code
    public static void main(String[] args) {
        /* Let us create the following weighted graph:
            10
        0 --------- 1
        | \       / |
        |  \ 5  /  |
        6|   \  /  | 15
        |    \|/   |
        2 --------- 3
            4
        */



         int V = 6; // Number of vertices in graph
        int E = 8; // Number of edges in graph
        NumDistinct graph = new NumDistinct(V, E);


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

        // Function call to count distinct MSTs
        int distinctMSTs = graph.countDistinctMSTs();
        System.out.println("Number of distinct MSTs: " + distinctMSTs);
    }
}
