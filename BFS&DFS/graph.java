import java.util.*;

public class graph {
    static int[][] matrix = new int[20][20]; // 2D array that will contain the graph

    public static void main(String[] args) {
        int e = 9, n = 8; // e is number of edges, n is number of vertices

        inputMatrix(e);
        System.out.println("Output:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void inputMatrix(int e) {
        Scanner sn = new Scanner(System.in);
        System.out.println("Enter the edges (e.g., A B):");
        for (int i = 0; i < e; i++) {
            char j = sn.next().charAt(0);
            char k = sn.next().charAt(0);

            matrix[(int)j - 65][(int)k - 65] = matrix[(int)k - 65][(int)j - 65] = 1;
            // Undirected edge: A-B implies B-A
        }

        sn.close();
    }
}
