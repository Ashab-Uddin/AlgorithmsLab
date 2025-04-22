import java.util.*;

public class SecondLongest {

    private static int[][] memo;       // LCS length table
    private static BitSet[][] ways;    // bit‑sets of attainable lengths

    static int lcsLen(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0) {
            ways[m][n] = new BitSet();
            ways[m][n].set(0);
            return memo[m][n] = 0;
        }
        if (memo[m][n] != -1) return memo[m][n];

        if (X[m - 1] == Y[n - 1]) {
            int len = 1 + lcsLen(X, Y, m - 1, n - 1);
            ways[m][n] = shiftRight(ways[m - 1][n - 1]);
            ways[m][n].set(len);
            return memo[m][n] = len;
        } else {
            int l1 = lcsLen(X, Y, m, n - 1);
            int l2 = lcsLen(X, Y, m - 1, n);
            memo[m][n] = Math.max(l1, l2);
            ways[m][n] = (BitSet) ways[m][n - 1].clone();
            ways[m][n].or(ways[m - 1][n]);
            return memo[m][n];
        }
    }

    private static BitSet shiftRight(BitSet bs) {
        BitSet res = new BitSet();
        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1))
            res.set(i + 1);
        return res;
    }

    public static void main(String[] args) {
        String X = "BDCB";
        String Y = "BACDB";

        char[] a = X.toCharArray();
        char[] b = Y.toCharArray();
        int m = a.length, n = b.length;

        memo = new int[m + 1][n + 1];
        ways = new BitSet[m + 1][n + 1];
        for (int[] row : memo) Arrays.fill(row, -1);

        int longest = lcsLen(a, b, m, n);
        int secondLongest = ways[m][n].previousSetBit(longest - 1);

        System.out.println("Length of longest  LCS : " + longest);
        if (secondLongest >= 0)
            System.out.println("Length of second‑longest LCS : " + secondLongest);
        else
            System.out.println("Second‑longest subsequence does not exist.");
    }
}
