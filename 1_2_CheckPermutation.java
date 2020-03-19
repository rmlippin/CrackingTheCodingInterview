// 1.2 Check Permutations
// Given two strings, write a method to decide if one is a permutation of the
// other.

// Initial ideas
// 1. Use a hash table like IsUnique, if we can assume unicode. O(n)
// 2. Maybe sort, and step through 1 by 1? O(nlogn)

class CheckPermutation {

    public static void main(String[] args) {
        printIsPermutation("cat", "tac");
        printIsPermutation("cata", "tac");
        printIsPermutation("cat", "cab");
        printIsPermutation("aabb", "aaab");
        printIsPermutation("abcdefghijklmnopqrstuvwxyz", "qwertyuiopasdfghjklzxcvbnm");
    }

    private static void printIsPermutation(String str1, String str2) {
        System.out.println(str1 + "|" + str2 + ": " + isPermutationHash(str1, str2)
            + "," + isPermutationSort(str1, str2));
    }

    // Assumptions:
    // 1. Case matters
    // 2. It is ASCII
    // 3. Whitespace matters
    private static boolean isPermutationHash(String str1, String str2) {

        if (str1.length() != str2.length())
            return false;

        int[] charHash = new int[128];
        for (int i=0; i<128; i++) {
            charHash[i] = 0;
        }

        // Populate hash with str1 values
        for (int i=0; i<str1.length(); i++) {
            charHash[(int)str1.charAt(i)]++;
        }

        // Subtract from hash with str2 values
        for (int i=0; i<str2.length(); i++) {
            charHash[(int)str2.charAt(i)]--;
            if (charHash[(int)str2.charAt(i)] < 0)
                return false;
        }

        // Check that all values are zero
        for (int i=0; i<128; i++) {
            if (charHash[i] != 0)
                return false;
        }

        // Otherwise return true
        return true;
    }

    public static boolean isPermutationSort(String str1, String str2) {

        if (str1.length() != str2.length())
            return false;

        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();

        java.util.Arrays.sort(char1);
        java.util.Arrays.sort(char2);

        return java.util.Arrays.equals(char1, char2);
    }

}