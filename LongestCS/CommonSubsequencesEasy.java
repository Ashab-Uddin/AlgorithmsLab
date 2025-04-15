public class CommonSubsequencesEasy {

    static String[] foundSubsequences = new String[1000]; 
    static int numberOfSubsequences = 0;

    public static void findCommonSubsequences(char[] stringA, char[] stringB, int indexA, int indexB, String currentSubsequence) {
        if (indexA == 0 || indexB == 0) {
            if (!currentSubsequence.isEmpty()) {
                saveIfNotDuplicate(currentSubsequence);
            }
            return;
        }

        if (stringA[indexA - 1] == stringB[indexB - 1]) {
            findCommonSubsequences(stringA, stringB, indexA - 1, indexB - 1, stringA[indexA - 1] + currentSubsequence);
        } else {
            findCommonSubsequences(stringA, stringB, indexA - 1, indexB, currentSubsequence);
            findCommonSubsequences(stringA, stringB, indexA, indexB - 1, currentSubsequence);
        }
    }
    public static void saveIfNotDuplicate(String newSubsequence) {
        for (int i = 0; i < numberOfSubsequences; i++) {
            if (foundSubsequences[i].equals(newSubsequence)) {
                return;  // Duplicate found, skip it
            }
        }
        foundSubsequences[numberOfSubsequences++] = newSubsequence;
    }
    public static void sortSubsequencesByLength() {
        for (int i = 0; i < numberOfSubsequences - 1; i++) {
            for (int j = i + 1; j < numberOfSubsequences; j++) {
                if (foundSubsequences[i].length() < foundSubsequences[j].length()) {
                    String temp = foundSubsequences[i];
                    foundSubsequences[i] = foundSubsequences[j];
                    foundSubsequences[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        String firstText = "ABCDEFGH";
        String secondText = "AEDHG";

        char[] charsOfFirst = firstText.toCharArray();
        char[] charsOfSecond = secondText.toCharArray();

        findCommonSubsequences(charsOfFirst, charsOfSecond, charsOfFirst.length, charsOfSecond.length, "");

        sortSubsequencesByLength();

        System.out.println("Common subsequences (from longest to shortest):");
        for (int i = 0; i < numberOfSubsequences; i++) {
            System.out.println(foundSubsequences[i]);
        }
    }
}
