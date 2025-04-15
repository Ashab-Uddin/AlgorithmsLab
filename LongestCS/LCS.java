public class LCS {

    // Function to find the length of the longest common subsequence
    public static int lcs(char[] X, char[] Y, int m, int n) {
        // Base case: if either string is empty
        if (m == 0 || n == 0) {
            return 0;
        }

        // If last characters match, add 1 and recurse
        if (X[m - 1] == Y[n - 1]) {
            return 1 + lcs(X, Y, m - 1, n - 1);
        } else {
            // If last characters don't match, take the max of two possibilities
            return Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
        }
    }

    public static void main(String[] args) {
        String X = "BDCB";   // First sequence
        String Y = "BACDB";  // Second sequence

        // Convert strings to character arrays
        char[] arr1 = X.toCharArray();
        char[] arr2 = Y.toCharArray();

        int len1 = arr1.length;
        int len2 = arr2.length;

        // Print the result
        System.out.println("Length of LCS is: " + lcs(arr1, arr2, len1, len2));
    }
}
