
import java.util.*;

public class DFSInput {
    static char[] c = new char[20];       // To store node names like A, B, C, etc.
    static int[] e = new int[20];         // Degree of each node
    static int[][] list = new int[20][20]; // Adjacency list
    static int[] checked = new int[20];   // Visited array
    static int[] stk = new int[20];       // Stack
    static int top = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, i, j;

        System.out.print("Enter number of nodes: ");
        n = sc.nextInt();
        sc.nextLine();

        // Input node names
        System.out.println("Enter node names (like A B C ...):");
        for (i = 0; i < n; i++) {
            c[i] = sc.next().charAt(0);
        }

        // Input adjacency list
        for (i = 0; i < n; i++) {
            System.out.print("Enter number of connections for " + c[i] + ": ");
            e[i] = sc.nextInt();
            System.out.print("Enter connected nodes for " + c[i] + ": ");
            for (j = 0; j < e[i]; j++) {
                char ch = sc.next().charAt(0);
                list[i][j] = indexOf(ch, n); // Get index of connected node
            }
        }

        // Choose starting node
        System.out.print("Enter starting node: ");
        char start = sc.next().charAt(0);
        int startIndex = indexOf(start, n);

        // DFS using stack
        push(startIndex);
        while (top != 0) {
            int node = stk[top - 1];
            int f = 0;
            for (i = 0; i < e[node]; i++) {
                if (notChecked(list[node][i]) == 1) {
                    push(list[node][i]);
                    f = 1;
                    break;
                }
            }
            if (f == 0) {
                pop();
            }
        }

        sc.close();
    }

    // Get index of a node from char
    static int indexOf(char ch, int n) {
        for (int i = 0; i < n; i++) {
            if (c[i] == ch)
                return i;
        }
        return -1;
    }

    // Check if node is already visited
    static int notChecked(int n) {
        return checked[n] == 0 ? 1 : 0;
    }

    // Pop from stack
    static int pop() {
        top--;
        return stk[top];
    }

    // Push to stack and print node
    static void push(int n) {
        checked[n] = 1;
        System.out.print(c[n] + " ");
        stk[top] = n;
        top++;
    }
}
