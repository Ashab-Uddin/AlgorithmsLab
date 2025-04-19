import java.util.*;

public class CycleDetectionBFS {
    static class Edge {
        int source, destination;
        public Edge(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    static void buildGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));
        graph[2].add(new Edge(2, 1));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 2));
        graph[3].add(new Edge(3, 4));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 1));
        graph[1].add(new Edge(1, 4));
    }

    static boolean hasCycleUsingBFS(ArrayList<Edge>[] graph, int totalVertices) {
        boolean[] isVisited = new boolean[totalVertices];
        int[] parentNode = new int[totalVertices];
        Arrays.fill(parentNode, -1);

        for (int currentVertex = 0; currentVertex < totalVertices; currentVertex++) {
            if (!isVisited[currentVertex]) {
                if (checkCycleBFS(graph, isVisited, parentNode, currentVertex)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkCycleBFS(ArrayList<Edge>[] graph, boolean[] isVisited, int[] parentNode, int startingNode) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.add(startingNode);
        isVisited[startingNode] = true;

        while (!bfsQueue.isEmpty()) {
            int currentNode = bfsQueue.poll();
            for (Edge connection : graph[currentNode]) {
                int neighborNode = connection.destination;
                if (!isVisited[neighborNode]) {
                    isVisited[neighborNode] = true;
                    parentNode[neighborNode] = currentNode;
                    bfsQueue.add(neighborNode);
                } else if (neighborNode != parentNode[currentNode]) {
                    System.out.print("Cycle detected: ");
                    showCyclePath(parentNode, currentNode, neighborNode);
                    return true;
                }
            }
        }
        return false;
    }

    static void showCyclePath(int[] parentNode, int currentNode, int repeatedNode) {
        List<Integer> path = new ArrayList<>();
        int tempNode = currentNode;

        while (tempNode != -1) {
            path.add(tempNode);
            if (tempNode == repeatedNode) break;
            tempNode = parentNode[tempNode];
        }

        Collections.reverse(path);
        path.add(repeatedNode);

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int totalVertices = 5;
        ArrayList<Edge>[] graph = new ArrayList[totalVertices];
        buildGraph(graph);

        if (!hasCycleUsingBFS(graph, totalVertices)) {
            System.out.println("No cycle found in the graph.");
        }
    }
}
