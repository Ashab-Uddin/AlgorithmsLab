public class lcspractice {
    public static int lcsprac(char [] X, char [] Y, int m,int n){
        if(m == 0 || n == 0){
            return 0;
        }
        if(X[m-1] == Y[n-1]){
            return 1 +lcsprac(X, Y, m-1, n-1);
        }
        else{
            return Math.max(lcsprac(X, Y, m, n-1),lcsprac(X, Y, m-1, n));
        }
    }
    public static void main(String[] args) {
        String X = "ABCDEF";
        String Y = "ACDGH";

        char [] arr1 = X.toCharArray();
        char [] arr2 = Y.toCharArray();

        int len1 = arr1.length;
        int len2 = arr2.length;

        System.out.println("length of LCS is: "+lcsprac(arr1, arr2, len1, len2));

    }
}
