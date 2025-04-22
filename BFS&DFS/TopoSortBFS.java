import java.util.*;

public class TopoSortBFS {
    static char[] c = new char[20];           // Node names
    static int[][] list = new int[20][20];    // Adjacency list
    static int[] e = new int[20];             // Number of edges from each node
    static int[] inDeg = new int[20];         // In-degree of each node
    static int[] queue = new int[20];         // Queue for BFS
    static int front = 0, rear = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        System.out.print("Enter number of nodes: ");
        n = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter node names (like A B C ...):");
        String[] nodes = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            c[i] = nodes[i].charAt(0);
        }

        for (int i = 0; i < n; i++) {
            System.out.print("Enter number of neighbors of " + c[i] + ": ");
            e[i] = sc.nextInt();
            sc.nextLine();

            if (e[i] > 0) {
                System.out.print("Enter " + e[i] + " neighbors of " + c[i] + " (like B C): ");
                String[] neighbors = sc.nextLine().split(" ");
                for (int j = 0; j < e[i]; j++) {
                    int index = indexOf(neighbors[j].charAt(0), n);
                    list[i][j] = index;
                    inDeg[index]++; // Count in-degree
                }
            }
        }

        // Display in-degrees
        System.out.println("\nInitial In-Degrees:");
        for (int i = 0; i < n; i++) {
            System.out.println(c[i] + ": " + inDeg[i]);
        }

        // Initialize queue with nodes having in-degree 0
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                enqueue(i);
            }
        }

        // BFS Topological Sort
        System.out.println("\nTopological Sort Order:");
        int count = 0;
        while (front < rear) {
            int u = dequeue();
            System.out.print(c[u] + " ");
            count++;

            for (int i = 0; i < e[u]; i++) {
                int v = list[u][i];
                inDeg[v]--;
                if (inDeg[v] == 0) {
                    enqueue(v);
                }
            }
        }

        // Check if cycle exists
        if (count != n) {
            System.out.println("\nCycle detected! Topological sort not possible.");
        }

        sc.close();
    }

    static int indexOf(char ch, int n) {
        for (int i = 0; i < n; i++) {
            if (c[i] == ch) return i;
        }
        return -1;
    }

    static void enqueue(int n) {
        queue[rear++] = n;
    }

    static int dequeue() {
        return queue[front++];
    }
}
