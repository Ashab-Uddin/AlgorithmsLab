import java.util.*;
public class DynamicBFS {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print("Enter number of vertices: ");
int v = sc.nextInt();
List<List<Integer>> adj = new ArrayList<>();
for (int i = 0; i < v; i++) {
adj.add(new ArrayList<>());
}
System.out.print("Enter number of edges: ");
int e = sc.nextInt();
System.out.println("Enter edges (u v):");
for (int i = 0; i < e; i++) {
int u = sc.nextInt();
int w = sc.nextInt(); // only input number graph
adj.get(u).add(w);
adj.get(w).add(u); // remove this line for directed graph
}
System.out.print("Enter starting node: ");
int start = sc.nextInt();
boolean[] visited = new boolean[v];
Queue<Integer> q = new LinkedList<>();
q.add(start);
visited[start] = true;
System.out.print("BFS Traversal: ");
while (!q.isEmpty()) {
int curr = q.poll();
System.out.print(curr + " ");
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
