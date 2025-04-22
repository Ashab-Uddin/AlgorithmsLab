public class LcsPrint {

    // Function to find and print the LCS
    public static void printLCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Step 1: Create the DP table
        int[][] dp = new int[m + 1][n + 1];

        // Step 2: Fill the table based on LCS logic
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Step 3: Backtrack to find the actual LCS string
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // Since we built LCS from the end, we reverse it
        System.out.println("Longest Common Subsequence: " + lcs.reverse().toString());
    }

    public static void main(String[] args) {
        String str1 = "ACDBE";
        String str2 = "ABCDE";

        printLCS(str1, str2);
    }
}
