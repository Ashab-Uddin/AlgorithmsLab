

import java.util.*;

public class TopoSortDFS {
    
    static class Graph {
        private int v; // number of vertices
        private ArrayList<Integer>[] adj; // adjacency list

        public Graph(int v) {
            this.v = v;
            adj = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int u, int v) {
            adj[u].add(v);
        }

        private void dfs(int node, boolean[] vis, Stack<Integer> stk) {
            vis[node] = true;
            for (int nbr : adj[node]) {
                if (!vis[nbr]) {
                    dfs(nbr, vis, stk);
                }
            }
            stk.push(node);
        }

        public void topoSort() {
            Stack<Integer> stk = new Stack<>();
            boolean[] vis = new boolean[v];

            for (int i = 0; i < v; i++) {
                if (!vis[i]) {
                    dfs(i, vis, stk);
                }
            }

            System.out.print("Topological Order: ");
            while (!stk.isEmpty()) {
                System.out.print(stk.pop() + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);

        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println("Performing Topological Sort using DFS:");
        g.topoSort();
    }
}
