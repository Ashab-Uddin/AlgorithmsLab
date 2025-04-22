import java.util.*;

public class LCS {

    // Function to compute LCS length
    public static int lcs(char X[], char Y[], int m, int n) {
        // The zeroth rows and columns are filled with zeroes
        if (m == 0 || n == 0) {
            return 0;
        }
        // The remaining values are filled by incrementing and choosing the maximum values according to the algorithm
        if (X[m - 1] == Y[n - 1]) {
            return 1 + lcs(X, Y, m - 1, n - 1);
        } else {
            return max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
        }
    }

    // Function to find the maximum between lcs(X, Y, m, n-1) and lcs(X, Y, m-1, n)
    static int max(int l1, int l2) {
        if (l1 < l2) {
            return l2;
        } else {
            return l1;
        }
    }

    public static void main(String[] args) {
        LCS lcsObj = new LCS();
        String X, Y;
        X = "BDCB"; // Sequence1
        Y = "BACDB"; // Sequence2
        char arr1[] = X.toCharArray();
        char arr2[] = Y.toCharArray();
        // Finding the length of two sequences
        int len1 = arr1.length;
        int len2 = arr2.length;
        // Printing the length of the longest common subsequence
        System.out.println("Length of LCS is: " + lcs(arr1, arr2, len1, len2));
    }
}
