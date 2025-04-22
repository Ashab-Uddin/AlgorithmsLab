import java.util.*;

public class DFS {
    static char[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'S'};
    static int[] e = {2, 2, 2, 2, 2, 2, 3, 3};
    
    // Adjacency list of the graph in Figure 1
    static int[][] list = {
        {3, 7},      // A
        {4, 7},      // B
        {5, 7},      // C
        {0, 6},      // D
        {1, 6},      // E
        {2, 6},      // F
        {3, 4, 5},   // G
        {0, 1, 2}    // S
    };
    
    static int[] checked = new int[20]; // To keep track of visited nodes
    static int[] stk = new int[20];     // Stack for DFS
    static int top = 0;                 // Stack pointer

    public static void main(String[] args) {
        int i, n, f = 0;
        push(7); // Start from node 'S' (index 7)

        while (top != 0) {
            n = stk[top - 1]; // Peek the top of the stack

            f = 0; // Reset flag before checking neighbors
            for (i = 0; i < e[n]; i++) {
                if (notChecked(list[n][i]) == 1) {
                    push(list[n][i]);
                    f = 1;
                    break;
                }
            }

            if (f == 0) {
                pop(); // Backtrack
            }
        }
    }

    // Checks if a node has not been visited
    static int notChecked(int n) {
        return checked[n] == 0 ? 1 : 0;
    }

    // Pops the top node from the stack
    static int pop() {
        top--;
        return stk[top];
    }

    // Pushes a node onto the stack and marks it as visited
    static void push(int n) {
        checked[n] = 1;
        System.out.print(c[n] + " "); // Print the visited node
        stk[top] = n;
        top++;
    }
}
