import java.util.*;

public class BreadFS {
    static char[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'S'};
    static int e[] = {2, 2, 2, 2, 2, 2, 3, 3};
    static int list[][] = {{3, 7}, {4, 7}, {5, 7}, {0, 6}, {1, 6}, {2, 6}, {3, 4, 5}, {0, 1, 2}}; 
    // adjacency list of graph
    static int[] checked = new int[20];
    static int[] que = new int[20];
    static int first = 0, last = 0;

    public static void main(String[] args) {
        int i, j, n;

        enq(7);  // Start BFS from node 7 (which corresponds to 'S')
        while (first < last) {
            n = dq();
            for (i = 0; i < e[n]; i++) {
                if (notChecked(list[n][i]) == 1) {
                    enq(list[n][i]);
                }
            }
        }
    }

    static int notChecked(int n) { // This method checks if the node has been visited or not
        if (checked[n] == 1) {
            return 0;
        }
        return 1;
    }

    static void enq(int n) { // This method is used to enqueue a node
        checked[n] = 1;
        que[last] = n;
        last++;
    }

    static int dq() { // This method is used to dequeue a node
        System.out.print(c[que[first]] + " ");
        first++;
        return que[first - 1];
    }
}
